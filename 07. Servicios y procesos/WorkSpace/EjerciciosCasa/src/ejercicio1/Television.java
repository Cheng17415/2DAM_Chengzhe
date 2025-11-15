package ejercicio1;

public class Television extends Electrodomestico {
	//Atributos
	private float resolucion; //Pulgada
	private boolean TDT;
	
	private final float RESOLUCION_DEFECTO = 20;
	private final boolean TDT_DEFECTO = false;
	
	//Constructores
	public Television() {
		super();
		resolucion = RESOLUCION_DEFECTO;
		TDT = TDT_DEFECTO;
	}
	public Television(float precio_base, float peso) {
		super(precio_base, peso);
		resolucion = RESOLUCION_DEFECTO;
		TDT = TDT_DEFECTO;
	}
	public Television(float precio_base, String color, char consumo, float peso, float resolucion, boolean TDT) {
		super(precio_base, color, consumo, peso);
		this.resolucion = resolucion;
		this.TDT = TDT;
	}
	
	//Métodos get
	public float getResolucion() {
		return resolucion;
	}
	public boolean isTDT() {
		return TDT;
	}
	
	//Otros métodos
	public double precioFinal() {
		double preFinal = super.precioFinal();
		if(resolucion > 40) preFinal *=1.3;
		if (TDT) preFinal += 50;
		return preFinal;
	}
	
	@Override
	public String toString() {
		return "Television [precio_base=" + precio_base + "€, color="
				+ color + ", consumo=" + consumo + ", peso=" + peso + "kg" + ", resolucion=" + resolucion + " pulgadas, TDT=" + TDT + "]" ;
	}
	
	
}
