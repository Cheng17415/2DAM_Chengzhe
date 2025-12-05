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
	private int id;

	private double cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

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

	public Pedido(int id, double cantidad,  Cliente cliente, Comercial comercial) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.cliente = cliente;
		this.comercial = comercial;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
		return "Pedido [id=" + id + ", cantidad=" + cantidad + ", fecha=" + fecha + ", cliente=" + cliente
				+ ", comercial=" + comercial + "]";
	}

}