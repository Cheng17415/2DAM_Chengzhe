package clases;

public class Persona implements Comparable<Persona>{
	private String nombre;
	private int edad;
	public Persona(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	public Persona() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
	}
	@Override
	public int compareTo(Persona o) {
		if(this.edad > o.edad) {return 1;} 
		else if(this.edad < o.edad) {return -1;} 
		return this.nombre.compareTo(o.nombre);
	}

}
