package principal;

public class Principal {
	public static void main(String [] args) {
		int NUM_HILOS = 10;
		Hilo [] hilos = new Hilo[NUM_HILOS];
		NumeroOculto oculto = new NumeroOculto();
		
		for (int i = 0; i < NUM_HILOS; i++) hilos[i] = new Hilo(i+1, oculto);
		for (Hilo hilo : hilos) hilo.start();
		
		for (Hilo hilo : hilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf("El número oculto era %d ", oculto.getNumOculto());
	}
}

/*********************************************/
class NumeroOculto{
	private int numOculto;
	static private boolean acertado = false; 
	
	public NumeroOculto() {
		this.numOculto = aleatorio.alea(0, 100);
	}

	public NumeroOculto(int numOculto) {
		super();
		this.numOculto = numOculto;
	}

	public int getNumOculto() {
		return numOculto;
	}

	public void setNumOculto(int numOculto) {
		this.numOculto = numOculto;
	}

	synchronized public int propuestaNumero(int num) {
		if(acertado == true) return -1;
		if(num == this.numOculto) {
			acertado = true;
			return 1;
		}
		return 0;
	}
}

/*********************************************/
class aleatorio{
	public static int alea(int li,int ls) {
		return (int)(Math.random()*(ls-li +1)) +li;
	}
}

/*********************************************/
class Hilo extends Thread{
	private final int id;
	private final NumeroOculto oculto;
	private int numero;
	private int resultado;
	
	public Hilo(int id, NumeroOculto oculto) {
		super();
		this.id = id;
		this.oculto = oculto;
	}


	@Override
	public void run(){
		do {
			numero = aleatorio.alea(0, 100);
			System.out.printf("El Hilo %d propone el número %d\n", id, numero);
			resultado = oculto.propuestaNumero(numero);
			if(resultado == 1) System.out.printf("El hilo %d ha acertado!\n", id);
			else if (resultado == -1) System.out.printf("Hilo %d ha perdido. Otro hilo ha acertado\n", id);
			
		} while(resultado == 0);
		
		
	}
}
