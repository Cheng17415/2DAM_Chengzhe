package principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorBanco extends ServerSocket{

	public ServidorBanco() throws IOException {
		super(1234);
	}
	
	public void aceptarPeticiones() {
		while(true) {
			try (Socket cliente = accept();
					DataInputStream dis = new DataInputStream(cliente.getInputStream());
					DataOutputStream dos = new DataOutputStream(cliente.getOutputStream())){
				double capital = dis.readDouble();
				double interes = dis.readDouble();
				double anios = dis.readDouble();
				dos.writeDouble(mensualidad(capital, interes, anios));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public double mensualidad (double capital, double ianual, double anios) {
		double iMensual = ianual/1200;
		double t = anios * 12;
		return (capital * iMensual)/(1 - Math.pow((1 + iMensual), -t));
		
	}
	
	public static void main(String[] args) {
		//Conectamos el servidor para escuchar solicitudes

		try{
			ServidorBanco servidor = new ServidorBanco();
			System.out.println("Servidor iniciado, esperando clientes...");
	        servidor.aceptarPeticiones();
	        servidor.close();
		} catch(IOException e) {
			e.getMessage();
		}
		
	}
}
