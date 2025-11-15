package Principal;

import Clases.Almacen;
import Clases.Hilo;

public class principal {
	public static final int CLIENTES = 300;
	public static final int INTENTOS = 10;
	public static final int UNIDADES = 100;
	public static void main(String[] args) {
		
		Almacen almacen = new Almacen(UNIDADES);
		Thread [] hilos = new Thread[CLIENTES];
		for (int i = 0; i < CLIENTES; i++) {
			hilos[i] = new Thread(new Hilo("Hilo " + (i + 1), INTENTOS, almacen));
		}
		
		for (int i = 0; i < CLIENTES; i++) {
			hilos[i].start();
		}
		
		for (Thread h : hilos) {
			try {
				h.join();
			} catch (InterruptedException ex) {}
		}
		System.out.println("Se acabó");
		System.out.println("Quedan " + almacen.getUnidades() + " unidades");
	}
}
