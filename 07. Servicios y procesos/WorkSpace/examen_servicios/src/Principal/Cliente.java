package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	final String COMANDO_TERMINACION = "SALIR";
	private String nombre;
	Scanner sc = new Scanner(System.in);
	public void verificarCadenas(BufferedReader bfr,PrintWriter pw) throws IOException	{
		System.out.println(bfr.readLine());
		nombre = sc.nextLine();
		Utilidades.enviar(nombre,pw);
		
		String cadena = "";
		System.out.println(bfr.readLine());
		do {
			System.out.println(bfr.readLine());
			System.out.print(String.format("[%s] => ", this.nombre));
			cadena = sc.nextLine();
			Utilidades.enviar(cadena,pw);
		} while(!cadena.equals(COMANDO_TERMINACION));
	}
	
	
	public static void main(String[] args) {
		Cliente cliente=new Cliente();
		System.out.print("Ingrese la direccion IP: [localhost por defecto] ");
		String ip = cliente.sc.nextLine();
		if(ip.length() <= 0) ip = "localhost";
		System.out.print("Ingrese el puerto: [5000 por defecto] ");
		String puerto = cliente.sc.nextLine();
		if (puerto.length() <= 0 || Utilidades.sonNumeros(puerto)) puerto = "5000";
		InetSocketAddress direccion=new InetSocketAddress(ip, Integer.valueOf(puerto));
		Socket conexion=new Socket();
		try {
			conexion.connect(direccion);
			BufferedReader bfr=Utilidades.getFlujoLectura(conexion);
			PrintWriter pw=Utilidades.getFlujoEscritura(conexion);
			System.out.println("Conexion exitosa");
			cliente.verificarCadenas(bfr, pw);
			pw.close();
			bfr.close();
			conexion.close();			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
