package ficheros;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Clases.Biblioteca;

public class Fich_03 {
	
	/*Generar en un fichero las notas aleatorios(50) relativas a fisica,
	 *quimica y mates. Separarlas por Alumno1:10:5:4 .csv*/
	public static void main(String[] args) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("notasAlumnos.csv"))){
			ArrayList<String> lista = new ArrayList<String>();
			for (int i = 1; i <= 50; i++) {
				lista.add("Alumno"+i+":"+Biblioteca.alea(0, 10)+":"+Biblioteca.alea(0, 10)+":"+Biblioteca.alea(0, 10));
			}
			for(String s : lista) {
				bw.write(s + "\n");
			}
		}catch(FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		}

	}

}
