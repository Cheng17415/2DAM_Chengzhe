package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Fich_05 {
	//Hacer programa que se le pida el nombre del equipo y nos cree un fichero nombre equipo.csv que contenga los integrantes del equipo
	public static void crearSubDirectorio(String directorio) {
		//Ejemplo C:\FICHEROS
		File fich = new File(directorio);
		if(!fich.exists()) {
			fich.mkdir();
			System.out.println("Fichero creado con éxito");
		}
		else {
			if(!fich.isDirectory()) fich.mkdir();
			System.out.println("Fichero ya existe");
			}
	}
	public static void main(String[] args) throws IOException {
		String camino ="C:\\Ficheros";
		crearSubDirectorio(camino);
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime el nombre completo del equipo");
		String club = sc.nextLine();
		fichero(club, camino);
		sc.close();
	}
	public static void fichero(String club, String camino) throws IOException{
		File f = new File(club + ".csv");
		try (BufferedReader br = new BufferedReader(new FileReader("jugadores.csv"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(camino + "\\"+f)))
			{
			String[] lista = null;
			String texto = br.readLine();
			if(texto == null) {
				System.out.println("El fichero está vacio");
				return;
			}
			while (texto != null) {
				lista = texto.split(",");
				if(lista[7].compareToIgnoreCase(club) == 0) {
					bw.write(texto + "\n");
				}
				texto = br.readLine();
			}
			System.out.println("Escrito con éxito en " + f.getName() + f.getAbsolutePath());
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		}
		
	}
	
}
