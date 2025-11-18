package clases;

import java.util.Objects;

public class Contacto {
	private String nombre;
	private int numero;
	
	public Contacto(String nombre, int numero) {
		super();
		this.nombre = nombre;
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", numero=" + numero + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return numero == other.numero;
	}

	
	
}
