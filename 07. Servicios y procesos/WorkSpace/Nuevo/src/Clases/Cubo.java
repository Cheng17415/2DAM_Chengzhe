package Clases;

public class Cubo extends Cuadrado {

	public Cubo(double dato1) {
		super(dato1);
		
	}
	public double volumen() {
		return Math.pow(dato1, 3);
	}
	
	@Override
	public String toString() {
		return "Cubo [dato1=" + dato1 + "]";
	}
	
	
}
