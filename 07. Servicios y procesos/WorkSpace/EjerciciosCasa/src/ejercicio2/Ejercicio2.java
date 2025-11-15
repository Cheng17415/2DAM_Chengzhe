package ejercicio2;

import java.util.Random;

public class Ejercicio2 {
	public static Random r = new Random();
	public static void main(String[] args) {
			int numAlea = r.nextInt(401) + 100;
			int decena = (numAlea - 1)/10 + 1;
			System.out.printf("El número generado es %d y está en la decena %d", numAlea, decena);
			System.out.println("");
	}

}
