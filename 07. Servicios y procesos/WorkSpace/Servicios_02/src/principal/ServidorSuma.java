package principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSuma extends ServerSocket{
	public ServidorSuma() throws IOException{
		//Acepta desde 1024-49151
		super(12345);
	}
	public void aceptarPeticiones() {
		//Es un bucle infinito, se ejecutará hasta que cortemos el programa
		while (true) {
			try(Socket cliente = accept();
					DataInputStream dis = new DataInputStream(cliente.getInputStream());
					DataOutputStream dos = new DataOutputStream(cliente.getOutputStream())){
				//Capturamos los datos que nos envia el cliente
				double n1 = dis.readDouble();
				double n2 = dis.readDouble();
				//Sumamos los datos y los devolvemos
				dos.writeDouble(n1+n2);
				//La conexion se cerrará en cuanto termine el bloque try-con-recursos
			} catch(IOException e) {
				//Ha ocurrido un error al conectar el cliente
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		//Conectamos el servidor para escuchar solicitudes

		try{
			ServidorSuma servidor = new ServidorSuma();
			System.out.println("Servidor iniciado, esperando clientes...");
	        servidor.aceptarPeticiones();
	        servidor.close();
		} catch(IOException e) {
			e.getMessage();
		}
		
	}
}
