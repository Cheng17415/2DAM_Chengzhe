package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
	public void verificarCadenas(BufferedReader bfr,PrintWriter pw) throws IOException	{
		int n=4;
		double mensu;
		pw.println(n);
		pw.println("150000:3:25");
		pw.println("250000:3:25");
		pw.println("350000:3:25");
		pw.println("450000:3:25");
		pw.flush();
		
		for(int i=0; i<n; i++) {
			mensu = Double.valueOf(bfr.readLine());
			System.out.println(mensu);
		}
	}
	public static void main(String[] args) {
		Cliente cliente=new Cliente();
		InetSocketAddress direccion=new InetSocketAddress("localhost", 9876);
		Socket conexion=new Socket();
		try {
			conexion.connect(direccion);
			BufferedReader bfr=Utilidades.getFlujoLectura(conexion);
			PrintWriter pw=Utilidades.getFlujoEscritura(conexion);
			cliente.verificarCadenas(bfr, pw);
			pw.close();
			bfr.close();
			conexion.close();			
		} catch (IOException e) {
			//Quiza el servidor no está encendido
			//Quizá lo esté pero su cortafuegos
			//impide conexiones
			//...
		}
		
		

	}

}
