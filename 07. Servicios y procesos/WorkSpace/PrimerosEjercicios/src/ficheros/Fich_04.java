package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fich_04 {
	//Leyendo este fichero, crear 2 ficheros. Los que han suspendido(alguna) y aprobados.
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("notasAlumnos.csv"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("aprobados.csv"));
				BufferedWriter bw2 = new BufferedWriter(new FileWriter("suspendidos.csv"));)
			{
			String[] lista = null;
			String texto = br.readLine();
			while (texto != null) {

				lista = texto.split(":");
				if (Integer.parseInt(lista[1]) < 5 || Integer.parseInt(lista[2]) < 5
						|| Integer.parseInt(lista[3]) < 5) {
					bw2.write(texto + "\n");
				}
				
				else bw.write(texto + "\n");
				
				texto = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		}
		
	}
}
