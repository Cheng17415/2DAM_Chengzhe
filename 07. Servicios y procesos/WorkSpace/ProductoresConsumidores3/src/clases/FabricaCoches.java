package clases;

import java.util.ArrayList;

public class FabricaCoches {
    
    // Almacenamiento: Usa la clase Coche correcta
	private final int CAPACIDAD_MAXIMA = 5; // Definimos un tamaño fijo para el Buffer
	private ArrayList<Coches> coches = new ArrayList<Coches>(CAPACIDAD_MAXIMA);


    // Método para el PRODUCTOR: Poner Coche
    public synchronized void ponerCoche(Coches coche) throws InterruptedException {
        
        // El Productor espera si el Buffer está LLENO
        while (coches.size() == CAPACIDAD_MAXIMA) {
            System.out.println("Fábrica llena. Productor esperando... (Capacidad: " + CAPACIDAD_MAXIMA + ")");
            wait();
        }
        
        coches.add(coche);
        System.out.println("El productor AGREGÓ: " + coche);
        
        // Notificar al Consumidor que hay un nuevo elemento disponible
        notifyAll(); // Usamos notifyAll() para despertar a todos los hilos en espera (Productores/Consumidores)
    }
    
    // Método para el CONSUMIDOR: Sacar Coche
    public synchronized Coches sacarCoche() throws InterruptedException {
        
        // El Consumidor espera si el Buffer está VACÍO
        while (coches.isEmpty()) {
            System.out.println("Fábrica vacía. Consumidor esperando...");
            wait();
        }
        
        // Retirar el coche (siempre el primero que entró)
        Coches cocheRetirado = coches.remove(0); // remove(0) elimina y devuelve el primer elemento (FIFO)
        System.out.println("El consumidor RETIRÓ: " + cocheRetirado);
        
        notifyAll();
        
        return cocheRetirado;
    }
}