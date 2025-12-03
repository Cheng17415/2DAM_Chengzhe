package ejLectoresEscritores;

public class Lector extends Thread {
    private AlmacenDeDatos almacen;
    private String nombre;

    public Lector(String nombre, AlmacenDeDatos almacen) {
        this.nombre = nombre;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) { // Lee dos veces
            try {
                almacen.iniciarLectura();
                System.out.println(nombre + " lee: \"" + almacen.leer() + "\"");
                Thread.sleep(100); // Simula el tiempo de lectura
                almacen.finalizarLectura();
                Thread.sleep(1000); // Tiempo entre intentos de lectura
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}