package ejercicios;

import java.util.Scanner;

public class PiramideIzquierda {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime la cantidad de niveles de una pirámide");
		int niveles = Integer.parseInt(sc.nextLine());
		for (int i = 0; i <= niveles/2+1; i++) {
			System.out.println(" ".repeat(niveles/2+1 - i) + "*".repeat(i));
		}
		for (int i = niveles/2; i > 0; i--) {
			System.out.println(" ".repeat(niveles/2+1 - i) + "*".repeat(i));
		}
		sc.close();
	}
}
