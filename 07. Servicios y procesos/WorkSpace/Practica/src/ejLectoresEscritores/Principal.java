package ejLectoresEscritores;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        AlmacenDeDatos almacen = new AlmacenDeDatos();

        final int NUM_LECTORES = 5;
        final int NUM_ESCRITORES = 2;

        Lector[] lectores = new Lector[NUM_LECTORES];
        Escritor[] escritores = new Escritor[NUM_ESCRITORES];

        // 1. Crear e iniciar lectores
        for (int i = 0; i < NUM_LECTORES; i++) {
            lectores[i] = new Lector("Lector-" + (i + 1), almacen);
            lectores[i].start();
        }
        
        // 2. Crear e iniciar escritores
        for (int i = 0; i < NUM_ESCRITORES; i++) {
            escritores[i] = new Escritor("Escritor-" + (i + 1), almacen);
            escritores[i].start();
        }

        // 3. Esperar la finalización (opcional para un programa de prueba)
        for (Lector l : lectores) {
            l.join();
        }
        for (Escritor e : escritores) {
            e.join();
        }

        System.out.println("\n--- SIMULACIÓN FINALIZADA ---");
        System.out.println("Estado final de los datos: " + almacen.leer());
    }
}