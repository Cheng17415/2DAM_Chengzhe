package com.rayosoft.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the solicitudes database table.
 * 
 */
@Entity
@Table(name="solicitudes")
@NamedQuery(name="Solicitude.findAll", query="SELECT s FROM Solicitude s")
public class Solicitude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String archivo;

	@Lob
	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Vacante
	@ManyToOne
	@JoinColumn(name="idVacante")
	private Vacante vacante;

	public Solicitude() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArchivo() {
		return this.archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vacante getVacante() {
		return this.vacante;
	}

	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}

}