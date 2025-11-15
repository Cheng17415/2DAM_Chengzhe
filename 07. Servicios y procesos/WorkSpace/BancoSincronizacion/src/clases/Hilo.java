package clases;

public class Hilo extends Thread{
	private String nombre;
	private int cantidad;
	private boolean ingreso;
	private Cuenta cuenta;
	
	public Hilo(String nombre, int cantidad, boolean ingreso ,Cuenta cuenta) {
		super();
		this.nombre = nombre;
		this.ingreso = ingreso;
		this.cantidad = cantidad;
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public void run() {
		if(ingreso) cuenta.ingresar(cantidad);
		else cuenta.retirar(cantidad);
		System.out.printf("El %s ha terminado.\n", nombre);
	}
}
