package Clases;

public class Cubo extends Cuadrado {

	public Cubo(double dato1) {
		super(dato1);
		// TODO Auto-generated constructor stub
	}
	public double Volumen() { return Math.pow(dato1, 3);}
	@Override
	public String toString() {
		return "Cubo lado="+dato1;
	}
	

}
