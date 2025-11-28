package clases;

public class Puerta {
	boolean ocupada;
	
	public Puerta() {this.ocupada = false;}
	
	public synchronized void liberarPuerta() {ocupada = false;}
	
	public synchronized boolean intentarEntrar() {
		if(!ocupada) {
			ocupada = true;
			return true;
		}
		return false;
	}
}
