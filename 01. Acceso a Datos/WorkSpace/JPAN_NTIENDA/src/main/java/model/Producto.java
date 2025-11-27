package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="NOMBRE_PRODUCTO")
	private String nombreProducto;
	
	//bi-directional many-to-many association to Alumno
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
		name="cliente_producto"
		, joinColumns={
			@JoinColumn(name="ID_PRODUCTO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_CLIENTE")
			}
		)
	private List<Cliente> clientes = new ArrayList<>();
		
	public Producto() {
	}

	public Producto(String nombreProducto) {
		super();
		this.nombreProducto = nombreProducto;
	}

	public Producto(int id, String nombreProducto) {
		super();
		this.id = id;
		this.nombreProducto = nombreProducto;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void add(Cliente cliente) {
		clientes.add(cliente);
		cliente.getProductos().add(this);
	}
	
	public void remove(Cliente cliente) {
		clientes.remove(cliente);
		cliente.getProductos().remove(this);
	}
}