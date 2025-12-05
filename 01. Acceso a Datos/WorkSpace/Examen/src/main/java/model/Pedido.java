package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private double total;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Comercial
	@ManyToOne
	@JoinColumn(name="id_comercial")
	private Comercial comercial;

	public Pedido() {
	}
	
	public Pedido(double total, Cliente cliente, Comercial comercial) {
		super();
		this.total = total;
		this.cliente = cliente;
		this.comercial = comercial;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Comercial getComercial() {
		return this.comercial;
	}

	public void setComercial(Comercial comercial) {
		this.comercial = comercial;
	}

	@Override
	public String toString() {
		return "id=" + id + ", fecha=" + fecha + ", total=" + total + ", id_comercial=" + comercial.getId() + ", id_cliente=" +cliente.getId();
	}
}