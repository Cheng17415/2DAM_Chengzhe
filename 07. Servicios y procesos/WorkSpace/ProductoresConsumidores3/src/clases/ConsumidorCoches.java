package clases;

public class ConsumidorCoches implements Runnable {
    
    private FabricaCoches fabrica;
    private final int LIMITE_CONSUMO; 
    public ConsumidorCoches(FabricaCoches fabrica, int limite) {
        this.fabrica = fabrica;
        this.LIMITE_CONSUMO = limite; 
    }

    @Override
    public void run() {
        Coches cocheConsumido;
        
        for (int i = 0; i < LIMITE_CONSUMO; i++) {
            try {
                cocheConsumido = fabrica.sacarCoche();
                Thread.sleep(300); 
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("CONSUMIDOR FINALIZADO: Se han procesado " + LIMITE_CONSUMO + " coches.");
    }
}