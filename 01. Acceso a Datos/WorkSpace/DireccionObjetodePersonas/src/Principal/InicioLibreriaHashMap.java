package Principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import clases.Biblioteca;
import clases.Libro;

public class InicioLibreriaHashMap {
	public static Scanner sc = new Scanner(System.in);

	public static void crearFichero(File fichero, boolean añadir) throws FileNotFoundException, IOException {
		HashMap<String, ArrayList<Libro>> mapa = new HashMap<>();
		ObjectOutputStream oos = null;
		String respuesta, titulo, autor, fecha, coleccion;
		LocalDate fecha_publicacion;
		try {
			if (!añadir) {
				oos = new ObjectOutputStream(new FileOutputStream(fichero));
			} else {
				oos = new ObjectOutputStream(new FileOutputStream(fichero, true)) {
					protected void writeStreamHeader() throws IOException {
						reset();
					}
				};

			}
			do {
				System.out.print("Dime el nombre de la coleccion ");
				coleccion = sc.nextLine();
				ArrayList<Libro> libros = new ArrayList<>();
				do {
					System.out.print("Dime el titulo ");
					titulo = sc.nextLine();
					System.out.print("Dime la autor ");
					autor = sc.nextLine();
					System.out.print("Dime la fecha de publicacion (YYYY-MM-DD)");
					fecha = sc.nextLine();
					fecha_publicacion = LocalDate.parse(fecha);
					libros.add(new Libro(titulo, autor, fecha_publicacion));

					System.out.print("¿Quiere usted seguir añadiendo libros? ");
					respuesta = sc.nextLine();
				} while (respuesta.equalsIgnoreCase("SI"));
				mapa.put(coleccion, libros);
				
				System.out.print("¿Quiere usted seguir añadiendo colecciones? ");
				respuesta = sc.nextLine();
			} while (respuesta.equalsIgnoreCase("SI"));
			oos.writeObject(mapa);

		} finally {
			oos.close();
		}
	}

	/*********************************************************************************************/
	public static void mostrarFichero(File fichero) {
		HashMap<String, ArrayList<Libro>> aux = new HashMap<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
			do {
				aux = (HashMap<String, ArrayList<Libro>>) ois.readObject();

				if(aux instanceof HashMap<String,ArrayList<Libro>>) {
					for(Entry<String,ArrayList<Libro>> co : aux.entrySet()) {
						System.out.println("Coleccion: " + co.getKey());
						for(Libro l:co.getValue()) System.out.println(l);
						System.out.println();
					}
				}

			} while (true);
		} catch (IOException ex) {
			System.out.println("Fin de fichero");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/*********************************************************************************************/
	public static void leer() {
		File fichero = null;
		String nombreFichero = null;

		System.out.println("Indique el nombre del fichero");
		nombreFichero = sc.nextLine();
		fichero = new File(nombreFichero);

		if (fichero.exists()) {
			mostrarFichero(fichero);
		} else {
			System.out.println("El fichero " + nombreFichero + " no existe.");
		}
	}

	/*********************************************************************************************/
	public static void crear() {
		File fichero = null;
		String nombreFichero = null, respuesta;
		try {
			System.out.println("Indique el nombre del fichero");
			nombreFichero = sc.nextLine();
			fichero = new File(nombreFichero);

			if (fichero.exists()) {
				System.out.println("El fichero existe.¿Quiere usted añadir registros?");
				respuesta = sc.nextLine();
				if (respuesta.equalsIgnoreCase("SI")) {
					crearFichero(fichero, true);
				} else {
					crearFichero(fichero, false);
				}
			} else {
				crearFichero(fichero, false);
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/*********************************************************************************************/
	public static void main(String[] args) {
		String[] opciones = { "1.-Crear fichero", "2.-Leer fichero", "3.-Salir" };
		int op;

		do {
			op = Biblioteca.menu(sc, opciones);

			switch (op) {
			case 1: // Crear fichero
				crear();
				break;
			case 2: // Leer fichero
				leer();
				break;
			}
			if (op != opciones.length) {
				System.out.println("Presione una tecla para continuar");
				sc.nextLine();
			}
		} while (op != opciones.length);
	}
}
