package ejercicios;

import java.util.Scanner;

import Clases.Biblioteca;

public class Ejercicio14 {
	public static Scanner sc = new Scanner(System.in);
	/*Pedir por pantalla filas y columnas
	Crear matriz bidimensional con esos datos rellenando datos de 0 a 10
	Se pretende imprimir la matriz generada y que incluya el
	valor de la suma por fila y por columna*/
			
	public static void main(String[] args) {
		
		System.out.println("¿Cuántas filas quieres que tenga la matriz?");
		int filas = Integer.valueOf(sc.nextLine());
		System.out.println("¿Cuántas columnas quieres que tenga la matriz?");
		int columnas = Integer.valueOf(sc.nextLine());
		int [][] matriz = new int [filas][columnas];
		int [] sfila = new int [filas];
		double sumafinal = 0;
		double [] mediacol = new double [columnas];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = Biblioteca.alea(0,10);
				sfila [i] += matriz[i][j];
				sumafinal += matriz[i][j];
				System.out.printf("%5d",matriz[i][j]);
			}
			System.out.println();
		}
		System.out.println("La media final es de: " + sumafinal/(filas*columnas));
		System.out.println("La suma de las filas es de: ");
		for (int i = 0; i < matriz.length; i++) {
			System.out.print(sfila[i] + "  ");		
		}
		System.out.println("\nLa media de las filas es de: ");
		for (int i = 0; i < matriz.length; i++) {
			System.out.printf("%5.2f",(double)sfila[i]/matriz[i].length);		
		}
			
		
		for (int i = 0; i < matriz[0].length; i++) {
			int scolumna = 0;
			
			for (int j = 0; j < matriz.length; j++) {
				scolumna += matriz[j][i];
				
			}
			mediacol[i] = scolumna/matriz.length;
			System.out.print("\nLa suma de la " + (i+1) + " columna es de " + scolumna);
		}
		System.out.println("\nLa media de las columnas es de: ");
		for (int i = 0; i < mediacol.length; i++) {
			System.out.printf("%.2f  ",mediacol[i]);		
		}
		
		
	}
	

}
