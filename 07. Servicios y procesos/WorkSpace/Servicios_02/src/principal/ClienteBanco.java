package principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBanco {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(Socket socket = new Socket("10.50.2.42", 12345);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
			
			System.out.println("Introduzca la capital: ");
			Double capital = Double.valueOf(sc.nextLine());
			System.out.println("Introduzca el interes anual: ");
			Double iAnual = Double.valueOf(sc.nextLine());
			System.out.println("Introduzca el nº de anios de credito: ");
			Double anios = Double.valueOf(sc.nextLine());
			
			dos.writeDouble(capital);
			dos.writeDouble(iAnual);
			dos.writeDouble(anios);
			
			double mensualidad = dis.readDouble();
			System.out.println("La mensualidad que debes de pagar es de " + mensualidad);
		} catch(IOException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.getMessage();
		} finally{
			sc.close();
		}
	}
}
