package ejFilosofos2;

public class Principal {

	public static void main(String[] args) {
		final int NFILOSOFOS = 5;
		Mesa mesa = new Mesa(NFILOSOFOS);
		Filosofo [] filosofos = new Filosofo[NFILOSOFOS];
		
		for (int i = 0; i < filosofos.length; i++) {
			filosofos[i] = new Filosofo(i, mesa);
			
		}
		for (Filosofo filosofo : filosofos) {
			filosofo.start();
		}
	}

}
