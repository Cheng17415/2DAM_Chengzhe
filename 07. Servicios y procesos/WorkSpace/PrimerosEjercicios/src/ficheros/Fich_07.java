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

public class Fich_07 {
	static HashMap <String,ArrayList<String>> hmProvincias = new HashMap <>();
	public static void main(String[] args) throws IOException {
		//Crear por cada provincia la comunidad autónoma
		leer();
		escribir();
	}
	public static void leer() throws IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader("PROVINCIAS.CSV"))) {
			String linea = br.readLine();
			String [] separado = null;
			while(linea!= null) {
				separado = linea.split(",");
				if(!hmProvincias.containsKey(separado[0])) {
					hmProvincias.put(separado[0], new ArrayList<>());
				}
				ArrayList<String> alComunidades = hmProvincias.get(separado[0]);
				alComunidades.add(separado[1]);
				hmProvincias.put(separado[0], alComunidades);
				linea = br.readLine();
				System.out.println("Archivo leído con éxito");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		}
	}
	public static void escribir() throws IOException{
		for (Entry<String, ArrayList<String>> entry : hmProvincias.entrySet()) {
			String provincia = entry.getKey();
			ArrayList<String> comunidades = entry.getValue();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(provincia + ".csv"))) {
				for (String comunidad: comunidades) {
					bw.write(comunidad + "\n");
				}
				System.out.println("Escrito con éxito");
				
			}catch (FileNotFoundException e) {
				System.out.println("Fichero no encontrado");
			}
			
		}
		
	}
}
