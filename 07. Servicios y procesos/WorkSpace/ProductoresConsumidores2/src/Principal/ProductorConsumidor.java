package Principal;

import Clases.Buffer;
import Clases.Consumidor;
import Clases.Productor;

public class ProductorConsumidor {
	/*Tenemos un buffer que puede guardar hasta 10 productos. Si esta vacio, espera.
	 * Tenemos un consumidor que consume. Si no hay nada para consumir, espera.*/
	public static void main(String[] args) {
		Buffer b = new Buffer(10);
		(new Productor(b)).start();
		(new Consumidor(b)).start();
	}
}
