package clases;

import java.io.Serializable;
import java.time.LocalDate;

public class Libro implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String autor;
	private LocalDate fecha_publicacion;
	
	
	public Libro() {
		super();
	}


	public Libro(String nombre, String autor, LocalDate fecha_publicacion) {
		super();
		this.nombre = nombre;
		this.autor = autor;
		this.fecha_publicacion = fecha_publicacion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public LocalDate getFecha_publicacion() {
		return fecha_publicacion;
	}


	public void setFecha_publicacion(LocalDate fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Libreria [nombre=" + nombre + ", autor=" + autor + ", fecha_publicacion=" + fecha_publicacion + "]";
	}
}
