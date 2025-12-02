package clases;

public class Productor extends Thread{
	private int cantidadProducir;
	private Buffer bf;
	public Productor(int cantidadProducir, Buffer bf) {
		super();
		this.cantidadProducir = cantidadProducir;
		this.bf = bf;
	}
	
	@Override
	public void run() {
		String letras = "abcdefghijklmnopqrstuvwxyz";
		int alea;
		for (int i = 0; i < cantidadProducir; i++) {
			alea = (int) (Math.random() * letras.length());
			char c = letras.charAt(alea);
			bf.producir(c);
			try {
				Thread.sleep((long)(Math.random() * 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
