package principal;

import clases.Carrera;
import clases.Liebre;
import clases.Tortuga;

public class Principal {
	public static void main(String[] args) {
		final int META = 70;
		Carrera c = new Carrera(META);
		System.out.println("Comienza la carrera");
		Tortuga t = new Tortuga("Trotuman",c);
		Liebre l = new Liebre("Libre", c);
		t.start();
		l.start();
		try {
			t.join();
			l.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ha habido ganador");
	}
}
