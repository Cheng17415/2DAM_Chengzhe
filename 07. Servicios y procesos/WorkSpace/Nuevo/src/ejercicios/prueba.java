package ejercicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class prueba {

	public static void main(String[] args) throws IOException {
		String cadena;

		if (args.length == 2) {
			System.out.println(args[0]);
			System.out.println(args[1]);
		}
		String fichero = args[0];
		String palabra = args[1];
		int co=0;

		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			cadena = br.readLine();
			while (cadena != null) {
				co+=busca(cadena, palabra);
				cadena = br.readLine();
			}
			System.out.println(co);
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo de entrada.");
		}

	}

	public static int busca(String cadena, String palabra) {

		int pos = cadena.indexOf(palabra);

		int c = 0;
		while (pos >= 0) {
			c++;
			cadena = cadena.substring(pos + palabra.length());
			pos = cadena.indexOf(palabra);
		}
		return c;
	}
}
