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
	
	
	public void flujos() {
		try {
			bufferEntrada = new DataInputStream(socket.getInputStream());
			bufferSalida = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error en la apertura de flujos.");
		}
	}
	public static void main(String[] args) throws IOException {
		while(true) {
			Servidor server = new Servidor();
		}
		}
		
}