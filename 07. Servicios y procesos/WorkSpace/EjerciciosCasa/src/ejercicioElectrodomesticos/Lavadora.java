package ejercicioElectrodomesticos;

public class Lavadora extends Electrodomestico {
	//Atributos
	private float carga; //Kilo
	private final float CARGA_DEFECTO = 5;
	
	//Constructores
	public Lavadora() {
		super();
		carga = CARGA_DEFECTO;
	}
	public Lavadora(float precio_base, float peso) {
		super(precio_base, peso);
		carga = CARGA_DEFECTO;
	}
	public Lavadora(float precio_base, String color, char consumo, float peso, float carga) {
		super(precio_base, color, consumo, peso);
		this.carga = carga;
	}
	
	//Métodos get
	public float getCarga() {
		return carga;
	}
	
	//Otros métodos
	public double precioFinal() {
		double preFinal = super.precioFinal();
		if(carga > 30) preFinal += 50;
		return preFinal;
	}
	
	@Override
	public String toString() {
		return "Lavadora [precio_base=" + precio_base + "€, color=" + color + ", consumo=" + consumo
				+ ", peso=" + peso + "kg, carga=" + carga + "kg]";
	}
	
	
	
}
