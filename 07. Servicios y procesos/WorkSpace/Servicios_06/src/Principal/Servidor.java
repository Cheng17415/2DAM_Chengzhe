package Principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    private ServerSocket serverSocket;
    private List<ManejadorCliente> clientes;
    private static final String COMANDO_TERMINACION = "salir()";
    private int puerto;

    public Servidor(int puerto) {
        clientes = new ArrayList<>();
        this.puerto = puerto;
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);
            System.out.println("Esperando conexiones de clientes...\n");
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + socket.getInetAddress().getHostAddress());
                
                ManejadorCliente manejador = new ManejadorCliente(socket, clientes.size() + 1);
                clientes.add(manejador);
                new Thread(manejador).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Clase interna para manejar cada cliente
    class ManejadorCliente implements Runnable {
        private Socket socket;
        private DataInputStream entrada;
        private DataOutputStream salida;
        private int idCliente;
        private boolean conectado;

        public ManejadorCliente(Socket socket, int idCliente) {
            this.socket = socket;
            this.idCliente = idCliente;
            this.conectado = true;
        }

        @Override
        public void run() {
            try {
                entrada = new DataInputStream(socket.getInputStream());
                salida = new DataOutputStream(socket.getOutputStream());
                salida.flush();

                // Enviar mensaje de bienvenida
                enviar("Bienvenido! Eres el cliente " + idCliente);

                // Escuchar mensajes del cliente
                String mensaje;
                while (conectado) {
                    mensaje = entrada.readUTF();
                    System.out.println("[Cliente " + idCliente + "]: " + mensaje);

                    if (mensaje.equals(COMANDO_TERMINACION)) {
                        System.out.println("Cliente " + idCliente + " se ha desconectado");
                        break;
                    }

                    // Broadcast del mensaje a todos los clientes
                    broadcast("[Cliente " + idCliente + "]: " + mensaje);
                }
            } catch (IOException e) {
                System.out.println("Cliente " + idCliente + " desconectado abruptamente");
            } finally {
                cerrarConexion();
            }
        }

        public void enviar(String mensaje) {
            try {
                salida.writeUTF(mensaje);
                salida.flush();
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje a cliente " + idCliente);
            }
        }

        private void broadcast(String mensaje) {
            for (ManejadorCliente cliente : clientes) {
                if (cliente != this && cliente.conectado) {
                    cliente.enviar(mensaje);
                }
            }
        }

        private void cerrarConexion() {
            conectado = false;
            try {
                if (entrada != null) entrada.close();
                if (salida != null) salida.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar conexión del cliente #" + idCliente);
            }
            clientes.remove(this);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        
        System.out.print("Indique el puerto a conectar [5500]: ");
        String puerto = sc.nextLine();
        if (puerto.length() <= 0) puerto = "5500";
        Servidor servidor = new Servidor(Integer.valueOf(puerto));
        servidor.iniciarServidor();
        sc.close();
    }
}