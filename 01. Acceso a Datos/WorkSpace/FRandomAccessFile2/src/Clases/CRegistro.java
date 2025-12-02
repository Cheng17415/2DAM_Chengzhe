package Clases;

import java.util.Objects;

public class CRegistro {
	String referencia;
	double precio;
	
	public CRegistro(String referencia, double precio) {
		this.referencia = referencia;
		this.precio = precio;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CRegistro other = (CRegistro) obj;
		return Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(referencia, other.referencia);
	}

	
	public String getReferencia() {
		return referencia;
	}


	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "CRegistro [referencia=" + referencia + ", precio=" + precio + "]";
	}
	
	public int tamano() {
		return this.referencia.length() * 2 + Double.BYTES;
	}
}
