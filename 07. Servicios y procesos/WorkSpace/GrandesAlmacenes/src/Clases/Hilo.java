package Clases;

public class Hilo implements Runnable {
	private String nombre;
	private int intento;
	private Almacen almacen;

	public Hilo(String nombre, int intento, Almacen almacen) {
		super();
		this.nombre = nombre;
		this.intento = intento;
		this.almacen = almacen;
	}

	@Override
	public void run() {
		while (intento > 0) {
			synchronized (almacen) {
				if (almacen.quedanUnidades()) {
					almacen.cogerUnidad();
					System.out.println(nombre + " ha conseguido comprar");
					return;
				} else {
					System.out.println(nombre + " ha entrado pero no ha conseguido comprar");
				}

			}
			intento--;
		}
		System.out.println(nombre + " se ha quedado sin paciencia");
	}

}
