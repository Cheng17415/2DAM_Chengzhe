package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the jornadas database table.
 * 
 */
@Entity
@Table(name="jornadas")
@NamedQuery(name="Jornada.findAll", query="SELECT j FROM Jornada j")
public class Jornada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idjornada;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	public Jornada() {
	}

	public int getIdjornada() {
		return this.idjornada;
	}

	public void setIdjornada(int idjornada) {
		this.idjornada = idjornada;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}