package primero;

import java.util.Scanner;

import clase.Biblioteca;

public class ejer10 {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String basura;
		int opcion;
		Boolean seguir= true;
		String[] opciones = { "1.Suma", "2.Resta", "3.Salir" };
		
		while(seguir) {
		opcion = Biblioteca.menu(sc, opciones);
       		
		switch (opcion) {
		case 1: 
			System.out.println("Quiero sumar");
			break;
		case 2: 
			System.out.println("Quiero restar");
			break;
		case 3: 
			seguir=false;
			break;
		default:
			break;
		}
		if(opcion!=3) {
			System.out.print("Pulse la tecla para continuar");
			basura=sc.nextLine();
		}
	 }
   }
}
