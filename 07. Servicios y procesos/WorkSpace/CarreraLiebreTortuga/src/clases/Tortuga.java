package clases;

public class Tortuga extends Thread{
	private String nombre;
	private int posicion;
	Carrera c;
	public Tortuga(String nombre,Carrera c) {
		super();
		this.nombre = nombre;
		this.posicion = 1;
		this.c = c;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	@Override
	public void run() {
		while(!c.isGanador()) {
			posicion = c.correr(posicion, calcularSuceso());
			System.out.println(c.visualizar(posicion, 'T'));
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int calcularSuceso() {
		int numAlea = (int) (Math.random() * 100) + 1;
		int suceso = 0;
		if(numAlea <= 50) {
			suceso = 3;
		} else if(numAlea > 50 && numAlea <=70) {
			suceso = -6;
		} else {
			suceso = 1;
		}
		return suceso;
	}
	
}
