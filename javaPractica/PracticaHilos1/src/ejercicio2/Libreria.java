package ejercicio2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Libreria {
	public static void main(String[] args) {
		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("Indique el nombre del fichero (30ficheroDeLenguaje.txt: "
		 * ); String input = sc.nextLine();
		 * 
		 * if(!Character.isDigit(input.charAt(0))){
		 * System.out.println("No se realizo ya que no empieza por un digito");
		 * sc.close(); return; } int n = 0; for (int i = 1; i < input.length(); i++) {
		 * if(!Character.isDigit(input.charAt(i))){ n = i; break; } } int numero =
		 * Integer.parseInt(input.substring(0, n)); String archivo = input.substring(n);
		 * String[] palabrasGeneradas = Generador.generarPalabras(numero);
		 * try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){ for
		 * (String s : palabrasGeneradas) { bw.write(s + "\n"); } } catch (IOException
		 * e) { e.printStackTrace(); } sc.close(); }
		 */
		// EJERCICIO CON HILOS
		Scanner sc = new Scanner(System.in);
		final int INSTANCIAS = 10;
		System.out.println("Indique el nombre del fichero (ficheroDeLenguaje.txt: ");
		String archivo = sc.nextLine().strip();

		// Creando y lanzando hilos
		Hilo[] hilos = new Hilo[INSTANCIAS];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Hilo((i + 1) * 10);
			hilos[i].setName("Hilo" + i);
			hilos[i].start();
			System.out.println("Empezando hilo " + hilos[i].getName());
		}

		// Esperar a que terminen los hilos
		for (Hilo hilo : hilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("hola");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
			for (Hilo hilo : hilos) {
				for (String s : hilo.getPalabras()) {
					bw.write(s + "\n");
				}
				System.out.println("Terminado hilo " + hilo.getName());
			}
			System.out.println("Se realizo con exito");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}
}

class Generador {
	public static String[] generarPalabras(int num) {
		// 2 espacios para que sea mas probable
		String letras = "abcdefghijklmnopqrstuvwxyz  ";
		int alea;
		String[] palabras = new String[num];
		Arrays.fill(palabras, "");
		for (int i = 0; i < num; i++) {
			while (true) {
				alea = (int) (Math.random() * letras.length());
				char c = letras.charAt(alea);
				if (c == ' ')
					break;
				palabras[i] += c;
			}
		}
		return palabras;
	}
}

class Hilo extends Thread {
	private int nPalabras;
	private String[] palabras;

	public Hilo(int nPalabras) {
		super();
		this.nPalabras = nPalabras;
		palabras = new String[nPalabras];
	}

	public int getNPalabras() {
		return nPalabras;
	}

	public void setNPalabras(int nPalabras) {
		this.nPalabras = nPalabras;
	}

	public String[] getPalabras() {
		return this.palabras;
	}

	@Override
	public void run() {
		palabras = Generador.generarPalabras(nPalabras);
	}

}
