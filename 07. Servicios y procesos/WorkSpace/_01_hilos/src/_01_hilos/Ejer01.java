package _01_hilos;

public class Ejer01 {
	public static void main(String[] args) {
		/*Introduccion a hilos*/
		//Primera implementación
		// Thread h1 = new Thread(new Ejer01().new RunnableImpl());
		// Thread h2 = new Thread(new Ejer01().new RunnableImpl());
		
		//Segunda implementación
		Tarea h1 = new Tarea();
		Tarea h2 = new Tarea();
		
		h1.setName("Hilo 1");
		h2.setName("Hilo 2");
		h1.start();
		h2.start();

		System.out.println("Yo soy el hilo principal y sigo haciendo mi trabajo");
		System.out.println("Fin del hilo principal");
	}
	/*
	 * Primera implementación
	 * private class RunnableImpl implements Runnable{ public void run() { num+=
	 * 100; System.out.println(Thread.currentThread().getName() +
	 * " ejecutandose, num: " + num); } }
	 */
}

//Segunda implementación
class Tarea extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Soy un hilo y esto es lo que hago " + getName());
		}
	}
}
