package Principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import clases.Biblioteca;
import clases.CPersona;

public class Inicio {
	public static Scanner sc = new Scanner(System.in);

	public static void crearFichero(File fichero, boolean añadir) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = null;
		String respuesta, nombre, direccion;
		long telefono;
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
				System.out.print("Dime el nombre ");
				nombre = sc.nextLine();
				System.out.print("Dime la direccion ");
				direccion = sc.nextLine();
				System.out.print("Dime el telefono");
				telefono = Long.valueOf(sc.nextLine());
				oos.writeObject(new CPersona(nombre, direccion, telefono));
				System.out.print("¿Quiere usted seguir añadiendo registros? ");
				respuesta = sc.nextLine();
			} while (respuesta.equalsIgnoreCase("Si"));
		} finally {
			oos.close();
		}
	}

	/*********************************************************************************************/
	public static void mostrarFichero(File fichero) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
			do {
				Object aux = ois.readObject();
				if (aux instanceof CPersona)System.out.println(aux);
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
			
			if(fichero.exists()) {
				mostrarFichero(fichero);
			} else {
				System.out.println("El fichero " + nombreFichero + " no existe.");
			}
		}
	/*********************************************************************************************/
	public static void crear() {
		File fichero = null;
		String nombreFichero = null,respuesta;
		try {
			System.out.println("Indique el nombre del fichero");
			nombreFichero = sc.nextLine();
			fichero = new File(nombreFichero);
			
			if(fichero.exists()) {
				System.out.println("El fichero existe.¿Quiere usted añadir registros?");
				respuesta = sc.nextLine();
				if(respuesta.equalsIgnoreCase("SI")) {
					crearFichero(fichero,true);
				}else {
					crearFichero(fichero,false);
				}
			} else {
				crearFichero(fichero,false);
			}
		}catch(IOException ex) {
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
			case 1: //Crear fichero
				crear();
				break;
			case 2: //Leer fichero
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
