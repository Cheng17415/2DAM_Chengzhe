package clases;

public class Equipo {
	String nombre;
	int puntos;
	
	public Equipo() {
		super();
	}

	public Equipo(String nombre, int puntos) {
		super();
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", puntos=" + puntos + "]";
	}
	
	
}
