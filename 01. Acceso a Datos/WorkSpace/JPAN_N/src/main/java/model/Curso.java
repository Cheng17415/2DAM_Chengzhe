package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double duracion;

	private String titulo;

	//bi-directional many-to-many association to Alumno
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
		name="alumnocurso"
		, joinColumns={
			@JoinColumn(name="CURSO_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ALUMNO_DNI")
			}
		)
	private List<Alumno> alumnos = new ArrayList<>();

	public Curso() {}
	

	public Curso(String id, double duracion, String titulo) {
		super();
		this.id = id;
		this.duracion = duracion;
		this.titulo = titulo;
		this.alumnos = new ArrayList<>();
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void add(Alumno alumno) {
		alumnos.add(alumno);
		alumno.getCursos().add(this);
	}
	
	public void remove(Alumno alumno) {
		alumnos.remove(alumno);
		alumno.getCursos().remove(this);
	}

}