package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Utilidades {
	/*
	 * Obtiene un flujo de escritura a partir de un socket
	 */
	public static PrintWriter getFlujoEscritura(Socket s) throws IOException {
		OutputStream os= s.getOutputStream();
		PrintWriter pw= new PrintWriter(os);
		return pw;
	}

	/*
	 * Obtiene un flujo de lectura a partir de un socket
	 */
	public static BufferedReader getFlujoLectura(Socket s) throws IOException {
		InputStream is = s.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		return bfr;
	}
	
	public static void enviar(String cadena, PrintWriter pw) {
		pw.println(cadena);
		pw.flush();
	}
	
	public static boolean sonNumeros(String cadena) {
		if(cadena.length() <=0) return false;
		for(int i= 0; i < cadena.length(); i++) {
			if(Character.isDigit(cadena.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}