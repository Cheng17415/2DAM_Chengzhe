package Principal;

public class Sumador {
	public static double mensualidad2(double capital, double ianual, double anios) {
		double iMensual = ianual/1200;
		double t = anios * 12;
		return (capital * iMensual)/(1 - Math.pow((1 + iMensual), -t));
	}
	
	public static double mensualidad(String cadena) {
		String campos [] = cadena.split(":");
		double capital = Double.valueOf(campos[0]);
		double interes = Double.valueOf(campos[1]);
		double anios = Double.valueOf(campos[2]);
		return mensualidad2(capital, interes, anios);
	}
	public static int sumaSimple(String cad){
		int suma=0;
		for (int i=0; i<cad.length(); i++){
			suma+=cad.codePointAt(i);
		}
		return suma;
	}
}
