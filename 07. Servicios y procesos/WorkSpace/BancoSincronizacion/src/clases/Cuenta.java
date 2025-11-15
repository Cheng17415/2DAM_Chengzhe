package clases;

public class Cuenta {
	private int dinero;
	
	public Cuenta(int dinero) {
		super();
		this.dinero = dinero;
	}
	
	public synchronized int getDinero() {
		return dinero;
	}

	public synchronized void ingresar(int cantidad) {
		dinero += cantidad;
	}
	public synchronized void retirar(int cantidad) {
		dinero -= cantidad;
	}
}
