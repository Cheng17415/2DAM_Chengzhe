package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="NOMBRE_CLIENTE")
	private String nombreCliente;

	public Cliente() {
	}
	
	@ManyToMany(mappedBy="clientes", cascade= CascadeType.PERSIST)
	private List<Producto> productos = new ArrayList<>(); 

	public Cliente(String nombreCliente) {
		super();
		this.nombreCliente = nombreCliente;
	}


	public Cliente(int id, String nombreCliente) {
		super();
		this.id = id;
		this.nombreCliente = nombreCliente;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	
	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public void add(Producto producto) {
		productos.add(producto);
		producto.getClientes().add(this);
	}
	
	public void remove(Producto producto) {
		productos.remove(producto);
		producto.getClientes().remove(this);
	}

}