package clases;

public class Coches {
	private String coche;
	private String matricula;
	private String modelo;
	
	public Coches(String coche, String matricula, String modelo) {
		super();
		this.coche = coche;
		this.matricula = matricula;
		this.modelo = modelo;
	}
	
	public String getCoche() {
		return coche;
	}

	public void setCoche(String coche) {
		this.coche = coche;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return "Coches [coche=" + coche + ", matricula=" + matricula + ", modelo=" + modelo + "]";
	}
	
	
}