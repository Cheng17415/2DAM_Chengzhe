package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FicheroOrdenarAlfa {
	public static void main(String[] args){
		List <String> lista = new ArrayList<>();
		String ficheroEntrada = "listaAlumnos.txt";
		String ficheroSalida = "listaAlumnos.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada))){
			String linea;
			while((linea = br.readLine()) != null) {
				lista.add(linea);
			}
			//Ordenar alfabeticamente
			Collections.sort(lista);
			
		} catch(IOException e) {
			System.out.print("Fichero no encontrado");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
			for (String s : lista) {
				bw.write(s + "\n");
			}
			System.out.println("Ordenado alfabéticamente con éxito");
		} catch(IOException e) {
			System.out.print("Fichero no encontrado");
		}
	}
}
