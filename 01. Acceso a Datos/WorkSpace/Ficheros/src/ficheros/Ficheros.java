package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ficheros {

	public static void main(String[] args) throws IOException {
		// ficheroNumeros();
		// ficheroTexto();
		// crearFicheroNotas();
		// clasificarAlumnos();
		// lecturaFicheroAprobados();
		crear();
		AprobadosSuspensos();
	}

	// EJERCICIO RESUELTO POR EL PROFE
	public static void crear() throws IOException {

		String cadena = "";
		try (BufferedWriter bw1 = new BufferedWriter(new FileWriter("alumnos.dat"));) {
			for (int i = 0; i < 50; i++) {
				cadena = String.format("Alumno:%d:%d:%d", i, alea(0, 10), alea(0, 10), alea(0, 10));
				bw1.write(cadena + "\n");
			}

		} catch (FileNotFoundException ex) {
			System.out.print("Fichero no encontrado");
		}
	}

	public static void AprobadosSuspensos() throws IOException {
		String linea ="",  matriz[];
		try (BufferedReader br1 = new BufferedReader(new FileReader("alumnos.dat"));
		         BufferedWriter bw1 = new BufferedWriter(new FileWriter("aprobados.dat"));
		         BufferedWriter bw2 = new BufferedWriter(new FileWriter("suspensos.dat"))) {
			linea = br1.readLine();
			while (linea != null) {
				matriz = linea.split(":");
				if (Integer.valueOf(matriz[1]) >= 5 && 
					Integer.valueOf(matriz[2]) >= 5&& 
					Integer.valueOf(matriz[3]) >= 5
				 ) {
					bw1.write(linea);
					bw1.newLine();
				} else {
					bw2.write(linea);
					bw2.newLine();
				}
				linea = br1.readLine();
			}

		} catch (FileNotFoundException ex) {
			System.out.print("Fichero no encontrado");
		}
	}

	// Método para generar notas aleatorias y escribir en archivo
	public static void crearFicheroNotas() {
		ArrayList<String> lista = new ArrayList<>();

		// Leer todos los nombres del archivo
		try (BufferedReader br1 = new BufferedReader(new FileReader("listaAlumnos.txt"))) {
			String linea;
			while ((linea = br1.readLine()) != null) {
				lista.add(linea.trim());
			}

			if (lista.isEmpty()) {
				System.out.println("El archivo listaAlumnos.txt está vacío.");
				return;
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter("notasAlumnos.txt"))) {
				for (int i = 0; i < 50; i++) {
					String nombreCompleto = lista.get(alea(0, lista.size() - 1));
					int fisica = alea(1, 10);
					int quimica = alea(1, 10);
					int matematicas = alea(1, 10);

					String lineaSalida = nombreCompleto + ": Fisica:" + fisica + " Quimica:" + quimica + " Matematica:"
							+ matematicas;
					bw.write(lineaSalida);
					bw.newLine();
				}

				System.out.println("Archivo notasAlumnos.txt generado correctamente.");

			} catch (IOException e) {
				System.out.println("Error al escribir el archivo: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error al leer listaAlumnos.txt: " + e.getMessage());
		}
	}

	public static void clasificarAlumnos() {
		try (BufferedReader br = new BufferedReader(new FileReader("notasAlumnos.txt"));
				BufferedWriter aprobados = new BufferedWriter(new FileWriter("aprobados.txt"));) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(": ");
				String[] notasTexto = partes[1].split(" ");
				int notaFisica = Integer.parseInt(notasTexto[0].split(":")[1]);
				int notaQuimica = Integer.parseInt(notasTexto[1].split(":")[1]);
				int notaMatematica = Integer.parseInt(notasTexto[2].split(":")[1]);

				double promedio = (notaFisica + notaQuimica + notaMatematica) / 3.0;

				if (promedio >= 5) {
					aprobados.write(linea);
					aprobados.newLine();
				} else {
					System.out.println("Suspendidos");
				}
			}
			System.out.println("Clasificación completada: aprobados.txt generados.");
		} catch (IOException e) {
			System.out.println("Error al procesar el archivo: " + e.getMessage());
		}
	}

	public static void lecturaFicheroAprobados() {
		System.out.println("\n--- Alumnos Aprobados ---");
		try (BufferedReader reader = new BufferedReader(new FileReader("aprobados.txt"))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de aprobados: " + e.getMessage());
		}
	}

	public static int alea(int li, int ls) {
		return (int) (Math.random() * (ls - li + 1)) + li;
	}

	public static void ficheroTexto() {
		String linea1 = "";
		String linea2 = "";
		try (BufferedReader br1 = new BufferedReader(new FileReader("textosA.dat"));
				BufferedReader br2 = new BufferedReader(new FileReader("textosB.dat"));
				BufferedWriter bw1 = new BufferedWriter(new FileWriter("textoAlterno.dat"))) {
			linea1 = br1.readLine();
			linea2 = br2.readLine();

			while (linea1 != null && linea2 != null) {// Si ninguno de los ha terminado y lee en forma alterna
				bw1.write(linea1);
				bw1.newLine();
				bw1.write(linea2);
				bw1.newLine();
				linea1 = br1.readLine();
				linea2 = br2.readLine();
			}
			while (linea1 != null) {
				bw1.write(linea1);
				bw1.newLine();
				linea1 = br1.readLine();
			}
			while (linea2 != null) {
				bw1.write(linea2);
				bw1.newLine();
				linea2 = br2.readLine();
			}

		} catch (FileNotFoundException ex) {
			System.out.print("Fichero no encontrado");
		} catch (IOException ex) {
			System.out.print(ex.getMessage());
		}
	}

	public static void ficheroNumeros() throws IOException {
		ArrayList<Integer> lista = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("numeros.dat"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("numeros_sort.dat"));) {
			String linea = "";
			linea = br.readLine();

			while (linea != null) {
				System.out.print(linea);
				lista.add(Integer.valueOf(linea));
				linea = br.readLine();
			}
			Collections.sort(lista); // es un método en Java que se utiliza para ordenar elementos de una lista en
										// orden ascendente
			for (Integer item : lista) {
				bw.write(item + "\n");
			}

			// br.close();
		} catch (FileNotFoundException ex) {
			System.out.print("Fichero no encontrado");
		}
	}
}