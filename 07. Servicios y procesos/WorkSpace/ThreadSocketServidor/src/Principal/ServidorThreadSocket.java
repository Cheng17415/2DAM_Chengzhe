
package Principal;

import java.io.IOException;


public class ServidorThreadSocket {

	public static final int PUERTO = 5000;
	
	public static void main(String args[]) {
		
		Servidor servidor = new Servidor(PUERTO);
		Cliente cliente = null;
		
		try {
			// Inicia el servidor
			servidor.conectar();
			// Mientras el servidor est� conectado aceptas nuevas
			// conexiones de clientes, que ser�n atendidas a trav�s
			// de hilos
			while (servidor.estaConectado()) {
				cliente = new Cliente(servidor.escuchar(),
					servidor);
				cliente.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
