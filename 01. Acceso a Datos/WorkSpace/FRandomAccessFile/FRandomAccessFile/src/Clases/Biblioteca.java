package Clases;

import java.util.Scanner;

public class Biblioteca {

	public static int menu(Scanner sc, String[] opciones) {
	    int op = -1;
	    boolean valido = false;

	    do {
	        for (String item : opciones) {
	            System.out.println(item);
	        }

	        System.out.print("Dime la opción a realizar: ");

	        try {
	            op = Integer.parseInt(sc.nextLine());
	            if (op >= 1 && op <= opciones.length) {
	                valido = true;
	            } else {
	                System.out.println("Opción fuera de rango. Inténtalo de nuevo.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada no válida. Introduce un número entero.");
	        }

	    } while (!valido);

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
	
	public static int alea(int li,int ls) {
		return (int)(Math.random()*(ls-li+1))+li;
	}
	public static boolean esPrimo(int n) {
		//Un nùmero es primo si solo es divisible entre 1 y si mismo.
		for(int i=2;i<=n-1;i++) {
			if (n%i==0)return false;
		}
		return true;
		
	}
}
