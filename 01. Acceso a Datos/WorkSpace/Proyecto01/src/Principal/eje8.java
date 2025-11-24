package primero;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class eje8 {
	/* Pedimos la palabra en español */
	/*
	 * Realizar un diccionario de castellano ingles utilizar hashmap si no existe lo
	 * metemos en el hashmap clave[español]:valor[ingles]
	 */
	public static Scanner scanner = new Scanner(System.in);
	
	public static void listar(HashMap<String, String> t) {
		for(Entry<String, String> m:t.entrySet()){
			System.out.printf("castellano=% ingles=%s\n", m.getKey());
		}
	}
	public static void main(String[] args) {
		HashMap<String, String> traductor = new HashMap<String, String>();

		traductor.put("Hola", "Hello");
		traductor.put("Como estas?", "How are you?");
		traductor.put("Piña", "Pineapple");
		traductor.put("Naranja", "Orange");
		traductor.put("Casa", "Home");
		traductor.put("Pared", "Wall");

		
		String castellano, ingles, respuesta;
		while(true) {
			System.out.print("Indique la palabra a traducir: ");
			castellano=scanner.nextLine();
			if(!traductor.containsKey(castellano)) {
				System.out.println("Dime la traducción ");
				ingles = scanner.nextLine();
				traductor.put(castellano, ingles);
			}
			else {
				System.out.println("Traducción " + traductor.get(castellano));
			}
			System.out.println("¿Quiere continuar ?");
			respuesta = scanner.nextLine();
			if(respuesta.compareToIgnoreCase("N")==0)break;
		}
		listar(traductor);
		
		// System.out.println(traductor.get(pal));

		/*
		 * for (String i : traductor.keySet()) System.out.println("Clave: " + i + " - "+
		 * " valor: " + traductor.get(i)); 
		 * for(String c:traductor.values())System.out.println(c); 
		 * for(java.util.Map.Entry<String, String> c:traductor.entrySet()) System.out.println(c.getKey()+ " " +
		 * c.getValue()); if(traductor.containsKey("Azul")){ traductor.put("Azul",
		 * "Blue"); }
		 */
		/*System.out.println("*******************************************");

		if (traductor.containsKey(pal)) {
			System.out.println(pal + " está dentro de la la lista");
		} else {
			System.out.println(pal + " no está dentro de la la lista.");
			while (true) {
				System.out.print("¿Desea ingresar la traducción para " + pal + "? (sí/no): ");
                String respuesta = scanner.next();
                
                if (respuesta.equalsIgnoreCase("sí")) {
                    System.out.print("Por favor, ingrese la traducción para " + pal + ": ");
                    String traduccion = scanner.next();
                    
                    traductor.put(pal, traduccion);
                    System.out.println("Se ha agregado " + pal + " a la lista con la traducción: " + traduccion);
                    break; 
                } else if (respuesta.equalsIgnoreCase("no")) {
                    System.out.println("No se ha agregado la palabra.");
                    break; 
                } else {
                    System.out.println("Por favor, ingrese 'sí' o 'no'.");
                }
            }
		}*/
	}
}


