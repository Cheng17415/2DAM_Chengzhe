package com.cheng.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the perfiles database table.
 * 
 */
@Entity
@Table(name="perfiles")
@NamedQuery(name="Perfile.findAll", query="SELECT p FROM Perfile p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String perfil;

	public Perfil() {
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