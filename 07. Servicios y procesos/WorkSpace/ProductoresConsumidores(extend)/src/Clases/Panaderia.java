package Clases;

public class Panaderia {
	private String pan;
	private boolean disponible;
	
	public synchronized void hornear(String masa) {
		while(disponible) {
			try {
				wait();
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		this.pan = masa;
		System.out.println("Panadero hornea " + this.pan);
		this.disponible = true;
		notify();
	}
	
	public synchronized String consumir() {
		while(!disponible) {
			try {
				wait();
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("El consumidor consume " + this.pan);
		this.disponible = false;
		notify();
		return this.pan;
	}
	
}
