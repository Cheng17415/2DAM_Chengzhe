package Clases;

public class Consumidor extends Thread {
	Buffer buffer;

	public Consumidor(Buffer buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
			for(int i= 0; i< 10; i++) {
				try {
					buffer.consumir();
					sleep((int)(Math.random()*1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

	}
	
	
}
