package Clases;

import java.util.Scanner;

public class Biblioteca {

	public static int menu(Scanner sc,String[] opciones) {
		int op;
		do {
			for(String items: opciones)System.out.println(items);
			System.out.print("Dime la opcion a realizar ");
			op=Integer.valueOf(sc.nextLine());
		}while(op<1||op>opciones.length);
		return op;
		
	}
	public static double redondear(double numero,int decimales) {
		//10.24567,2
		int exponente=(int)Math.pow(10, decimales);
		return (double)Math.round(numero*exponente)/exponente;
	}
	public static double truncar(double numero,int decimales) {
		//10.24567,2
		double exponente=Math.pow(10, decimales);
		return (int)(numero*exponente)/exponente;
	}
	
	
}
