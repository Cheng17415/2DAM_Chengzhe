package clases;

public class Liebre extends Thread{
	private String nombre;
	private int posicion;
	private Carrera c;
	
	public Liebre(String nombre, Carrera c) {
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
			posicion = c.correr(posicion,calcularSuceso());
			System.out.println(c.visualizar(posicion, 'L'));
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
		if(numAlea >20 && numAlea <= 40) {
			suceso = 9;
		} else if(numAlea > 40 && numAlea <=50) {
			suceso = -12;
		} else if(numAlea > 50 && numAlea <=80) {
			suceso = 1;
		}else if(numAlea > 80 && numAlea <=100) {
			suceso = -2;
		}
		return suceso;
	}	
}
