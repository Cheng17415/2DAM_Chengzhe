package ejFilosofos2;

public class Filosofo extends Thread{
	private int comensal;
	private Mesa mesa;
	public Filosofo(int comensal, Mesa mesa) {
		super();
		this.comensal = comensal;
		this.mesa = mesa;
	}
	public int getComensal() {
		return comensal;
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			pensar();
			mesa.cogerTenedores(comensal);
			comer();
			mesa.dejarTenedores(comensal);
			System.out.println("Filosofo " + comensal + " ha dejado de comer");
		}
		
	}
	
	public void pensar() {
		System.out.println("Filosofo " + comensal + " está pensando");
		try {
			sleep((long) (Math.random() * 4000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void comer() {
		System.out.println("Filosofo " + comensal + " está comiendo");
		try {
			sleep((long) (Math.random() * 4000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
