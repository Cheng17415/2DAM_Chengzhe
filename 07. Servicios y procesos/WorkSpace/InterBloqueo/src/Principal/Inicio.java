package Principal;

import Clases.Cuenta;
import Clases.Hilo;

public class Inicio {
	public static void main(String[] args) {
		/*Simular transferencias de un gestor de una cuenta a otra.*/
		Cuenta c1 = new Cuenta("11111",12500);
		Cuenta c2 = new Cuenta("22222",23400);
		
		System.out.printf("Saldo Inicial c1 = %d c2 = %d\n",c1.getSaldo(),c2.getSaldo());
		
		Hilo h1 = new Hilo(c1,c2,"H1");
		Hilo h2 = new Hilo(c2,c1,"H2");
		
		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.printf("Saldo final c1 = %d c2 = %d\n",c1.getSaldo(),c2.getSaldo());
	}
}
