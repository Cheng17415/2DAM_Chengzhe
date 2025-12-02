package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the partidos database table.
 * 
 */
@Embeddable
public class PartidoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idjornada;

	@Column(insertable=false, updatable=false)
	private int idlocal;

	@Column(insertable=false, updatable=false)
	private int idvisitante;

	public PartidoPK() {
	}
	public int getIdjornada() {
		return this.idjornada;
	}
	public void setIdjornada(int idjornada) {
		this.idjornada = idjornada;
	}
	public int getIdlocal() {
		return this.idlocal;
	}
	public void setIdlocal(int idlocal) {
		this.idlocal = idlocal;
	}
	public int getIdvisitante() {
		return this.idvisitante;
	}
	public void setIdvisitante(int idvisitante) {
		this.idvisitante = idvisitante;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PartidoPK)) {
			return false;
		}
		PartidoPK castOther = (PartidoPK)other;
		return 
			(this.idjornada == castOther.idjornada)
			&& (this.idlocal == castOther.idlocal)
			&& (this.idvisitante == castOther.idvisitante);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idjornada;
		hash = hash * prime + this.idlocal;
		hash = hash * prime + this.idvisitante;
		
		return hash;
	}
}