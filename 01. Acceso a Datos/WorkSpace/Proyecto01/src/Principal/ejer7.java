package primero;

import java.util.ArrayList;
import clase.Persona;

public class ejer7 {

	public static void main(String[] args) {
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		listaPersonas.add(new Persona("Maria", "Perez", 22));
		listaPersonas.add(new Persona("Julio", "Lopez", 25));
		listaPersonas.add(new Persona("Mario", "Díaz", 30));
		
		for (Persona p : listaPersonas) {
			//System.out.println(p);
		}
		int n = devolverEdad(listaPersonas, "Julio", "Lopez");
		if(n==1) {
			System.out.print("La persona no existe");
		}
		else {
			System.out.println("La persona existe y su edad es: "+ n);
		}
		
	}
	
	/*Array list nombre y los apellidos de una persona, nos tiene que devolver si existe la edad*/
	public static int devolverEdad(ArrayList<Persona> listaPersonas, String nombre, String apellido) {
		for (Persona persona : listaPersonas) {
			if (persona.getNombre().compareToIgnoreCase(nombre) == 0 && persona.getApellidos().compareToIgnoreCase(apellido) == 0 ) 
				//System.out.println("La persona existe y su edad es: " + persona.getEdad());
				return persona.getEdad();				
		}return -1;
	}	
}
