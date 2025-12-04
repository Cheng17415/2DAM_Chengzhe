package clases;

public class Producto {
	private long tiempo;
	
	public Producto() {
		super();
		//Cada producto tiene un tiempo distinto de cobro.
		this.tiempo = generarTiempoProducto();
	}

	public long getTiempo() {
		return tiempo;
	}
	
	private long generarTiempoProducto() {
		return (long) (Math.random() * (500 - 250 + 1) +250);
	}
}
