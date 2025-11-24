package _01_hilos;

public class Ejer05 {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Tarea2("Tarea 1"));
		Thread t2 = new Thread(new Tarea2("Tarea 2"));
		t1.start();
		try {
			t1.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		t2.start();
		try {
			t2.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Tarea2 implements Runnable{
	private String nombre;
	public Tarea2(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public void run() {
		for (int i = 0; i <10; i++) {
			System.out.println("Soy el hilo " + i + " " + nombre);
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
