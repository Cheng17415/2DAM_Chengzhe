package ejercicioElectrodomesticos;

public class Electrodomestico {
	//Atributos
	protected float precio_base; //€
	protected String color;
	protected char consumo;
	protected float peso; //Kilo
	
	//Atributos por defecto
	private final float PRECIO_DEFECTO = 100; 
	private final String COLOR_DEFECTO = "blanco";
	private final char CONSUMO_DEFECTO = 'F';
	private final float PESO_DEFECTO = 5; 
	
	private final String[] COLORES_DISPONIBLES = {"blanco", "negro", "rojo", "azul", "gris"};
	
	//Constructores
	public Electrodomestico() {
		precio_base = PRECIO_DEFECTO;
		color= COLOR_DEFECTO;
		consumo = CONSUMO_DEFECTO;
		peso = PESO_DEFECTO;
	}
	
	public Electrodomestico(float precio_base, float peso) {
		super();
		this.precio_base = precio_base;
		this.peso = peso;
		color= COLOR_DEFECTO;
		consumo = CONSUMO_DEFECTO;
	}
	
	public Electrodomestico(float precio_base, String color, char consumo, float peso) {
		super();
		this.precio_base = precio_base;
		this.color = comprobarColor(COLORES_DISPONIBLES, color);
		this.consumo = comprobarConsumoEnergetico(consumo);
		this.peso = peso;
	}
	
	//Métodos get
	public float getPrecio_base() {
		return precio_base;
	}
	public String getColor() {
		return color;
	}
	public char getConsumo() {
		return consumo;
	}
	public float getPeso() {
		return peso;
	}
	
	//Otros métodos
	private String comprobarColor(String[] disponibles, String color) {
		for (String colorValido:disponibles) {
			if(color.toLowerCase() == colorValido) {
				return color.toLowerCase();
			}
		}
		return COLOR_DEFECTO;
	}
	
	private char comprobarConsumoEnergetico(char letra){
		letra = Character.toUpperCase(letra);
		if(letra >= 'A'  && letra <= 'F') {
			return letra;
		}
		return CONSUMO_DEFECTO;
	}
	
	public double precioFinal() {
		double preFinal = precio_base;
		switch(consumo) {
		case 'A':
			preFinal += 100;
			break;
		case 'B':
			preFinal += 80;
			break;
		case 'C':
			preFinal += 60;
			break;
		case 'D':
			preFinal += 50;
			break;
		case 'E':
			preFinal += 30;
			break;
		case 'F':
			preFinal += 10;
			break;
		}
		
		if(peso > 0 && peso <20) {
			preFinal +=10;
		}else if(peso >20 && peso <50) {
			preFinal +=50;
		}else if(peso >50 && peso <80) {
			preFinal +=80;
		}else if(peso > 80) {
			preFinal +=100;
		}
	return preFinal;
	}

	@Override
	public String toString() {
		return "Electrodomestico [precio_base=" + precio_base + "€, color=" + color + ", consumo=" + consumo + ", peso="
				+ peso + "kg]";
	}

}
