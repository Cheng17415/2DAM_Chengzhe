package principal;

import clases.Atleta;

public class CarreraRelevo {
	public static void main(String[] args) {
		final int CORREDORES = 4;
		Object testigo = new Object();
		Atleta[] atletas = new Atleta[CORREDORES];
		for (int i = 0; i < atletas.length; i++) {
			atletas[i] = new Atleta("Atleta " + (i+1), testigo);
		}
		
		double carrera = 0;
		for (int i = 0; i < atletas.length; i++) {
			atletas[i].start();
			synchronized(testigo) {
				try {
					testigo.wait();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			carrera += atletas[i].getDuracion();
		}
		System.out.println("Duración total de la carrera " + carrera + " segundos.");	
	}
}
