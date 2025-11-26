package Clases;

public class Almacen {
	private int unidades;
	
	public Almacen(int unidades) {
		this.unidades = unidades;
	}

	public int getUnidades() {
		return this.unidades;
	}
	
	public synchronized boolean quedanUnidades() {
		return unidades > 0;
	}
	
	public synchronized void cogerUnidad() {
		if(unidades > 0) unidades--;
	}
	
}
