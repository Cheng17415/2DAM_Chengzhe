package Principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor{
	Scanner sc = new Scanner(System.in);
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream bufferEntrada = null;
	private DataOutputStream bufferSalida = null;
	final String COMANDO_TERMINACION = "salir()";
	
	public void levantarConexion(int puerto) {
		try {
			serverSocket = new ServerSocket(puerto);
			System.out.println("Esperando conexion entrante en el puerto " + String.valueOf(puerto));
			socket = serverSocket.accept();
		} catch(IOException e) {
			System.out.println("Error en la conexion " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void flujos() {
		try {
			bufferEntrada = new DataInputStream(socket.getInputStream());
			bufferSalida = new DataOutputStream(socket.getOutputStream());
			bufferSalida.flush();
		} catch(Exception e) {
			System.out.println("Error en la apertura de flujos.");
		}
	}
	
	
	
	public void recibirDatos() {
		String cadena = "";
		try {
			do {
				cadena = bufferEntrada.readUTF();
				System.out.print("\n[Cliente] => " + cadena);
				System.out.print("\n[Usted] => ");
			} while(!cadena.equals(COMANDO_TERMINACION));
			
		} catch(IOException e) {
			cerrarConexion();
		}
	}
	
	public void escribirDatos() {
		String mensaje ="";
		while (!mensaje.equals(COMANDO_TERMINACION)) {
			System.out.print("\n[Usted] => ");
		    mensaje = sc.nextLine();
		    enviar(mensaje);
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
	
	public void ejecutarConexion(int puerto) {
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						levantarConexion(puerto);
						flujos();
						recibirDatos();
					} finally {
						cerrarConexion();
					}
				}
				
			}
		});
		hilo.start();
	}
	
	private void cerrarConexion() {
		try {
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		} catch(IOException e) {
			System.out.println("Error al cerrar conexiones");
		} finally {
			System.out.println("Conversación finalizada");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		Servidor s = new Servidor();
		System.out.print("Indique el puerto a conectar [5500]: ");
		String puerto = s.sc.nextLine();
		if (puerto.length() <= 0) puerto = "5500";
		s.ejecutarConexion(Integer.valueOf(puerto));
		s.escribirDatos();
	}
}
