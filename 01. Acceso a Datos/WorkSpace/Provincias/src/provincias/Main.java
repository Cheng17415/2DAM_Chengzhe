package provincias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		/*Nos proporciona fichero
		LEER FICHERO provincias csv.
		Generar por cada provincia generar poblacion con edades entre
			num aleatorio entre 0 y 128 años*/
		leerFichero(true);
	}
	public static void leerFichero(boolean hasHeader) {
		String fichero = "PROVINCIAS.CSV";
		String temp = "PROVINCIASCONEDAD.csv";
		try(BufferedReader br = new BufferedReader(new FileReader(fichero));
				BufferedWriter bw = new BufferedWriter(new FileWriter(temp))){
			//Primera linea es header
			if(hasHeader) {
				bw.write(br.readLine());
				bw.newLine();
			}
			String linea = null;
			while((linea = br.readLine()) != null){
				bw.write(linea +"," +alea(0,128));
				bw.newLine();
			}
			
			
			System.out.println("Fichero escrito con exito");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	public static int alea(int li, int ls) {
		return (int)(Math.random() * (ls - li +1)) +li;
	}
}
