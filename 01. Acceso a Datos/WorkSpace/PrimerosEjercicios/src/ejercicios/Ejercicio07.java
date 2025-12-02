package ejercicios;
import java.util.ArrayList;

import Clases.Persona;
public class Ejercicio07 {
	public static void main(String[] args) {
		ArrayList <Persona> personas = new ArrayList<>();
		Persona p1 = new Persona("Pepe","Rodriguez",22);
		Persona p2 = new Persona("Lola", "Mendez",25);
		Persona p3 = new Persona("Pope", "Ye", 50);
		personas.add(p1);
		personas.add(p2);
		personas.add(p3);
		for (Persona persona : personas) {
			System.out.println(persona);
		}
		int edad = buscarEdad(personas, "pepe", "rodriguez");
		System.out.println(edad == -1 ? "No existe esa persona": "Esa persona tiene" + edad);
	}
	//Crear metodo, pasar ArrayList, nombre y apellido y nos devuelve si existe la edad
	public static int buscarEdad(ArrayList<Persona> personas, String nombre, String apellido) {
		for (Persona persona : personas) {
			if(persona.getNombre().compareToIgnoreCase(nombre) == 0 && persona.getApellido().compareToIgnoreCase(apellido) == 0) {
				return persona.getEdad();
			}
		}
		return -1;
	}
}
