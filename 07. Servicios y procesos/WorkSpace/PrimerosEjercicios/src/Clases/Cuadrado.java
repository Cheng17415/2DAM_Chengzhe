package Clases;

public class Cuadrado extends FiguraGeometrica {

	public Cuadrado(double dato1) {
		super(dato1);
	}

	@Override
	public double perimetro() {
		return 4 * dato1;
	}

	@Override
	public double area() {
		return Math.pow(dato1, 2);
	}

	@Override
	public String toString() {
		return "Cuadrado [dato1=" + dato1 + "]";
	}
	

}
