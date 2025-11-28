package ejercicio1;

import java.util.Random;

public class Ejercicio1 {
	public static Random r = new Random();
	public static void main(String[] args) {
		//Generar un numero aleatorio y saber en que decena está.
		int numAlea = r.nextInt(401) + 100;
		int decena = (numAlea - 1)/10 + 1;
		System.out.printf("El número generado es %d y está en la decena %d", numAlea, decena);
		System.out.println("");
	}

}
