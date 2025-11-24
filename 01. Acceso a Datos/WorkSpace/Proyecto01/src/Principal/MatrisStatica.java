package primero;

import java.util.Scanner;

public class MatrisStatica {
	/*
	 * Matrices estaticas Pedir por pantalla el nro de filas y el nro de columnas.
	 * Crear una matriz bidimencional con esas dimensiones y la rellene con nros
	 * aleatorios de cero a 10. Imprimir la matriz generada y que se incluidas la
	 * suma por columnas y suma por filas.
	 */
	public static Scanner sc = new Scanner(System.in);
	
	public static int alea(int li, int ls) {
		return (int)Math.random() *(ls-li+1)+li;
	}
	
	public static void main(String[] args) {
		System.out.print("Ingrese el número de columnas: ");
		int nroColumnas = Integer.valueOf(sc.nextLine());
		
		System.out.print("Ingrese el número de filas: ");
		int nroFilas = Integer.valueOf(sc.nextLine());
		
        int[][] matriz = new int[nroFilas][nroColumnas];
        for (int i = 0; i < nroFilas; i++) {
            for (int j = 0; j < nroColumnas; j++) {
                matriz[i][j] = alea(nroFilas, nroColumnas);
            }
        }

        System.out.println("\nMatriz generada con suma por filas:");
        int[] sumaColumnas = new int[nroColumnas]; 

        for (int i = 0; i < nroFilas; i++) {
            int sumaFila = 0;
            for (int j = 0; j < nroColumnas; j++) {
                System.out.printf("%4d", matriz[i][j]);
                sumaFila += matriz[i][j];
                sumaColumnas[j] += matriz[i][j];
            }
            System.out.printf(" | %4d (Suma Fila %d)%n", sumaFila, i + 1);
        }
        System.out.println("_____".repeat(nroColumnas + 1));
        for (int j = 0; j < nroColumnas; j++) {
            System.out.printf("%4d", sumaColumnas[j]);
        }
        System.out.printf(" | Suma por columnas%n");
	}

}
