package Clases;

public class Cuadrado extends FiguraGeometrica {

	public Cuadrado(double dato1) {		super(dato1);	}

	public double perimetro() {	return 4*dato1;	}
	public double area() {	return Math.pow(dato1, 2);	}

	public String toString() {		return "Cuadrado lado="+dato1;	}
	
}
