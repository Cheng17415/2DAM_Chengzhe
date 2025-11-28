package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import clases.Persona;

public class FicheroOrdenadoClases {
	public static void main(String[] args) {
		List <Persona> lista = new ArrayList<>();
		String ficheroEntrada = "listaPersonas.txt";
		String ficheroSalida = "listaOrdEdad.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada))){
			String linea;
			while((linea = br.readLine()) != null) {
				String [] t = linea.split(":");
				if(t.length == 2) {
					lista.add(new Persona(t[0], Integer.parseInt(t[1])));
				}
				
			}
			//Ordenar alfabeticamente
			Collections.sort(lista);
			//Descendente
			/*Collections.sort(lista, new Comparator<Persona>(){
				@Override
				public int compare(Persona o1, Persona o2) {
					int n = o2.getEdad() - o1.getEdad();
					if(n!=0) return n;
					return o2.getNombre().compareTo(o1.getNombre());
				}
			});*/
		} catch(IOException e) {
			System.out.print("Fichero no encontrado");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
			for (Persona p : lista) {
				bw.write(p.getNombre() + ":" + p.getEdad() +"\n");
			}
			System.out.println("Ordenado con éxito");
		} catch(IOException e) {
			System.out.print("Fichero no encontrado");
		}
	}
}
