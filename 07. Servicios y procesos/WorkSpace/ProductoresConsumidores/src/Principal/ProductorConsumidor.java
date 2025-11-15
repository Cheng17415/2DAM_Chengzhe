package Principal;

import Clases.Consumidor;
import Clases.Panaderia;
import Clases.Panadero;

public class ProductorConsumidor {

	public static void main(String[] args) {
		Panaderia p = new Panaderia();
		new Thread(new Panadero(p)).start();
		new Thread(new Consumidor(p)).start();
	}

}
