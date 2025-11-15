package Principal;

import Clases.Buffer;
import Clases.Consumidor;
import Clases.Productor;

public class ProductorConsumidor {
	public static void main(String[] args) {
		Buffer b = new Buffer(10);
		(new Productor(b)).start();
		(new Consumidor(b)).start();
	}
}
