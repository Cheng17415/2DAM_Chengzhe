package ejercicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/* 	Diccionario Castellano Ingles.
   	Preguntar la palabra en Castellano, mostrar en Ingles
   	Si no existe, introducir traduccion en Inglés */

public class Ejercicio08 {
	public static Scanner sc = new Scanner(System.in);
	public static HashMap<String, String> diccionario = new HashMap <>();
	public static void main(String[] args) {

		crearArchivo();
		leerArchivo();
		buscarDic(diccionario);
		sc.close();
		
		/*	Formas de parsear en un Map
		for (String c : diccionario.keySet()) System.out.println(c + " " + diccionario.get(c));
		for (String c : diccionario.values()) System.out.println(c);
		for (Entry<String,String> c : diccionario.entrySet()) {
			System.out.println(c.getKey() + " " + c.getValue());
		}*/
	}
	
	public static void buscarDic(HashMap <String, String> diccionario) {
		int temp = -1;
		do {
			System.out.println("Introduzca palabra a buscar: ");
			String palabra = sc.nextLine().toLowerCase();
			
			if (diccionario.containsKey(palabra)) {
				System.out.println(palabra + ": " + diccionario.get(palabra));
			} else {
				System.out.println("La palabra no existe en el diccionario, introduzca su significado");
				String sign = sc.nextLine();
				diccionario.put(palabra, sign);
				escribirArchivo(palabra, sign);
			}
			System.out.println("Pulse 0 para salir");
			System.out.println("Pulse 1 para continuar");
			temp = Integer.valueOf(sc.nextLine());
			
		} while(temp != 0);
		
	}
	
	public static void crearArchivo(){
		File archivo = new File("diccionario.txt");
	
		try {
			if(archivo.createNewFile()) {
				System.out.println("Archivo creado con éxito: " + archivo.getName());
			}
			else {
				System.out.println("Archivo ya existente "+ archivo.getAbsolutePath());
			}
			
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error");
			e.printStackTrace();
		}
	}
	
	public static void escribirArchivo(String palabra, String sign) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("diccionario.txt",true))){
			bw.write(palabra + ":" + sign);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error");
			e.printStackTrace();
		}
	}
	
	public static void leerArchivo(){
		File f = new File("diccionario.txt");
		try (Scanner leer = new Scanner(f)){
			System.out.println("Archivo leído con éxito");
			while(leer.hasNextLine()) {
				String linea = leer.nextLine();
				String[] datos = linea.split(":");
				diccionario.put(datos[0], datos[1]);
			}
		} catch (IOException e1) {
			System.out.println("Ha ocurrido un error al leer el archivo");
			e1.printStackTrace();
		}
	}
}
