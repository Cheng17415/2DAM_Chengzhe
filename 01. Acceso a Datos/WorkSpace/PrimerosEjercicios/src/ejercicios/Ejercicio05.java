package ejercicios;

import java.util.Scanner;

public class Ejercicio05 {
	public static void main(String[] args) {
		//Pedir por pantalla el número de mes y año
		//Nombre del mes y cuántos días tiene ese mes en ese año
		Scanner sc = new Scanner(System.in);
		String[] nombre = {"Enero","Febrero","Marzo","Abril", "Mayo","Junio","Julio","Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		System.out.println("Introduzca el mes ");
		int mes = Integer.valueOf(sc.nextLine());
		System.out.println("Introduzca el año");
		int anio = Integer.valueOf(sc.nextLine());
		System.out.printf("El mes %d es %s y tiene %d días", mes, nombre[mes-1], dias(mes, anio));
		sc.close();
	}
	
	public static int dias(int mes, int anio) {
		switch(mes) {
		case 1,3,5,7,8,10,12:
			return 31;
		case 4,6,9,11:
			return 30;
		case 2:
			return bisiesto(anio) ? 29: 28;
		default:
			return 0;
		}
	}

	public static boolean bisiesto (int num) {	
		return (num % 4 == 0 && num % 100 != 0)||num % 400 == 0;
	}
}
