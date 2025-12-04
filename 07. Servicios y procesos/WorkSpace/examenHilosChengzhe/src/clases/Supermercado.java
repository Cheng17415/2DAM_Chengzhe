package clases;

public class Supermercado {
	
	public static void atenderCliente(Cliente cliente, Cajera cajera) {
		//Cada cajero debe mirar una vez si ese cliente está atendido, si no, pasa al siguiente.
		if(cliente.esAtendido()) return;
		System.out.println("El cliente " + cliente.getNombre() + " está siendo atendido por cajera " + cajera.getNombre());
		//Para cada produco, simulo que está escaneando el producto con el método sleep.
		for(Producto p : cliente.getProductos()) {
			long tiempoProducto = p.getTiempo();
			try {
				Cajera.sleep(tiempoProducto);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cliente.setDuracion(cliente.getDuracion() + tiempoProducto);
		}
		System.out.println("El cliente " + cliente.getNombre() + " ha tardado " + (cliente.getDuracion()/1000.0) + " segundos");
	}
}
