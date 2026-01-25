package com.cheng.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="vacantes")
public class Vacante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private Double salario;
	private Integer destacado;
	private String imagen = "no-image.png";
	private String estatus;
	private String detalles;

	//@Transient
	@OneToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;

	public Vacante() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDestacado() {
		return this.destacado;
	}

	public void setDestacado(int destacado) {
		this.destacado = destacado;
	}

	public String getDetalles() {
		return this.detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void reset() {
		this.imagen = null;
	}

	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", destacado=" + destacado
				+ ", detalles=" + detalles + ", estatus=" + estatus + ", fecha=" + fecha + ", imagen=" + imagen
				+ ", salario=" + salario + ", categoria=" + categoria + "]";
	}
	
}