package ejercicios;

import java.util.Scanner;

public class piramidaDerecha {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime la cantidad de niveles de una pirámide");
		int niveles = Integer.parseInt(sc.nextLine());
		for (int i = 1; i <= niveles/2 +1; i++) {
			System.out.print("*".repeat(i) + "\n");
		}
		for (int i = niveles/2 ; i > 0; i--) {
			System.out.print("*".repeat(i) + "\n");
		}
		sc.close();
	}
	/*	*
	 *  **
	 *  ***
	 *  ****
	 *  ***
	 *  **
	 *  *
	 * */
}
