package principa;

import java.io.*;
import java.net.Socket;

public class ClienteHandler extends Thread {

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private String nombreUsuario;

    public ClienteHandler(Socket socket) throws IOException {
        this.socket = socket;
        salida = new PrintWriter(socket.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {

        try {

            // Recibir nombre de usuario
            nombreUsuario = entrada.readLine();
            salida.println("Bienvenido " + nombreUsuario + ", el servidor está listo");

            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {

                if (mensaje.equalsIgnoreCase("SALIR")) {
                    salida.println("Conexión cerrada. Hasta pronto.");
                    break;
                }

                // Procesar mensaje
                String respuesta = mensaje.toUpperCase() + " (Longitud: " + mensaje.length() + ")";
                salida.println(respuesta);
            }

            socket.close();
            System.out.println("Cliente desconectado: " + nombreUsuario);

        } catch (IOException e) {
            System.out.println("Error con el cliente");
        }
    }
}
