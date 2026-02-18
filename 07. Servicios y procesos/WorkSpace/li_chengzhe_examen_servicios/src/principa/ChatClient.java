package principa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static final String HOST = "localhost";
    public static final int PUERTO = 5000;

    public static void main(String[] args) {

        try (
            Socket socket = new Socket(HOST, PUERTO);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {

            System.out.print("Introduce tu nombre: ");
            String nombre = scanner.nextLine();
            salida.println(nombre);

            // Mostrar mensaje de bienvenida
            System.out.println(entrada.readLine());

            String mensaje;

            while (true) {

                System.out.print("Mensaje: ");
                mensaje = scanner.nextLine();

                salida.println(mensaje);

                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println(entrada.readLine());
                    break;
                }

                // Mostrar respuesta del servidor
                String respuesta = entrada.readLine();
                System.out.println("Servidor responde: " + respuesta);
            }

        } catch (IOException e) {
            System.out.println("No se pudo conectar con el servidor.");
        }
    }
}