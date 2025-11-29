package principal;

import clases.Buffer;
import clases.Consumidor;
import clases.Productor;

public class Principal {
	public static void main(String[] args) {
		final int CAPACIDADBUFFER = 6;
		final int CAPACIDADPRODUCIR = 15;
		final int CAPACIDADCONSUMIR = 15;
		Buffer bf = new Buffer(CAPACIDADBUFFER);
		new Productor(CAPACIDADPRODUCIR,bf).start();
		new Consumidor(CAPACIDADCONSUMIR,bf).start();
	}
}
