package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vacantes database table.
 * 
 */
@Entity
@Table(name="vacantes")
@NamedQuery(name="Vacante.findAll", query="SELECT v FROM Vacante v")
public class Vacante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String descripcion;

	private int destacado;

	@Lob
	private String detalles;

	private String estatus;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String imagen;

	private String nombre;

	private double salario;

	//bi-directional many-to-one association to Solicitude
	@OneToMany(mappedBy="vacante")
	private List<Solicitude> solicitudes;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;

	public Vacante() {
	}

	public int getId() {
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

	public int getDestacado() {
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

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public List<Solicitude> getSolicitudes() {
		return this.solicitudes;
	}

	public void setSolicitudes(List<Solicitude> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Solicitude addSolicitude(Solicitude solicitude) {
		getSolicitudes().add(solicitude);
		solicitude.setVacante(this);

		return solicitude;
	}

	public Solicitude removeSolicitude(Solicitude solicitude) {
		getSolicitudes().remove(solicitude);
		solicitude.setVacante(null);

		return solicitude;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}