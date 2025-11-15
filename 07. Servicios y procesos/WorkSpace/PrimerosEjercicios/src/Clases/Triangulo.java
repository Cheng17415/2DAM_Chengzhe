package Clases;

public class Triangulo extends FiguraGeometrica {
	private double altura;
	public Triangulo(double dato1, double altura) {
		super(dato1);
		this.altura = altura;
	}

	
	public double getAltura() {
		return altura;
	}


	public void setAltura(double altura) {
		this.altura = altura;
	}


	@Override
	public double perimetro() {

		return 0;
	}

	@Override
	public double area() {
		return (dato1 * altura) /2;
	}

}
