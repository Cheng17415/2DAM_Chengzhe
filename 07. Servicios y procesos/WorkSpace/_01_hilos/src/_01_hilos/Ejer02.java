package _01_hilos;

import java.util.Date;


public class Ejer02 {
	//en una matriz de 500-000-000. Random cada num, buscar el máximo
	//En dos hilos, la mitad de ellos, buscar el máximo y al final del hilo comparar entre esos dos
	public static void main(String[] args) {
		
		System.out.println("Cantidad de nucleos del procesador:" +
				Runtime.getRuntime().availableProcessors());
		
		int [] v = new int [500_000_000];
		
		System.out.println("Incio carga de matriz");
		for (int i = 0; i < v.length; i++) {
			v[i] = alea(0,2_000_000);
		}
		System.out.println("Fin de carga de matriz");
		
		System.out.println("Empezando hilo principal");
		Date d1 = new Date();
		int may = v[0];
		
		for(int i = 1; i < v.length; i++) {
			if(v[i] > may) may = v[i];
		}
		
		Date d2 = new Date();
		long ms = (d2.getTime() - d1.getTime());
		System.out.println("Un hilo ha tardado " + ms + " milisegundos");
		System.out.println("Fin hilo principal");
		d1 = new Date();
		
		HiloMayor h1 = new HiloMayor();
		h1.fijarRango(0, v.length/2 ,v );
		
		HiloMayor h2 = new HiloMayor();
		h2.fijarRango(v.length/2 +1, v.length-1 ,v );
		
		h1.start();
		h2.start();
		
		while(h1.isAlive() || h2.isAlive());
		
		System.out.println("Mayor elemento del vector:");
		if(h1.may > h2.may) {
			System.out.println(h1.may);
		}else {
			System.out.println(h2.may);
		}
		d2 = new Date();
		ms = (d2.getTime() - d1.getTime());
		System.out.println("2 hilos han tardado " + ms + " milisegundos");

	}
	
	public static int alea(int li, int ls) {
		return (int) (Math.random() * (ls - li + 1)) + li;
	}

}

class HiloMayor extends Thread{
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
		
		for(int f= ini +1; f <fin; f++) {
			if(v[f] > may) may = v[f];
		}
		System.out.println("Fin hilo ");
	}
}

