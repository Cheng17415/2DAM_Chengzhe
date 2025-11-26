package clases;

public class Puerta {
	boolean ocupada;
	
	public Puerta() {this.ocupada = false;}
	
	public boolean estaOcupada() {return ocupada;}
	
	public synchronized void liberarPuerta() {ocupada = false;}
	
	public synchronized boolean intentarEntrar() {
		ocupada =! ocupada;
		return ocupada;
	}
}
