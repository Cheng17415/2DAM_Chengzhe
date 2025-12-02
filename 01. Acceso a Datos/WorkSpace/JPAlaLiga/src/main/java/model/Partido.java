package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the partidos database table.
 * 
 */
@Entity
@Table(name="partidos")
@NamedQuery(name="Partido.findAll", query="SELECT p FROM Partido p")
public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PartidoPK id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="GOL_LOCAL")
	private int golLocal;

	@Column(name="GOL_VISITANTE")
	private int golVisitante;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="IDLOCAL")
	private Equipo equipo1;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="IDVISITANTE")
	private Equipo equipo2;

	public Partido() {
	}

	public PartidoPK getId() {
		return this.id;
	}

	public void setId(PartidoPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getGolLocal() {
		return this.golLocal;
	}

	public void setGolLocal(int golLocal) {
		this.golLocal = golLocal;
	}

	public int getGolVisitante() {
		return this.golVisitante;
	}

	public void setGolVisitante(int golVisitante) {
		this.golVisitante = golVisitante;
	}

	public Equipo getEquipo1() {
		return this.equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return this.equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	@Override
	public String toString() {
		return "Partido [id=" + id.getIdjornada() + "," + id.getIdlocal() + "," + id.getIdvisitante()+ ", fecha=" + fecha + ", golLocal=" + golLocal + ", golVisitante=" + golVisitante
				;
	}
	
	

}