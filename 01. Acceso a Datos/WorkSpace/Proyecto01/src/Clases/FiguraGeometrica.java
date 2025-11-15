package Clases;

public abstract class FiguraGeometrica {
   protected double dato1;
   
   public FiguraGeometrica(double dato1) {
	super();
	this.dato1 = dato1;
}
   
   public double getDato1() {
	return dato1;
}

   public void setDato1(double dato1) {
	this.dato1 = dato1;
   }

   public abstract double  perimetro();
   public abstract double  area();
   @Override
   public String toString() {
	return "FiguraGeometrica [dato1=" + dato1 + "]";
   }
   
}
