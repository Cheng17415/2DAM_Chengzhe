package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente_producto database table.
 * 
 */
@Entity
@Table(name="cliente_producto")
@NamedQuery(name="ClienteProducto.findAll", query="SELECT c FROM ClienteProducto c")
public class ClienteProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClienteProductoPK id;

	public ClienteProducto() {
	}

	public ClienteProductoPK getId() {
		return this.id;
	}

	public void setId(ClienteProductoPK id) {
		this.id = id;
	}

}