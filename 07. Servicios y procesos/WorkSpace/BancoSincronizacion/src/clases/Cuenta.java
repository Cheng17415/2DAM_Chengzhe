package clases;

public class Cuenta {
	private int dinero;
	
	public Cuenta(int dinero) {
		super();
		this.dinero = dinero;
	}
	//Se utiliza synchronized para que solamente 1 hilo pueda estar dentro del método.
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
