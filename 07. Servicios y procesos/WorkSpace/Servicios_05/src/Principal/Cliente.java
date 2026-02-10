package Principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	Scanner sc = new Scanner(System.in);
	private Socket socket;
	private DataInputStream bufferEntrada = null;
	private DataOutputStream bufferSalida = null;
	final String COMANDO_TERMINACION = "salir()";

	public void levantarConexion(String ip, int puerto) {
		try {
			socket = new Socket(ip, puerto);
			System.out.println("Conectado a  " + socket.getInetAddress().getHostAddress());
		} catch (IOException e) {
			System.out.println("Error en la conexion " + e.getMessage());
			System.exit(0);
		}
	}

	public void flujos() {
		try {
			bufferEntrada = new DataInputStream(socket.getInputStream());
			bufferSalida = new DataOutputStream(socket.getOutputStream());
			bufferSalida.flush();
		} catch (Exception e) {
			System.out.println("Error en la apertura de flujos.");
		}
	}

	public void recibirDatos() {
		String cadena = "";
		try {
			do {
				cadena = (String) bufferEntrada.readUTF();
				System.out.print("\n[Cliente] => " + cadena);
				System.out.print("\n[Usted] => ");
			} while (!cadena.equals(COMANDO_TERMINACION));

		} catch (IOException e) {
			cerrarConexion();
		}
	}

	public void ejecutarConexion(String ip, int puerto) {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					levantarConexion(ip, puerto);
					flujos();
					recibirDatos();
				} finally {
					cerrarConexion();
				}
			}
		});
		hilo.start();
	}

	public void escribirDatos() {
		String mensaje ="";
		while (!mensaje.equals(COMANDO_TERMINACION)) {
		    mensaje = sc.nextLine();
		    enviar("n[Usted] => " +mensaje);
		}
		cerrarConexion();

	}
	
	public void enviar(String cadena) {
		try {
			bufferSalida.writeUTF(cadena);
			bufferSalida.flush();
		} catch(IOException e) {
			System.out.println("Error al envia los datos " + e.getMessage());
		}
	}

	private void cerrarConexion() {
		try {
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar conexiones");
		} finally {
			System.out.println("Conversación finalizada");
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		System.out.println("Ingrese la direccion IP: [localhost por defecto]");
		String ip = cliente.sc.nextLine();
		if (ip.length() <= 0)
			ip = "localhost";
		System.out.println("Ingrese el puerto: [5500 por defecto]");
		String puerto = cliente.sc.nextLine();
		if (puerto.length() <= 0)
			puerto = "5500";
		cliente.ejecutarConexion(ip, Integer.parseInt(puerto));
		cliente.escribirDatos();
	}
}
