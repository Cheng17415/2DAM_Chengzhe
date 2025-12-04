package clases;

public class Cajera extends Thread{
	private String nombre;
	//Clientes comunes para todas las cajeras
	private Cliente [] clientes;
	
	public Cajera(String nombre, Cliente [] clientes) {
		super();
		this.nombre = nombre;
		this.clientes = clientes;
	}

	public String getNombre() {
		return nombre;
	}
	
	@Override
	public void run() {
		System.out.println("Cajera " + nombre + " ha empezado a cobrar");
		for (Cliente cliente : clientes) {
			Supermercado.atenderCliente(cliente, this);
		}
		System.out.println("La cajera " + nombre + " ha terminado de cobrar.");
	}

	
}
