package ejercicios;

import java.util.Scanner;

public class Ejercicio01 {

	public static Scanner sc = new Scanner(System.in);

	// Pedir por pantalla un número del año
	// Trimestre al que pertenece y cúal es su semestre
	public static void main(String[] args) {
		System.out.println("Introduzca un número entero desde el 1 al 12");
		int mes = Integer.valueOf(sc.nextLine());
		String trimestre, semestre;

		while (mes < 1 || mes > 12) {
			System.out.println("Introduzca un número válido");
			mes = Integer.valueOf(sc.nextLine());
		}
		trimestre = (int)(mes - 1) / 3 + 1 + " trimestre"; 
		semestre =	(int)(mes - 1) / 6 + 1 + " semestre";
		
		System.out.printf("El mes %2d pertenece al %10s y al %10s",mes, trimestre, semestre);
	}
}
