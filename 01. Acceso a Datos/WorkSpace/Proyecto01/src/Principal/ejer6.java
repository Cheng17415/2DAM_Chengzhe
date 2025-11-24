package primero;

import java.util.Calendar;
import java.util.Scanner;

public class ejer6 {

	/*
	 * Pedir el nro de mes y el año. Y nos tiene que decir el nombre de mes y el nro
	 * de dias que tiene ese mes ese año
	 */
	static Scanner scanner = new Scanner(System.in);
	
	public static boolean bisiestos(int a) {		
		return (a % 4 == 0 && a% 100 != 0) || (a % 400 == 0);
	}
	
	public static void main(String[] args) {
		String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octure", "Noviembre", "Diciembre"};
			
		System.out.print("Ingrese un número de mes del 1 al 12: ");
		int mes = Integer.valueOf(scanner.nextLine());
		
		System.out.print("Ingrese el año: ");
		int año = Integer.valueOf(scanner.nextLine());		

		int ndias;
		switch (mes) {
		case 1: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 12: 
			ndias=31;
			break;
		case 2:
			ndias= bisiestos(año) ? 29 : 28;
		default:
			ndias=30;
			break;
		}
		System.out.printf("Mes indicado es: " + meses[mes - 1] + ", el año indicado es: "+ año + ", Cantidad de dias del mes es:  " + ndias);
	}

}
