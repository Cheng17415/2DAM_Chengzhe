package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable {
	final String COMANDO_TERMINACION = "SALIR";
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	public HiloConexion(Socket socket) {
		this.socket = socket;
	}

	public void procesarLineas() throws IOException, InterruptedException {
		Thread.sleep(1000);
		Utilidades.enviar("[SERVIDOR] => Introduzca su nombre", pw);
		String nombre = bfr.readLine();
		Utilidades.enviar("[SERVIDOR] => Bienvenido " + nombre +", el servidor esta listo", pw);
		Utilidades.enviar("[SERVIDOR] => Introduzca el texto a convertir (Para salir: SALIR)", pw);
		String linea ="";
		do {
			linea = bfr.readLine();
			if(!linea.equals(COMANDO_TERMINACION)) {
				Utilidades.enviar(String.format("[SERVIDOR] => %s (longitud %d)", linea.toUpperCase(), linea.length()), pw);
			}
		} while(!linea.equals(COMANDO_TERMINACION));
		System.out.println("Conexion interrumpida con " + socket.getInetAddress());
	}

	public void run() {
		try {

			bfr = Utilidades.getFlujoLectura(this.socket);
			pw = Utilidades.getFlujoEscritura(this.socket);
			procesarLineas();

		} catch (IOException | InterruptedException e) {
			System.out.println("Hubo una interrupción");
		}

	}

}
