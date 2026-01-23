package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the perfiles database table.
 * 
 */
@Entity
@Table(name="perfiles")
@NamedQuery(name="Perfile.findAll", query="SELECT p FROM Perfile p")
public class Perfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String perfil;

	public Perfile() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}