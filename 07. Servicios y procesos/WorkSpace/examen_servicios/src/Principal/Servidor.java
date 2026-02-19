package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	private int puerto;
	
	public Servidor(int puerto) {
		super();
		this.puerto = puerto;
	}
	
	public int getPuerto() {
		return puerto;
	}

	public void servir(){
		ServerSocket serverSocket;
		
		try {
			serverSocket=new ServerSocket(puerto);
			while (true){
				Socket conexion;
				conexion=serverSocket.accept();
				System.out.println("Conexion establecida con " + conexion.getInetAddress());
				HiloConexion hiloConexion=new HiloConexion(conexion);
				Thread hilo=new Thread(hiloConexion);
				hilo.start();
			}
		} catch (IOException e) {
			System.out.println("Hubo un error " + e.getMessage());
		}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Indique el puerto a conectar [5000]: ");
		String puerto = sc.nextLine();
		if (puerto.length() <= 0) puerto = "5000";
		Servidor servidor = new Servidor(Integer.valueOf(puerto));
		System.out.println("Servidor encendido en el puerto " + servidor.getPuerto());
		servidor.servir();
		sc.close();
	}
}
