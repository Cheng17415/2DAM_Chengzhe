package Clases;

public class Hilo extends Thread{
	Cuenta c1,c2;
	String nomHilo;
	
	public Hilo(Cuenta c1, Cuenta c2, String nomHilo) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.nomHilo = nomHilo;
	}

	public String getNomHilo() {
		return nomHilo;
	}

	public void setNomHilo(String nomHilo) {
		this.nomHilo = nomHilo;
	}
	
	@Override
	public void run() {
		super.run();
		int cantidad = 10;
		int numTransF = 0;
		for (int i = 0; i < 10000; i++) {
			if(GestorTransacciones.transferencia(c1,c2,cantidad)) {
				numTransF++;
			}
		}
		System.out.printf("Fin de %s, %d transferencias hechas de %s a %s. \n",this.getNomHilo(), numTransF,c1.getNumCuenta(),c2.getNumCuenta());
	}
	
}
