package clases;

public class Cliente {
	private String nombre;
	private Producto [] productos;
	private long duracion;
	private boolean atendido;
	
	public Cliente(String nombre, int numProductos) {
		super();
		this.nombre = nombre;
		this.productos = new Producto[numProductos];
		duracion = 0;
		atendido = false;
		for(int i = 0; i < numProductos; i++) {
			this.productos[i] = new Producto();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public synchronized boolean esAtendido() {
		//Si el cliente no ha sido atendido, devuelvo falso para que sepa que necesito atender a ese cliente.
		if(atendido) return true;
		atendido = true;
		return false;
	}

	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}
}
