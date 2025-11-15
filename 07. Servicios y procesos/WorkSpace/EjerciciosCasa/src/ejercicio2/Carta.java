package ejercicio2;

public class Carta {
	private String palo;
	private int num;
	private String figura;

	public Carta(String palo, int num) {
		this.palo = palo;
		this.num = num;
		this.figura = obtenerFigura(num);
	}

	public Carta(String palo, String figura) {
		this.palo = palo;
		this.num = obtenerNumeroFigura(figura);
		this.figura = figura;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getFigura() {
		return figura;
	}

	public void setFigura(String figura) {
		this.figura = figura;
	}

	@Override
	public String toString() {
		if (figura == null) {
			return num + " de " + palo;
		} else {
			return figura + " de " + palo;
		}
	}

	private String obtenerFigura(int num) {
		switch (num) {
		case 8:
			return "sota";
		case 9:
			return "caballo";
		case 10:
			return "rey";
		default:
			return null;
		}
	}

	private int obtenerNumeroFigura(String figura) {
		switch (figura) {
		case "sota":
			return 8;
		case "caballo":
			return 9;
		case "rey":
			return 10;
		default:
			return -1;
		}
	}

}
