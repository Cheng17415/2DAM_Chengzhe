package sincronizacionHilos;

public class Inicio {
	public static final int NUM_HILOS = 10;
	public static final int CUENTA_TOTAL = 1_000_000_000;

	/*Dividir una cuenta total en diferentes hilos
	 * Es lenta ya que como esta sincronizado, solo un hilo puede entrar*/
	public static void main(String[] args) {
		Contador c = new Contador();
		Thread[] hilos = new Thread[NUM_HILOS];
		for (int i = 0; i < NUM_HILOS; i++) {
			Thread th = new Thread(new Hilo(i, CUENTA_TOTAL / NUM_HILOS, c));
			th.start();
			hilos[i] = th;
		}

		for (Thread h : hilos) {
			try {
				h.join();
			} catch (InterruptedException ex) {}
		}
		System.out.printf("Cuenta global:%d\s", c.getCuenta());

	}
}

/***********************************************/
class Contador {
	private int cuenta = 0;

	synchronized public int getCuenta() {
		return cuenta;
	}

	synchronized public int incrementa() {
		return cuenta++;
	}
}

/***********************************************/
class Hilo implements Runnable {
	int numHilo, miParte, miCuenta = 0;
	private final Contador cont;

	public Hilo(int numHilo, int miParte, Contador cont) {
		super();
		this.numHilo = numHilo;
		this.miParte = miParte;
		this.cont = cont;
	}

	public int getMiCuenta() {
		return miCuenta;
	}

	@Override
	public void run() {
		for (int i = 0; i < miParte; i++) {
			this.cont.incrementa();
			miCuenta++;
		}
		System.out.printf("Hilo %d terminado, cuenta: %d\n", numHilo, getMiCuenta());
	}

}