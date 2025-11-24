package _01_hilos;

public class Ejer04InterrumpirHilo {
	public static void main(String[] args) throws InterruptedException {
		//Hilo que queremos interrumpir
		Thread workerTask = new Thread(new WorkerTask(),"Trabajo");
		workerTask.start();
		//Esperamos un rato y luego interrumpimos el hilo
		Thread.sleep(2000);
		System.out.println("Hilo principal: Interrumpiendo el hilo de trabajo");
		workerTask.interrupt();
	}
}
class WorkerTask implements Runnable{
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + ": Empezando a dormir...");
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + ": Desperté normalmente.");
		} catch(InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + ": Fue interrumpido mientras ejectutaba.");
		}
		finally {
			System.out.println(Thread.currentThread().getName() + ": Finalizando.");
		}
	}
}
