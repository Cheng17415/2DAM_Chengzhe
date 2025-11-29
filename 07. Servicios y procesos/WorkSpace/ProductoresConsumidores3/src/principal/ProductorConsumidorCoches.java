package principal;

import clases.ConsumidorCoches;
import clases.FabricaCoches;
import clases.ProductorCoches;

public class ProductorConsumidorCoches {
	
	private static final int COCHES_A_GENERAR = 5; 

	/*Una productor de coches va a generar 5 coches, los cuales el consumidor consume.*/
	public static void main(String[] args) {
        
		FabricaCoches fabrica = new FabricaCoches();
        
		ProductorCoches productor = new ProductorCoches(fabrica); 
        
		ConsumidorCoches consumidor = new ConsumidorCoches(fabrica, COCHES_A_GENERAR);
        
		new Thread(productor).start();
		new Thread(consumidor).start();
	}
}