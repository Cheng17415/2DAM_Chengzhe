package clases;

public class Carrera {
	private int meta;
	private boolean ganador;

	public Carrera(int meta) {
		super();
		this.meta = meta;
		this.ganador = false;
	}

	public int getMeta() {
		return meta;
	}
	
	public boolean isGanador() {
		return ganador;
	}

	public int correr(int pos, int casillas) {
		int nuevaPosicion = pos + casillas;
		if(nuevaPosicion < 1) return 1;
		if(nuevaPosicion >= meta) {
			ganador = true;
			nuevaPosicion = meta;
		}
		return nuevaPosicion;
	}
	public String visualizar(int posicion, char c) {
		return " ".repeat(posicion -1) + c;
	}
}
