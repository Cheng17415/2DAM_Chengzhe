package ejFilosofos;

public class Filosofo extends Thread{
	private String nombre;
	private Object izq;
	private Object der;
	
	
	public Filosofo(String nombre, Object izq, Object der) {
		super();
		this.nombre = nombre;

		this.izq = izq;
		this.der = der;
	}
	public String getNombre() {
		return nombre;
	}
	
	public Object getIzq() {
		return izq;
	}

	public Object getDer() {
		return der;
	}

	@Override
	public void run() {
		super.run();
		while(true) {
			System.out.println("Filosofo " + nombre + " está pensando");
			try {
				sleep(((long) (Math.random() * 4000)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Filosofo " + nombre + " quiere comer");
			try {
				intentarComer(this, izq, der);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	public static void intentarComer(Filosofo f, Object p1, Object p2) throws InterruptedException {
		synchronized(p1) {
			synchronized(p2) {
				System.out.println("El filosofo " +f.getNombre() + " está comiendo");
				sleep(((long) (Math.random() * 4000)));
				System.out.println("El filosofo " +f.getNombre() + " ha terminado de comer");
			}
		}
	}
	
}
