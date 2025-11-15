package primerosHilos;

import java.util.Date;
import java.util.Scanner;

public class Ejer3 {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		//Pedimos numero de hilos.
		//Crear tantos hilos como los que tenemos.
		//Saber cuánto tarda y obtener el máximo
		int nhilosTotales = Runtime.getRuntime().availableProcessors();
		int nhilo;
		int [] v = new int[500_000_000];
		System.out.println("Incio carga de matriz");
		for (int i = 0; i < v.length; i++) {
			v[i] = alea(0,2_000_000);
		}
		System.out.println("Fin de carga de matriz");
		
		do {
			System.out.println("¿Cuántos hilos quieres ejecutar? Hay un total de " + nhilosTotales);
			nhilo = Integer.valueOf(sc.nextLine());
		} while(nhilo > nhilosTotales);
	
		Hilo [] hilos = new Hilo [nhilo];
		//Cantidad de elementos de v[] que tiene que coger cada hilo
		int cantidad = v.length / nhilo;
		
		//Inicializar hilos
		for(int i = 0; i <hilos.length; i++) {
			hilos[i] = new Hilo();
			hilos[i].fijarRango(cantidad * i, cantidad *(i+1) - 1, v);
			//hilos[i].start();
		}
		
		Date d1 = new Date();
		for(int i = 0; i <hilos.length; i++) hilos[i].start();
	
		//Comprobar que todos los hilos terminen
		for(int i = 0; i< hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int mayor = hilos[0].may;
		for (int i = 1; i< hilos.length; i++) {
			if(hilos[i].may > mayor) mayor = hilos[i].may;
		}
		Date d2 = new Date();
		long ms = (d2.getTime() - d1.getTime());
		System.out.println("Los " + nhilo + " hilos han tardado " + ms + " milisegundos");
		System.out.println("El mayor es " + mayor);
		
	}
	public static int alea(int li, int ls) {
		return (int) (Math.random() * (ls - li + 1)) + li;
	}
}

class Hilo extends Thread{
	int [] v;
	int ini,fin;
	int may;
	
	void fijarRango(int i, int f, int[] v) {
		this.ini = i;
		this.fin = f;
		this.v = v;
	}
	public void run() {
		System.out.println("Empezando hilo ");
		may = v[ini];
		
		for(int f= ini +1; f <=fin; f++) {
			if(v[f] > may) may = v[f];
		}
		System.out.println("Fin hilo ");
	}
	
}