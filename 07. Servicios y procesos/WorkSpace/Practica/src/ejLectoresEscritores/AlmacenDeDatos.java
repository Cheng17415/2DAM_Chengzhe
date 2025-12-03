package ejLectoresEscritores;

public class AlmacenDeDatos {
    private String datos = "Datos iniciales";
    private int lectoresActivos = 0;
    private boolean escritorActivo = false;
    private int escritoresEsperando = 0; // Para la prevención de inanición

    // ================== MÉTODOS DE LECTURA ==================

    public synchronized void iniciarLectura() throws InterruptedException {
        // Los Lectores esperan si:
        // 1. Hay un escritor activo (escritorActivo == true).
        // 2. Hay escritores esperando (escritoresEsperando > 0) para darles prioridad (Starvation prevention).
        while (escritorActivo || escritoresEsperando > 0) {
            wait();
        }
        lectoresActivos++;
        System.out.println("-> Lector entra. Lectores Activos: " + lectoresActivos);
    }

    public synchronized void finalizarLectura() {
        lectoresActivos--;
        System.out.println("<- Lector sale. Lectores Activos: " + lectoresActivos);
        // Si no quedan lectores, notifica a los hilos esperando (escritores).
        if (lectoresActivos == 0) {
            notifyAll(); // Despierta a todos (principalmente a escritores)
        }
    }

    public String leer() {
        // Simulación de lectura
        return this.datos;
    }

    // ================== MÉTODOS DE ESCRITURA ==================

    public synchronized void iniciarEscritura() throws InterruptedException {
        escritoresEsperando++; // Se anuncia que un escritor está esperando.

        // El Escritor espera si:
        // 1. Hay otro escritor activo (escritorActivo == true).
        // 2. Hay lectores activos (lectoresActivos > 0).
        while (escritorActivo || lectoresActivos > 0) {
            wait();
        }
        
        escritoresEsperando--; // Ya tiene el permiso, deja de esperar.
        escritorActivo = true;
        System.out.println("\n*** ESCRITOR ENTRA (EXCLUSIVO) ***");
    }

    public synchronized void finalizarEscritura() {
        escritorActivo = false;
        System.out.println("*** ESCRITOR SALE ***\n");
        // Despierta a todos:
        // - Si hay escritores esperando, uno de ellos tomará el turno.
        // - Si no hay escritores, los lectores pueden entrar.
        notifyAll();
    }

    public void escribir(String nuevosDatos) {
        // Simulación de escritura
        this.datos = nuevosDatos;
        System.out.println("ESCRITOR ha cambiado los datos a: \"" + this.datos + "\"");
    }
}