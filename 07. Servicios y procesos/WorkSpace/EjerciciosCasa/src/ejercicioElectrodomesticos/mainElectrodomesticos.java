package ejercicioElectrodomesticos;


public class mainElectrodomesticos {

	public static void main(String[] args) {
		//Instanciar
		Electrodomestico [] ed = new Electrodomestico[10];
		asignarValoresArray(ed);
		recorrerArray(ed);
		precioClase(ed);
	}
	
	public static void asignarValoresArray(Electrodomestico[] ed) {
		ed[0] = new Electrodomestico();
		ed[1] = new Electrodomestico(150,20);
		ed[2] = new Electrodomestico(200, "rojo", 'B',35);
		ed[3] = new Lavadora();
		ed[4] = new Lavadora(300,65);
		ed[5] = new Lavadora(350,"negro",'F', 85, 35);
		ed[6] = new Television();
		ed[7] = new Television(500,5);
		ed[8] = new Television(400,"gris",'A',10, 45, true);
		ed[9] = new Lavadora(600, "azul",'D', 60, 20);
	}
	
	public static void recorrerArray(Electrodomestico[] ed) {
		for (Electrodomestico e : ed) {
			System.out.println(e.toString());
			System.out.printf("El precio final es de %.2f€\n\n",e.precioFinal());
		}
	}
	
	public static void precioClase(Electrodomestico[] ed) {
		double pre_lav = 0, pre_tel = 0, pre_ele = 0; 
		for (Electrodomestico e : ed) {
			if (e instanceof Lavadora) {
				pre_lav += e.precioFinal();
			} else if(e instanceof Television) {
				pre_tel += e.precioFinal();
			}
			
			pre_ele += e.precioFinal();
		}
		System.out.printf("Las lavadoras tienen un precio final de %.2f€\n", pre_lav);
		System.out.printf("Las televisiones tienen un precio final de %.2f€\n", pre_tel);
		System.out.printf("Los electrodomesticos tienen un precio final de %.2f€\n", pre_ele);
	}

}
