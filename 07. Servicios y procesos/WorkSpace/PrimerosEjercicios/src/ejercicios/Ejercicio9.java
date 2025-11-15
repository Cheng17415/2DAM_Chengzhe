package ejercicios;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import Clases.Persona;

public class Ejercicio9 {
	//Ejercicio HashMap <String,Persona> DNI acceder a persona
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		HashMap <String,Persona> dNIPersona = new HashMap<>();
		buscarDNI(dNIPersona);
		parseDNI(dNIPersona);
	}
	
	public static void buscarDNI(HashMap <String, Persona> dNIPersona) {
		int temp = -1;
		
		do {
			System.out.println("Introduzca DNI a buscar: ");
			String dNI = sc.nextLine().toUpperCase();
			
			if (dNIPersona.containsKey(dNI)) {
				System.out.println(dNI + ": " + dNIPersona.get(dNI));
				System.out.println("1. Modificar DNI  2. Modificar Datos Persona  0. Salir");
				int temp2 = Integer.valueOf(sc.nextLine());
				switch (temp2){
				case 1:
					modificarDNI(dNIPersona, dNI);
					break;
				case 2:
					modificarDatos(dNIPersona,dNI);
					break;
				default:
					break;
				}
			} else {
				dNIPersona.put(dNI, pNueva());
			}
			System.out.println("Pulse 0 para salir");
			System.out.println("Pulse 1 para continuar");
			temp = Integer.valueOf(sc.nextLine());
			
		} while(temp != 0);
		
	}
	public static void parseDNI(HashMap <String, Persona> dNIPersona) {
		for(Entry<String, Persona> e:dNIPersona.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}
	
	public static void modificarDNI(HashMap <String, Persona> dNIPersona, String dNI) {
		System.out.println("Introduzca nuevo DNI");
		String dNINuevo = sc.nextLine().toUpperCase();
		dNIPersona.put(dNINuevo, dNIPersona.get(dNI));
		dNIPersona.remove(dNI);
		System.out.println("Actualizado con éxito");
		
	}
	public static void modificarDatos(HashMap <String, Persona> dNIPersona, String dNI) {
		System.out.println("Introduzca el nombre actualizado de la persona");
		String nombre = sc.nextLine();
		System.out.println("Introduzca el apellido actualizado de la persona");
		String apellido = sc.nextLine();
		System.out.println("Introduzca la edad actualizada de la persona");
		int edad = Integer.valueOf(sc.nextLine());
		dNIPersona.replace(dNI, new Persona(nombre,apellido, edad));
		System.out.println("Actualizado con éxito");
	}
	
	public static Persona pNueva() {
		System.out.println("Este DNI no existe todavía");
		System.out.println("Introduzca el nombre de la persona");
		String nombre = sc.nextLine();
		System.out.println("Introduzca el apellido de la persona");
		String apellido = sc.nextLine();
		System.out.println("Introduzca la edad de la persona");
		int edad = Integer.valueOf(sc.nextLine());
		return new Persona(nombre, apellido,edad);
	}
}
