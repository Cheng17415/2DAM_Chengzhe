package ficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Clases.Biblioteca;

public class Fich_08 {
	//Escribe en primos.dat  numero primos que hay entre 1 y 500.
	public static void main(String[] args) {
		try (BufferedWriter br = new BufferedWriter(new FileWriter("primos.dat"))) {
			for (int i = 1; i <= 500; i++) {
				if(Biblioteca.esPrimo(i)) {
					br.write(i + "\n");
				}
			}
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error con el archivo");
		}	
	}
}
