package clases;

public class Buffer {
	private int tamanoMax;
	private char[] buffer;
	private int contador = 0;
	
	public Buffer(int tamanoMax) {
		super();
		this.tamanoMax = tamanoMax;
		buffer = new char[tamanoMax];
	}
		
	public synchronized void producir(char c) {
		while(contador == tamanoMax) {
			try {
				System.out.println("Esperando a que el buffer no este lleno");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[contador] = c;
		contador++;
		System.out.println("Anadido " + c);
		notifyAll();
	}
	
	public synchronized void consumir() {
		while(contador == 0) {
			try {
				System.out.println("Esperando a que produzcan");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		char c = buffer[--contador];
		System.out.println("Consumido " + c);
		notifyAll();
	}
	
}
