package principal;

import clases.Cuenta;
import clases.Hilo;

public class Inicio {
	public static final int EUROS = 100;
	public static void main(String[] args) {
		Cuenta cuenta = new Cuenta(EUROS);
		Hilo [] hilos = new Hilo[240];
		int idx = 0;
		for (int i = 0; i < 40; i++) hilos[idx++] = new Hilo("Hilo " + (idx +1), 100, true, cuenta);
		for (int i = 0; i < 20; i++) hilos[idx++] = new Hilo("Hilo " + (idx +1), 50, true, cuenta);
		for (int i = 0; i < 60; i++) hilos[idx++] = new Hilo("Hilo " + (idx +1), 20, true, cuenta);
		for (int i = 0; i < 40; i++) hilos[idx++] = new Hilo("Hilo " + (idx +1), 100, false, cuenta);
		for (int i = 0; i < 20; i++) hilos[idx++] = new Hilo("Hilo " + (idx +1), 50, false, cuenta);
		for (int i = 0; i < 60; i++) hilos[idx++] = new Hilo("Hilo " + (idx +1), 20, false, cuenta);
		
		for (Hilo hilo : hilos) {
			hilo.start();
		}
		for (Hilo hilo : hilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(cuenta.getDinero());
	}
}
