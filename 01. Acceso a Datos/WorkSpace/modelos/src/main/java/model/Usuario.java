package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private int estatus;

	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;

	private String nombre;

	private String password;

	private String username;

	//bi-directional many-to-one association to Solicitude
	@OneToMany(mappedBy="usuario")
	private List<Solicitude> solicitudes;

	public Usuario() {
	}

	public int getId() {
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

	public int getEstatus() {
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

	public List<Solicitude> getSolicitudes() {
		return this.solicitudes;
	}

	public void setSolicitudes(List<Solicitude> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Solicitude addSolicitude(Solicitude solicitude) {
		getSolicitudes().add(solicitude);
		solicitude.setUsuario(this);

		return solicitude;
	}

	public Solicitude removeSolicitude(Solicitude solicitude) {
		getSolicitudes().remove(solicitude);
		solicitude.setUsuario(null);

		return solicitude;
	}

}