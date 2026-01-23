package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorias database table.
 * 
 */
@Entity
@Table(name="categorias")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Vacante
	@OneToMany(mappedBy="categoria")
	private List<Vacante> vacantes;

	public Categoria() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Vacante> getVacantes() {
		return this.vacantes;
	}

	public void setVacantes(List<Vacante> vacantes) {
		this.vacantes = vacantes;
	}

	public Vacante addVacante(Vacante vacante) {
		getVacantes().add(vacante);
		vacante.setCategoria(this);

		return vacante;
	}

	public Vacante removeVacante(Vacante vacante) {
		getVacantes().remove(vacante);
		vacante.setCategoria(null);

		return vacante;
	}

}