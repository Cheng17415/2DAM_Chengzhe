package primero;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import clase.Persona;

public class eje9 {
	/*Ejercicio dni/persona*/
	static Scanner scanner = new Scanner(System.in);
    
	public static void listar(HashMap<String, Persona> a) {
		for(Entry<String, Persona> item:a.entrySet()) {
			System.out.println(item.getKey()+ " " + item.getValue());
		}
	}
	public static Persona nueva() {
		String nombre, apellidos;
		int edad;
		System.out.println();
		
		System.out.print("Dime el nombre del alumno: ");
		nombre = scanner.nextLine();
		
		System.out.print("Dime el apellido del alumno: ");
		apellidos = scanner.nextLine();
		
		System.out.print("Dime la edad: ");
		edad = Integer.valueOf(scanner.nextLine());
		return new Persona(nombre, apellidos, edad);
		
	}
	
	public static void modificar(HashMap<String, Persona> a, String dni, Persona p) {
		String nombre, apellidos;
		int edad;
		System.out.println();
		
		System.out.print("Dime el nombre del alumno: ");
		nombre = scanner.nextLine();
		
		System.out.print("Dime el apellido del alumno: ");
		apellidos = scanner.nextLine();
		
		System.out.print("Dime la edad: ");
		edad = Integer.valueOf(scanner.nextLine());
		if(!(p.getNombre().equals(nombre) && p.getApellidos().equals(apellidos) && p.getEdad()== edad)) {
			a.put(dni, new Persona(nombre, apellidos, edad));
		}
	}
	
	public static void tratamiento(HashMap<String, Persona> a) {
		String dni, dniNuevo, nombre, apellidos;
		int edad;

		System.out.print("Indique el dni a consultar: ");
		dni = scanner.nextLine();
		if(!a.containsKey(dni)) {
			a.put(dni, nueva());
		}else {
			modificar(a, dni, a.get(dni));
		}
	}
	
	public static void main(String[] args) {
		HashMap<String, Persona> alumnos = new HashMap<String, Persona>();

		alumnos.put("1234D", new Persona("Jose", "Ruiz", 20));
		alumnos.put("4567Q", new Persona("Maria", "Perez", 22));
		alumnos.put("7894A", new Persona("Ester", "Diaz", 30));	
		//listar(alumnos);
		//consultarDni(alumnos);
		tratamiento(alumnos);
		listar(alumnos);
	}
	/*Metodo que pase hashmap, pida dni si no existe, que nos pida datos de la persona a inluir. Y si existe nos de la opcion de 
	 * modificar algunos datos que ya tenemos*/
	
}
