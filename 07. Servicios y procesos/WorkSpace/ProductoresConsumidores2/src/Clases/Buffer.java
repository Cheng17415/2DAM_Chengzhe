package Clases;

public class Buffer {
	private int[] buffer;
	private int tamano;
	private int contador = 0;
	public Buffer(int tamano) {
		super();
		this.tamano = tamano;
		buffer = new int[tamano];
	}
	
	/**
	 * @throws InterruptedException ************************/
	public synchronized void producir(int valor) throws InterruptedException {
		while(contador == tamano) {
			System.out.println("Buffer lleno, el productor está esperando.");
			wait();
		}
		buffer[contador] = valor;
		contador++;
		System.out.println("El productor agrego " + valor);
		notify(); //Avisa al consumidor que hay un elemento nuevo
	}
	
	/**
	 * @throws InterruptedException ********************************************************/
	public synchronized int consumir() throws InterruptedException {
		while(contador == 0) {
			System.out.println("El consumidor esta esperando. El buffer está vacío.");
			wait(); // Espera si el buffer está vacío
		}
		int valor = buffer[--contador];
		System.out.println("El consumidor consumio " + valor);
		notify(); //Notifica al productor que hay valores por introducir
		return valor;
	}
	
	
}
