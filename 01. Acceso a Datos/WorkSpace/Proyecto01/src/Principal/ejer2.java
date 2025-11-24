package primero;

import java.util.Scanner;

public class ejer2 {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		  System.out.print("Dime un número de mes [1-12]: "); 
		  int mes = Integer.valueOf(sc.nextLine());
		  int tri = (int)((mes-1)/3)+1;
		  int sem = (int)((mes-1)/6)+1;
		  System.out.printf("El mes %d pertenece al trimestre %d y al semestre %d" + mes, tri, sem);
		  /*GENEERAR NRO ALEATORIO ENTRE 100 Y 500, Y NOS DIGA EN QUE DECENA ESTÁ*/
	}
}
