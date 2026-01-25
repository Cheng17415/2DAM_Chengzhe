package com.cheng.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String nombre;
	private String email;
	private String password;
	private Integer estatus;
	private Date fechaRegistro;

	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="UsuarioPerfil",
				joinColumns = @JoinColumn(name="idUsuario"),
				inverseJoinColumns = @JoinColumn(name="idPerfil")
				)
	private List<Perfil> perfiles;
	/*
	//bi-directional many-to-one association to Solicitude
	@OneToMany(mappedBy="usuario")
	private List<Solicitude> solicitudes;
	*/
	
	public void agregar(Perfil tempPerfil) {
		if(perfiles == null) {
			perfiles = new LinkedList<Perfil>();
		}
		perfiles.add(tempPerfil);
	}
	public Usuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEstatus() {
		return this.estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Perfil> getPerfiles(){
		return perfiles;
	}
	
	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", nombre=" + nombre + ", email=" + email
				+ ", password=" + password + ", estatus=" + estatus + ", fechaRegistro=" + fechaRegistro + "]";
	}

	
}