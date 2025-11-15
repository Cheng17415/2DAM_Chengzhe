package ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

import Clases.*;

public class PrincipalHerencia {

	public static Scanner sc = new Scanner(System.in);

	public static void imprimir(ArrayList<FiguraGeometrica> fg) {
		for (FiguraGeometrica fig : fg) {
			System.out.printf("perimetro=%.2f area=%.2f, %s\n", fig.perimetro(), fig.area(), fig.toString());
			if(fig instanceof Cubo) System.out.println("Volumen:" + ((Cubo) fig).volumen());
		}
		
	}

	private static void imprimir(FiguraGeometrica fig) {
		System.out.printf("perimetro=%.2f area=%.2f, %s\n", fig.perimetro(), fig.area(), fig.toString());
		if(fig instanceof Cubo) System.out.println("Volumen:" + ((Cubo) fig).volumen());
	}

	public static void imprimir(ArrayList<FiguraGeometrica> fg, String cual) {

		for (FiguraGeometrica fig : fg) {
			if (cual.compareToIgnoreCase("CUBO") == 0 && fig instanceof Cubo) {
				imprimir(fig);
			} else if (cual.compareToIgnoreCase("CIRCULO") == 0 && fig instanceof Circulo) {
				imprimir(fig);
			} else if (cual.compareToIgnoreCase("CUADRADO") == 0 && fig instanceof Cuadrado) {
				imprimir(fig);
			} else if (cual.compareToIgnoreCase("RECTANGULO") == 0 && fig instanceof Rectangulo) {
				imprimir(fig);
			}
		}
	}

	public static void addFig(ArrayList<FiguraGeometrica> figuras) {
		int opc = -1;

		System.out.println("¿Qué figura geométrica quiere añadir?");
		String[] opciones = { "1. Circulo", "2. Rectangulo", "3. Cuadrado", "4. Menu", "5. Salir" };
		opc = Biblioteca.menu(sc, opciones);
		switch (opc) {
		case 1:
			System.out.println("¿Cuál es el radio del círculo?");
			figuras.add(new Circulo(Double.valueOf(sc.nextLine())));
			break;
		case 2:
			System.out.println("¿Cuál es el primer lado?");
			double lado = Double.valueOf(sc.nextLine());
			System.out.println("¿Cuál es el segundo lado?");
			figuras.add(new Rectangulo(lado, Double.valueOf(sc.nextLine())));
			break;
		case 3:
			System.out.println("¿Cuál es el lado del cuadrado?");
			figuras.add(new Cuadrado(Double.valueOf(sc.nextLine())));
			break;
		case 4:
			System.out.println("¿Cuál es el lado del cubo?");
			figuras.add(new Cubo(Double.valueOf(sc.nextLine())));
			break;
		}
	}

	public static void main(String[] args) {

		ArrayList<FiguraGeometrica> figuras = new ArrayList<>();

		int opc = -1;
		while (opc != 4) {

			System.out.println("Decida lo opción que quieres realizar a las figuras geométricas");
			String[] opciones = { "1. Añadir", "2. Listar", "3. Buscar tipo de figura", "4. Salir" };
			opc = Biblioteca.menu(sc, opciones);
			switch (opc) {
			case 1:
				addFig(figuras);
				break;
			case 2:
				imprimir(figuras);
				break;
			case 3:
				System.out.println("¿Qué figura geométrica quiere imrpimir?");
				imprimir(figuras, sc.nextLine());
				break;
			}
		}

		/*
		 * 1. Añadir figuras geometricas 2. Buscar todas las figuras geometricas 3.
		 * Pedir nombre de la figura geometrica que quiero imprimir y solo mostrar la
		 * figura que pedimos 4. Salir
		 */



	}

}
