package principal;

import java.util.Date;

import clases.Cajera;
import clases.Cliente;

public class Principal {

	public static void main(String[] args) {
		//Generar entre 20 y 50 clientes
		final int NCLIENTES = alea(20,50);
		final int NCAJERA = 5;
		Cliente [] clientes = new Cliente[NCLIENTES];
		Cajera [] cajeras = new Cajera[NCAJERA];
		
		//Generar clientes
		for (int i = 0; i < clientes.length; i++) {
			//Cada cliente puede tener entre 5 y 10 productos.
			clientes[i] = new Cliente("Cl-" + (i+1), alea(5,10));
		}
		
		//Generar cajeras
		for (int i = 0; i < cajeras.length; i++) {
			cajeras[i] = new Cajera("Ca-" + (i+1), clientes);
		}
		
		//Para calcular el tiempo que tardan las cajeras en atender a todos los clientes.
		Date d1 = new Date();
		for (Cajera cajera : cajeras) {
			cajera.start();
		}
		for (Cajera cajera : cajeras) {
			try {
				cajera.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Date d2 = new Date();
		long ms = (d2.getTime() - d1.getTime());
		System.out.println("Han terminado todas las cajeras. El sistema ha tardado " + ms/1000.0 + " segundos");
	}
	
	public static int alea(int li,int ls) {
		return (int)(Math.random()*(ls-li +1)) +li;
	}
}
