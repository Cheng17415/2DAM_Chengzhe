package clases;

public class Consumidor extends Thread{
	private int cantidadConsumir;
	private Buffer bf;
	public Consumidor(int cantidadConsumir, Buffer bf) {
		super();
		this.cantidadConsumir = cantidadConsumir;
		this.bf = bf;
	}
	@Override
	public void run() {
		for (int i = 0; i < cantidadConsumir; i++) {
			bf.consumir();
			try {
				Thread.sleep((long)(Math.random() * 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
