package principal;

import clases.AtletaLiso;

public class CarreraLiso {
	public static void main(String[] args) throws InterruptedException {
		final int NUMATLETAS = 8;
		Object pistoletazo = new Object();
		AtletaLiso[] atletas = new AtletaLiso[NUMATLETAS];
		for (int i = 0; i < atletas.length; i++) {
			atletas[i] = new AtletaLiso("Dorsal " + (i+1), pistoletazo);
			atletas[i].start();	
		}
		System.out.println("preparados");
		Thread.sleep(1000);
		System.out.println("listos");
		Thread.sleep(1000);
		System.out.println("ya!");
		synchronized	(pistoletazo) {
			pistoletazo.notifyAll();
		}
		
		for (AtletaLiso atletaLiso : atletas) {
			atletaLiso.join();
		}
		AtletaLiso ganador = atletas[0];
		for (int i = 1; i < atletas.length; i++) {
			if(atletas[i].getDuracion() < ganador.getDuracion()) {
				ganador = atletas[i];
			}
			
		}
		System.out.println("Ha acabado la carrera");
		System.out.println("El ganador es " + ganador.getDorsal() + " con un tiempo de " + ganador.getDuracion());
	}
}
