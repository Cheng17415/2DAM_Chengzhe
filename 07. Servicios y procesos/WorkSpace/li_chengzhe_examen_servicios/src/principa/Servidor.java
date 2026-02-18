package principa;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Servidor {
	static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) {
    	System.out.print("Indique el puerto a conectar [5500]: ");
        String puerto = sc.nextLine();
        if (puerto.length() <= 0) puerto = "5500";
        try (ServerSocket serverSocket = new ServerSocket(Integer.valueOf(puerto))) {

            System.out.println("Servidor iniciado en puerto " + puerto);

            while (true) { // Acepta conexiones infinitamente
                Socket socketCliente = serverSocket.accept();
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress());

                // Lanzamos un hilo por cliente
                ClienteHandler cliente = new ClienteHandler(socketCliente);
                cliente.start();
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor");
            e.printStackTrace();
        }
    }
}