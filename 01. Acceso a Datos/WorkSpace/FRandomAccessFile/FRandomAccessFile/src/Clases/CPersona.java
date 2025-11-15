package Clases;

public class CPersona {
	private String nombre;
	private String direccion;
	private long telefono;
	
	public CPersona() {		super();}

	public CPersona(String nombre, String direccion, long telefono) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "CPersona [nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
	public int tamaño() {
		//8 bytes los long
		return nombre.length()*2+direccion.length()*2+Long.BYTES;
	}
	
}
