package clases;

public class ProductorCoches implements Runnable {
    
    private FabricaCoches fabrica;
    private static int contadorMatriculas = 1000;
    private final int LIMITE_PRODUCCION = 5; 
    public ProductorCoches(FabricaCoches fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        for (int i = 0; i < LIMITE_PRODUCCION; i++) {
            
            String matricula = "MAT-" + (++contadorMatriculas);
            Coches nuevoCoche = new Coches(matricula, "Marca-P", "Modelo-" + (contadorMatriculas % 3));
            
            try {
                fabrica.ponerCoche(nuevoCoche);
                Thread.sleep(100); 
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                break; 
            }
        }
        System.out.println("PRODUCTOR FINALIZADO: Se han generado " + LIMITE_PRODUCCION + " coches.");
    }
}