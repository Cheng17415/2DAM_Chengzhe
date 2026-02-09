package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
	public void verificarCadenas(BufferedReader bfr,PrintWriter pw) throws IOException	{
		pw.println(2);
		pw.println("ABC");
		pw.println("ZZ");
		pw.flush();
		String suma1=bfr.readLine();
		String suma2=bfr.readLine();
		System.out.println(suma1);
		System.out.println(suma2);
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
