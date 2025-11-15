package Clases;

public class Productor extends Thread{
	Buffer buffer;

	public Productor(Buffer buffer) {
		super();
		this.buffer = buffer;
	}

	private int alea(int li,int ls) {
		return (int)(Math.random()*(ls-li+1)) +1;
	}
	@Override
	public void run() {
			for(int i= 0; i< 10; i++) {
				try {
					buffer.producir(alea(500,5000));
					sleep((int)(Math.random()*1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

	}
	
	
}
