package ejercicios;

import java.util.Scanner;

public class Ejercicio3 {

	public static void main(String[] args) {
		// Divisible entre 4, no son entre 100 a menos que también entre 400
		Scanner sc = new Scanner(System.in);
		System.out.println("Escriba un año");
		int dato = Integer.valueOf(sc.nextLine());
		System.out.println(bisiesto(dato) ? "Bisiesto": "No bisiesto");
		sc.close();

	}
	public static boolean bisiesto (int num) {	
		return (num % 4 == 0 && num % 100 != 0)||num % 400 == 0;
	}

}
