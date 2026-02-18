package principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor{
	Scanner sc = new Scanner(System.in);
	private ServerSocket serversocket;
	private Socket socket;
	private DataInputStream bufferEntrada = null;
	private DataOutputStream bufferSalida = null;
	final String COMANDO_TERMINACION = "SALIR";
	
	public void levantarConexion(int puerto) {
		try {
			serversocket = new ServerSocket(puerto);
			System.out.println("Esperando conexion entrante en el puerto " + String.valueOf(puerto));
			socket = serversocket.accept();
			System.out.println("Conexion establecida con " + socket.getInetAddress().getHostAddress());
		} catch (IOException e) {
			System.out.println("Error al levantar conexion: " + e.getMessage());
		}
	}
	
	public void flujos() {
		try {
			bufferEntrada = new DataInputStream(socket.getInputStream());
			bufferSalida = new DataOutputStream(socket.getOutputStream());
			bufferSalida.flush();
		} catch (IOException e) {
			System.out.println("Error en la apertura de flujos: " + e.getMessage());
		}
	}
	
	public void recibirDatos() {
		String cadena = "";
		try {
			enviar("[SERVIDOR] => Escriba su nombre");
			String nombre = bufferEntrada.readUTF();
			enviar(String.format("Bienvenido %s, el servidor está listo", nombre));
			do {
				cadena = bufferEntrada.readUTF();
				if(!cadena.equals(COMANDO_TERMINACION)) {
					enviar(String.format("[SERVIDOR] => %s [%d]", cadena.toUpperCase(),cadena.length()));
				}
			} while(!cadena.equals(COMANDO_TERMINACION));
		} catch (IOException e) {
			cerrarConexion();
		}
	}

	private void enviar(String cadena) {
		try {
			bufferSalida.writeUTF(cadena);
			bufferSalida.flush();
		} catch (IOException e) {
			System.out.println("Error al enviar datos " + e.getMessage());
		}
	}

	public void cerrarConexion() {
		try {
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
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
	public static void main(String[] args) throws IOException {
		Servidor servidor = new Servidor();
		System.out.print("Indique el puerto a conectar [5000]: ");
		String puerto = servidor.sc.nextLine();
		if(puerto.length() <= 0 || !Utileria.sonDigitos(puerto)) {
			System.out.println("Puerto invalido, conectando al puerto 5000");
			puerto = "5000";
		}
		servidor.ejecutarConexion(Integer.valueOf(puerto));
		
	}
		
}