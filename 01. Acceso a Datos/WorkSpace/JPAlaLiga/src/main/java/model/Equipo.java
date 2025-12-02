package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="equipos")
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idequipo;

	private String nombre;

	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="equipo1", cascade = {CascadeType.PERSIST})
	private List<Partido> partidos1 = new ArrayList<>();

	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="equipo2")
	private List<Partido> partidos2;

	public Equipo() {
	}

	public Equipo(int idequipo, String nombre, List<Partido> partidos1, List<Partido> partidos2) {
		super();
		this.idequipo = idequipo;
		this.nombre = nombre;
		this.partidos1 = partidos1;
		this.partidos2 = partidos2;
	}


	public int getIdequipo() {
		return this.idequipo;
	}

	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Partido> getPartidos1() {
		return this.partidos1;
	}

	public void setPartidos1(List<Partido> partidos1) {
		this.partidos1 = partidos1;
	}

	public Partido addPartidos1(Partido partidos1) {
		getPartidos1().add(partidos1);
		partidos1.setEquipo1(this);

		return partidos1;
	}

	public Partido removePartidos1(Partido partidos1) {
		getPartidos1().remove(partidos1);
		partidos1.setEquipo1(null);

		return partidos1;
	}

	public List<Partido> getPartidos2() {
		return this.partidos2;
	}

	public void setPartidos2(List<Partido> partidos2) {
		this.partidos2 = partidos2;
	}

	public Partido addPartidos2(Partido partidos2) {
		getPartidos2().add(partidos2);
		partidos2.setEquipo2(this);

		return partidos2;
	}

	public Partido removePartidos2(Partido partidos2) {
		getPartidos2().remove(partidos2);
		partidos2.setEquipo2(null);

		return partidos2;
	}

	@Override
	public String toString() {
		return "Equipo [idequipo=" + idequipo + ", nombre=" + nombre + ", partidos1=" + partidos1 + ", partidos2="
				+ partidos2 + "]";
	}
	

}