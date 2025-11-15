package ejercicios;

import java.util.Scanner;

public class ej01 {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Introduzca un número entero desde el 1 al 12");
		int mes = Integer.valueOf(sc.nextLine());
		String trimestre, semestre;

		while (mes < 1 || mes > 12) {
			System.out.println("Introduzca un número válido");
			mes = Integer.valueOf(sc.nextLine());
		}

		/*if (mes >= 1 && mes <= 3) {
			trimestre = "1 trimestre";
			semestre = "1 semestre";
		} else if (mes > 3 && mes <= 6) {
			trimestre = "2 trimestre";
			semestre = "1 semestre";
		} else if (mes > 6 && mes <= 9) {
			trimestre = "3 trimestre";
			semestre = "2 semestre";
		} else {
			trimestre = "4 trimestre";
			semestre = "2 semestre";
		}*/
		trimestre = (int)(mes - 1) / 3 + 1 + " trimestre"; 
		semestre =	(int)(mes - 1) / 6 + 1 + " semestre";
		
		System.out.printf("El mes %2d pertenece al %10s y al %10s",mes, trimestre, semestre);
	}

	// pedir por pantalla un número del año
	// Trimestre al que pertenece y cúal es su semestre
	
	//Dado un numero entero 
}
