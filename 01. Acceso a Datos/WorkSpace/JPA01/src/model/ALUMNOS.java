package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

//import javax.persistence.*;

@Entity
//Query JPQL
@NamedQuery(name="ALUMNOS.todos", query ="SELECT d FROM ALUMNOS d")
public class ALUMNOS implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	String dni;
	String nombre;
	String apellidos;
	int edad;
		
	@OneToMany(mappedBy ="alumno", cascade = {CascadeType.PERSIST})
	private List<CURSOS> cursos = new ArrayList<>();
	
	public ALUMNOS() {
		super();
	}

	public ALUMNOS(String dni, String nombre, String apellidos, int edad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}

	
	
	public List<CURSOS> getCursos() {
		return cursos;
	}


	public void setCursos(List<CURSOS> cursos) {
		this.cursos = cursos;
	}

	public void add(CURSOS c) {
		this.cursos.add(c);
		c.setAlumno(this);
	}

	@Override
	public String toString() {
		String cadena = "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + "\n";
		for(CURSOS c:cursos) {
			cadena += c.toStringSin() + "\n";
		}
		return cadena;
	}


	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, edad, nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ALUMNOS other = (ALUMNOS) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni) && edad == other.edad
				&& Objects.equals(nombre, other.nombre);
	}
	
	
}
