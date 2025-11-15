package ejercicios;

import java.util.Scanner;

import Clases.Biblioteca;

public class Ejercicio10 {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		boolean seguir = true;
		String[] opciones = {"1. Suma","2. Resta","3. Salir"};
		
		while(seguir) {
			int opcion = Biblioteca.menu(sc, opciones);
			switch(opcion) {
			case 1: //Suma
				System.out.println("Quiero sumar");
				break;
			case 2: //Resta
				System.out.println("Quiero restar");
				break;
			case 3:
				System.out.println("Fin del programa");
				seguir = false;
				break;
			}
			if(opcion != 3) {
				System.out.println("Pulse enter para continuar...");
				sc.nextLine();
			}
		}
	}

}
