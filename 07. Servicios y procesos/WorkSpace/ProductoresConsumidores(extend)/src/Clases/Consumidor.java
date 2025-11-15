package Clases;


public class Consumidor extends Thread{
	private Panaderia panaderia;
	
	public Consumidor(Panaderia panaderia){
		this.panaderia = panaderia;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			panaderia.consumir();
		}
	}

}
