package ejLectoresEscritores;

public class Escritor extends Thread {
    private AlmacenDeDatos almacen;
    private String nombre;
    private static int version = 1;

    public Escritor(String nombre, AlmacenDeDatos almacen) {
        this.nombre = nombre;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            // El escritor espera un poco para dar chance a que los lectores se junten
            Thread.sleep(1500); 
            
            almacen.iniciarEscritura();
            
            // Simula el tiempo de escritura y actualización
            String nuevosDatos = "Versión de " + nombre + " #" + version++;
            almacen.escribir(nuevosDatos);
            Thread.sleep(200); 
            
            almacen.finalizarEscritura();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
