package clases;

public class Atletas extends Thread{
	private String nombre;
	private static boolean testigo;
	
	public Atletas(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static boolean isTestigo() {
		return testigo;
	}

	public static void setTestigo(boolean testigo) {
		Atletas.testigo = testigo;
	}
	
	
}
