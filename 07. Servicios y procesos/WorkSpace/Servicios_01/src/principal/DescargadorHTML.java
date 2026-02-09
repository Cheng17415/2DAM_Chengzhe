package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DescargadorHTML {
	public static void main(String[] args) {
		int port = 80;
		String direccion = "www.google.es";
		
		//Inicializacion;
		InetSocketAddress direccionRed = new InetSocketAddress(direccion, port);
		Socket socket = new Socket();
		try {
			socket.connect(direccionRed);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			
			BufferedReader bfr = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(osw);
			
			pw.println("GET /index.html");
			pw.flush();
			
			String linea; 
			while ((linea = bfr.readLine())!= null) {
				System.out.println(linea);
			}
		} catch(IOException e) {
			e.getMessage();
		}
		
	}
}
