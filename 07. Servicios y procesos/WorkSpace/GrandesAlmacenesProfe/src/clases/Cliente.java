package clases;

import java.util.Random;

public class Cliente extends Thread{
	Puerta puerta;
	Almacen almacen;
	String nombre;
	Random generador;
	final int N_INTENTOS = 10;
	
	public Cliente(Puerta puerta, Almacen almacen, String nombre) {
		super();
		this.puerta = puerta;
		this.almacen = almacen;
		this.nombre = nombre;
		this.generador = new Random();
	}
	
	public void esperar() {
		try {
			Thread.sleep(generador.nextInt(100));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		for(int i= 0; i< N_INTENTOS; i++) {
			if(puerta.intentarEntrar()) {
				esperar();
				puerta.liberarPuerta();
				if(almacen.cogerProducto()) {
					System.out.println(nombre + " : cogí un producto");
				} else {System.out.println("No cogí nada");}
				return;
			}
			esperar();
		}
		System.out.println("Lo intente " + N_INTENTOS + " y no pude coger ninguno.");
	}
	
}
