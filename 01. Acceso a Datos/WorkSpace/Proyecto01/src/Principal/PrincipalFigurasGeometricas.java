package primero;

import java.util.ArrayList;
import java.util.Scanner;

import clase.*;

public class PrincipalFigurasGeometricas {

	static Scanner sc = new Scanner(System.in);

	    public static void añadirFiguras(ArrayList<FiguraGeometrica> f) {
	        while (true) {
	            System.out.print("\n¿Que figura desea añadir? [1]Circulo - [2]Rectangulo - [3]Cuadrado - [4]Cubo - [0]Salir: ");
	            int figuraIngresar = Integer.parseInt(sc.nextLine());

	            if (figuraIngresar == 0) break;

	            switch (figuraIngresar) {
	                case 1:
	                    System.out.print("Ingrese el radio del círculo: ");
	                    int radio = Integer.parseInt(sc.nextLine());
	                    f.add(new Circulo(radio));
	                    break;
	                case 2:
	                    System.out.print("Ingrese el ancho del rectángulo: ");
	                    int ancho = Integer.parseInt(sc.nextLine());
	                    System.out.print("Ingrese el alto del rectángulo: ");
	                    int alto = Integer.parseInt(sc.nextLine());
	                    f.add(new Rectangulo(ancho, alto));
	                    break;
	                case 3:
	                    System.out.print("Ingrese el lado del cuadrado: ");
	                    int ladoCuadrado = Integer.parseInt(sc.nextLine());
	                    f.add(new Cuadrado(ladoCuadrado));
	                    break;
	                case 4:
	                    System.out.print("Ingrese el lado del cubo: ");
	                    int ladoCubo = Integer.parseInt(sc.nextLine());
	                    f.add(new Cubo(ladoCubo));
	                    break;
	                default:
	                    System.out.println("Opción inválida.");
	                    break;
	            }

	            System.out.println("Figura añadida correctamente.\n");
	        }
	    }

	    public static void mostrarUnaSolaFigura(ArrayList<FiguraGeometrica> f) {
	        System.out.print("Indique la figura que desea visualizar (Cuadrado, Circulo, Rectangulo, Cubo): ");
	        String tipoFigura = sc.nextLine();

	        boolean encontrado = false;
	        for (FiguraGeometrica figura : f) {
	            if (tipoFigura.equalsIgnoreCase("Cuadrado") && figura instanceof Cuadrado
	                || tipoFigura.equalsIgnoreCase("Circulo") && figura instanceof Circulo
	                || tipoFigura.equalsIgnoreCase("Rectangulo") && figura instanceof Rectangulo
	                || tipoFigura.equalsIgnoreCase("Cubo") && figura instanceof Cubo) {

	                imprimirFigu(figura);
	                encontrado = true;
	            }
	        }

	        if (!encontrado) {
	            System.out.println("No se encontró ninguna figura de tipo " + tipoFigura);
	        }
	    }

	    public static void menu(ArrayList<FiguraGeometrica> figuras) {
	        int opcion;
	        boolean continuar = true;

	        String[] opciones = {
	            "1. Añadir figuras geométricas",
	            "2. Mostrar todas las figuras",
	            "3. Mostrar solo un tipo de figura",
	            "4. Salir"
	        };

	        while (continuar) {
	            opcion = Biblioteca.menu(sc, opciones);  

	            switch (opcion) {
	                case 1:
	                    añadirFiguras(figuras);
	                    break;
	                case 2:
	                    System.out.println("\nLista completa de figuras:");
	                    for (FiguraGeometrica fg : figuras) {
	                        imprimirFigu(fg);
	                    }
	                    break;
	                case 3:
	                    mostrarUnaSolaFigura(figuras);
	                    break;
	                case 4:
	                    continuar = false;
	                    System.out.println("Saliendo del programa.");
	                    break;
	                default:
	                    System.out.println("Opción inválida.");
	            }
	        }
	    }

	    public static void imprimirFigu(FiguraGeometrica fg) {
	        System.out.printf("Perímetro = %.2f, Área = %.2f, %s\n", fg.perimetro(), fg.area(), fg.toString());
	    }

	    public static void main(String[] args) {
	        ArrayList<FiguraGeometrica> figuras = new ArrayList<>();

	        figuras.add(new Circulo(10));
	        figuras.add(new Circulo(20));
	        figuras.add(new Rectangulo(5, 10));
	        figuras.add(new Cuadrado(15));
	        figuras.add(new Cubo(7));

	        menu(figuras);
	    }
	}
