package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cliente_producto database table.
 * 
 */
@Embeddable
public class ClienteProductoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_CLIENTE")
	private int idCliente;

	@Column(name="ID_PRODUCTO")
	private int idProducto;

	public ClienteProductoPK() {
	}
	public int getIdCliente() {
		return this.idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdProducto() {
		return this.idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClienteProductoPK)) {
			return false;
		}
		ClienteProductoPK castOther = (ClienteProductoPK)other;
		return 
			(this.idCliente == castOther.idCliente)
			&& (this.idProducto == castOther.idProducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCliente;
		hash = hash * prime + this.idProducto;
		
		return hash;
	}
}