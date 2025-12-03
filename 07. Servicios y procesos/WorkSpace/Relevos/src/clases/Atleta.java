package clases;

public class Atleta extends Thread{
	private String nombre;
	private double duracion;
	private Object testigo;
	
	public Atleta(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Atleta(String nombre, Object testigo) {
		super();
		this.nombre = nombre;
		this.testigo = testigo;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Object getTestigo() {
		return testigo;
	}

	public void setTestigo(Object testigo) {
		this.testigo = testigo;
	}

	public double getDuracion() {
		return duracion;
	}

	@Override
	public void run() {
		super.run();
		System.out.println(nombre + " ha empezado la carrera.");
		long ini = System.currentTimeMillis();
		try {
			long n = (long)(Math.random() *(11-9 +1)) + 9;
			Thread.sleep(n * 1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(nombre + " ha terminado la carrera");
		synchronized(testigo) {
			System.out.println(nombre + " ha pasado el testigo.");
			testigo.notify();
			long fin = System.currentTimeMillis();
			duracion = (double)(fin - ini)/1000;
			System.out.println("Tiempo total " + duracion + " segundos");
		}
	}


	
	
}
