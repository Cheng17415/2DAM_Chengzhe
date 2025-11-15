package principal;

public class tareaHilos {
	public static void main(String [] args) {
		System.out.println("hola");
		
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


	synchronized public int getNumOculto() {
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
class hilo extends Thread{
	public int num;
	private final NumeroOculto oculto;
	
	public hilo(int num, NumeroOculto oculto) {
		super();
		this.num = num;
		this.oculto = oculto;
	}


	@Override
	public void run(){
		
	}
}
