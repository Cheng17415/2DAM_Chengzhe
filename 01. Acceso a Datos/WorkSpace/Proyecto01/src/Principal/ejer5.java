package primero;

import java.util.Scanner;

public class ejer5 {

	/* Pedir un numero por pantalla y sumar un año. Y quiero saber si es viciesto o no* */
	static Scanner scanner = new Scanner(System.in);
	
	public static boolean bisiestos(int a) {		
		return (a % 4 == 0 && a% 100 != 0) || (a % 400 == 0);
	}
	
	public static void main(String[] args) {
		System.out.print("Ingrese un número de año: ");
		int año = Integer.valueOf(scanner.nextLine());
		//System.out.printf("El valor es: ", bisiestos(año));
		System.out.println(bisiestos(año) ? "Bisiesto" : "No bisiesto");
	}
}
