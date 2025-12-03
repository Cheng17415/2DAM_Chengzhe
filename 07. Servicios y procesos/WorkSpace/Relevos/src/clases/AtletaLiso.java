package clases;

public class AtletaLiso extends Thread{
	private String dorsal;
	private Object pistoletazo;
	private double duracion;
	public AtletaLiso(String dorsal, Object pistoletazo) {
		super();
		this.dorsal = dorsal;
		this.pistoletazo = pistoletazo;
	}
	public String getDorsal() {
		return dorsal;
	}
	public void setDorsal(String dorsal) {
		this.dorsal = dorsal;
	}
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
	@Override
	public String toString() {
		return "AtletaLiso [dorsal=" + dorsal + ", duracion=" + duracion + "]";
	}
	@Override
	public void run() {
		super.run();
		synchronized(pistoletazo) {
			try {
				pistoletazo.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(dorsal + " ha empezado ha correr");
		double tiempoini = System.currentTimeMillis();
		
		try {
			long n = (long)(Math.random() *(11-9 +1)) + 9;
			Thread.sleep(n * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double tiempofin = System.currentTimeMillis();
		duracion = (tiempofin - tiempoini)/1000;
		System.out.println(dorsal + " ha terminado de correr con un tiempo de " + duracion);
		}
	
	
	
}
