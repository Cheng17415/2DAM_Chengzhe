package ficherosEquipos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class FicherosEquipos {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//leerArchivoEquipo();
		leerEquipoJugadores();
	}
	//CLAVE EQUIPO VALOR JUGADORES
	public static void leerEquipoJugadores() {
		String matriz[], linea = "";
		HashMap<String, ArrayList<String>> equipo = new HashMap<String, ArrayList<String>>();	
		try (
		        BufferedReader br = new BufferedReader(new FileReader("jugadores.csv"));
		        BufferedWriter bw = new BufferedWriter(new FileWriter(equipo + ".csv"));
		    ) {
		        linea = br.readLine();	        
		        while (linea != null) {
		            matriz = linea.split(",");
		            
		            if(!equipo.containsKey(matriz[7])) {//verificar si está contenida la clave
		            	equipo.put(matriz[7],new ArrayList<String>()); // creamos el arrayList en blanco 
		            }
		            ArrayList<String> nuevo= equipo.get(matriz[7]); //si existe o no exite el equipo accedemos al arraylist
		            nuevo.add(linea);
		            equipo.put(matriz[7], nuevo); //guarda el nuevo
		            linea = br.readLine();		            
		        }

		    } catch (FileNotFoundException ex) {
		        System.out.print("Fichero no encontrado");
		    } catch (IOException ex) {
		        System.out.println("Error al leer o escribir el archivo: " + ex.getMessage());
		    }
			/**************************************************************************************/
			//RECORRER HASPMAP
			String equipos;
			ArrayList<String> integrantes;
			
			for (Entry<String, ArrayList<String>> eq : equipo.entrySet()) {
				
				equipos = eq.getKey(); //nombre del equipo
				integrantes = eq.getValue();
				
				try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(equipos+".csv")); ) {
					for (String inte : integrantes) { //recorro
						bw1.write(inte+"\n");
					}
				} catch (FileNotFoundException ex) {
					System.out.print("Fichero no encontrado");
				} catch (IOException ex) {
					System.out.print(ex.getMessage());
				}
			}
	}
	public static void leerArchivoEquipo() throws FileNotFoundException, IOException {
	    String matriz[];
	    System.out.print("Dime el nombre del equipo:");
	    String equipo = sc.nextLine();
	    String linea = "";

	    try (
	        BufferedReader br = new BufferedReader(new FileReader("jugadores.csv"));
	        BufferedWriter bw = new BufferedWriter(new FileWriter(equipo + ".csv"));
	    ) {
	        linea = br.readLine();	        
	        while (linea != null) {
	            matriz = linea.split(",");
	            
	            if (matriz.length > 7) {
	                if (matriz[7].compareToIgnoreCase(equipo) == 0) {
	                    bw.write(linea + "\n");
	                }
	            }
	            
	            linea = br.readLine();
	        }

	    } catch (FileNotFoundException ex) {
	        System.out.print("Fichero no encontrado");
	    } catch (IOException ex) {
	        System.out.println("Error al leer o escribir el archivo: " + ex.getMessage());
	    }
	}


}