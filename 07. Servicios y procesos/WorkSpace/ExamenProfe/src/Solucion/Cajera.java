package Solucion;

import java.util.List;

class Cajera extends Thread {
	private final String nombre;
	private final List<Cliente> cola;
	private final long inicioSimulacionMs;

	public Cajera(String nombre, List<Cliente> cola, long inicioSimulacionMs) {
		this.nombre = nombre;
		this.cola = cola;
		this.inicioSimulacionMs = inicioSimulacionMs;
	}
	@Override
	public void run() {
		Cliente cliente = null;
		System.out.printf("[%6d ms] %s: inicio de turno\n", tiempoDesdeInicio(), nombre);
		try {
			while (true) {

				synchronized (cola) {
					if (cola.size() == 0)break;
					cliente=cola.remove(0);
				}
				procesarCliente(cliente);

			}
		} finally {
			System.out.printf("[%6d ms] %s: fin de turno\n", tiempoDesdeInicio(), nombre);
		}
	}

	private void procesarCliente(Cliente cliente) {
		long inicioCliente = tiempoDesdeInicio();
		System.out.printf("[%6d ms] %s: atiende a %s%n", inicioCliente, nombre, cliente.getNombre());

		int i = 1;
		for (int tiempo : cliente.getTiemposProducto()) {
			try {
				Thread.sleep(tiempo);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
			System.out.printf("[%6d ms] %s: producto %d de %s cobrado (%d ms)%n", tiempoDesdeInicio(), nombre, i++,
					cliente.getNombre(), tiempo);
		}

		long finCliente = tiempoDesdeInicio();
		System.out.printf("[%6d ms] %s: terminó con %s (duración: %d ms)%n", finCliente, nombre, cliente.getNombre(),
				(finCliente - inicioCliente));
	}

	private long tiempoDesdeInicio() {
		return System.currentTimeMillis() - inicioSimulacionMs;
	}
}

