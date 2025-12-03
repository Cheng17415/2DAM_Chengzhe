package ejFilosofos2;

public class Mesa {
	private boolean [] tenedores;

	public Mesa(int num) {
		super();
		tenedores = new boolean [num];
	}
	
	public int tenedorIzq(int i) {
		return i;
	}
	
	public int tenedorDer(int i) {
		if(i == 0) {
			return tenedores.length - 1;
		}
		return i - 1;
	}
	
	public synchronized void cogerTenedores(int comensal) {
		//Si un hilo está en el wait Libera también el método, lo que permite a otros hilos entrar.
		while(tenedores[tenedorIzq(comensal)] || tenedores[tenedorDer(comensal)]) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		tenedores[tenedorIzq(comensal)] = true;
		tenedores[tenedorDer(comensal)] = true;
	}
	
	public synchronized void dejarTenedores(int comensal) {
		tenedores[tenedorIzq(comensal)] = false;
		tenedores[tenedorDer(comensal)] = false;
		notifyAll();
	}
	
}
