package principal;

import clases.Cuenta;
import clases.Hilo;

public class Inicio {
	public static final int EUROS = 100;
	public static void main(String[] args) {
		/*Un banco quiere simular operaciones de entrada y salida de dinero a la vez
		 *y que al final de las operaciones tengan el mismo dinero del que empezó */
		Cuenta cuenta = new Cuenta(EUROS);
		Hilo [] hilos = new Hilo[240];
		int idx = 0;
		for (int i = 0; i < 60; i++) {
			if(i < 20) {
				hilos[idx++] = new Hilo("Hilo " + (idx +1), 50, true, cuenta);
				hilos[idx++] = new Hilo("Hilo " + (idx +1), 50, false, cuenta);
			}
			if(i < 40) {
				hilos[idx++] = new Hilo("Hilo " + (idx +1), 100, true, cuenta);
				hilos[idx++] = new Hilo("Hilo " + (idx +1), 100, false, cuenta);
			}
			hilos[idx++] = new Hilo("Hilo " + (idx +1), 20, true, cuenta);
			hilos[idx++] = new Hilo("Hilo " + (idx +1), 20, false, cuenta);
		}
		
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
