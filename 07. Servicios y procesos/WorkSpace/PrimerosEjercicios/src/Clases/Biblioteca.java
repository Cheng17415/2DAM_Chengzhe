package Clases;

import java.io.File;
import java.util.Scanner;

public class Biblioteca {
	// Método estático
	// Menú
	public static int menu(Scanner sc, String[] opciones) {
		int op;
		do {
			for (String items : opciones)
				System.out.println(items);
			System.out.println("Dime la opcion a realizar");
			op = Integer.valueOf(sc.nextLine());
		} while (op < 1 || op > opciones.length);
		return op;
	}

	// 10.24567 Redondear a 2 decimales
	public static double redondear(double num, int pos) {
		double pot = Math.pow(10, pos);
		return Math.round(num * pot) / pot;
	}

	// 10.24567 Truncar a 2 decimales
	public static double truncar(double num, int pos) {
		double pot = Math.pow(10, pos);
		return (int) (num * pot) / pot;
	}

	public static int alea(int li, int ls) {
		return (int) (Math.random() * (ls - li + 1)) + li;
	}

	public static boolean esPrimo(int n) {
		if (n == 1)
			return false;
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void crearSubDirectorio(String directorio) {
		File fich = new File(directorio);
		if (!fich.exists()) {
			fich.mkdir();
			System.out.println("Fichero creado con éxito");
		} else {
			System.out.println("Fichero ya existe");
		}
	}
	// Herencia polimorfismo
	// Clase abstracta figuras geometricas
	// Cuyo atributo sea dato UNO de tipo double
	// Y tiene que incorporar dos metodos abstractos que sean el area y el perímetro
}
