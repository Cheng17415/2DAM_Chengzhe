package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="CURSOS.todos", query ="SELECT d FROM CURSOS d")
public class CURSOS implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String titulo;
	private int duracion;
	private double precio;
	
	
	@ManyToOne
	@JoinColumn(name="alumno_dni")
	private ALUMNOS alumno;          // referencia al objeto ALUMNOS

	// Constructor vacío
	public CURSOS() {
		super();
	}

	// Constructor completo
	public CURSOS(String id, String titulo, int duracion, double precio, ALUMNOS alumno) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.duracion = duracion;
		this.precio = precio;
		this.alumno = alumno;
	}

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public ALUMNOS getAlumno() {
		return alumno;
	}

	public void setAlumno(ALUMNOS alumno) {
		this.alumno = alumno;
		alumno.getCursos().add(this);
	}
	
	public String toStringSin() {
		return "CURSOS [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion +
		       ", precio=" + precio;
	}
	
	@Override
	public String toString() {
		return "CURSOS [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion +
		       ", precio=" + precio + ", alumno=" + (alumno != null ? alumno.getDni() : "null") + "]";
	}
}