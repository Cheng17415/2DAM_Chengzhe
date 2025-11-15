package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Clases.Biblioteca;

public class Fich_06 {
	// Hacer una sola lectura del fichero y queremos que posteriormente crear
	// nombreEquipo.csv y dentro los integrantes
	// HashMap<String,ArrayList<String>>
	static HashMap<String, ArrayList<String>> hm = new HashMap<>();

	
	public static void main(String[] args) throws IOException {
		String camino = "C:\\Ficheros\\Ejercicio6";
		Biblioteca.crearSubDirectorio(camino);
		leer();
		escribir(camino);
	}

	public static void leer() throws IOException {

		String[] matriz = null;
		try (BufferedReader br = new BufferedReader(new FileReader("jugadores.csv"))) {
			String equipo = br.readLine();
			while (equipo != null) {
				matriz = equipo.split(",");
				if (!hm.containsKey(matriz[7])) {
					hm.put(matriz[7], new ArrayList<String>());
				}
				ArrayList<String> jugadores = hm.get(matriz[7]);
				jugadores.add(equipo);
				hm.put(matriz[7], jugadores);
				equipo = br.readLine();
			}
		}

	}

	public static void escribir(String camino) throws IOException {
		for (Entry<String, ArrayList<String>> entry : hm.entrySet()) {
			String nombre = entry.getKey();
			ArrayList<String> jugadores = entry.getValue();

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(camino + "\\" +nombre + ".csv"))) {
				for(String s: jugadores) {
					bw.write(s + "\n");
				}
			} catch (FileNotFoundException e) {
				System.out.println("Fichero no encontrado");
			}
			
		}
	}
}
