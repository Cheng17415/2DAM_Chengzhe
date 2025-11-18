package clases;

import java.util.ArrayList;
import java.util.Objects;

public class Mensaje {
	private Contacto contacto;
	private ArrayList<String> mensajes = new ArrayList<>();

	public Mensaje(Contacto contacto, String mensaje) {
		super();
		this.contacto = contacto;
		
	}

	public Contacto getContacto() {
		return contacto;
	}

	public ArrayList<String> getMensajes() {
		return mensajes;
	}

	@Override
	public String toString() {
		return contacto + ", mensaje =" + mensajes;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(contacto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(contacto, other.contacto);
	}

	public void enviarMensaje(String mensaje) {
		mensajes.add(mensaje);
	}
}
