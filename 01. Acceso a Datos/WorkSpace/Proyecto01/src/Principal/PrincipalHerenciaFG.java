package Principal;

import java.util.ArrayList;
import java.util.Scanner;

import Clases.*;

public class PrincipalHerenciaFG {
	public static Scanner sc=new Scanner(System.in);
	
	public static void submenu(ArrayList<FiguraGeometrica> f) {
		String[] items= {"1.-Cuadrado","2.-Rectangulo","3.-Circulo","4.-Cubo","5.-Salir"};
		int op;
		double vdato1,vdato2;
		String basura="";
		boolean seguir=true;
		while(seguir) {
		    op=Biblioteca.menu(sc, items);
		    switch(op) {
		    case 1: //cuadrados
		    	System.out.print("Dime el lado "); 
		    	vdato1=Double.valueOf(sc.nextLine());
		    	f.add(new Cuadrado(vdato1));
		    	break;
		    case 2: //rectangulo
		    	System.out.print("Dime el lado1 ");vdato1=Double.valueOf(sc.nextLine());
		    	System.out.print("Dime el lado2 ");vdato2=Double.valueOf(sc.nextLine());
		    	f.add(new Rectangulo(vdato1,vdato2));
		    	break;
		    case 3: //circulos
		      	System.out.print("Dime el radio "); 
		    	vdato1=Double.valueOf(sc.nextLine());
		    	f.add(new Circulo(vdato1));
		    	break;
		    case 4: //cubox
		       	System.out.print("Dime el lado del cubo "); 
		    	vdato1=Double.valueOf(sc.nextLine());
		    	f.add(new Cubo(vdato1));
		    	break;
		    case 5:
		    	seguir=false;
		    	break;
		    }
		    if (op!=5) {
		    	System.out.print("Presione una tecla para continuar ");
		    	basura=sc.nextLine();
		    }
		    
	    }
		
		
		
		
		
		
		
		
		
	}
	
	public static void imprimir(FiguraGeometrica fg) {
		System.out.printf("perimetro=%.2f area=%.2f %s\n", fg.perimetro(), fg.area(), fg.toString());

	}

	public static void cuantos(ArrayList<FiguraGeometrica> f) {
		int ncuadrados = 0, ncubos = 0, ncirculos = 0, nrectangulos = 0;
		for (FiguraGeometrica figu : f) {
			if (figu instanceof Cubo) {
				ncubos++;
			} else if (figu instanceof Cuadrado) {
				ncuadrados++;
			} else if (figu instanceof Circulo) {
				ncirculos++;
			} else if (figu instanceof Rectangulo) {
				nrectangulos++;
			}
		}
		System.out.println("Cuadrados " + ncuadrados);
		System.out.println("Cubos " + ncubos);
		System.out.println("Circulos " + ncirculos);
		System.out.println("Rectangulos " + nrectangulos);

	}

	public static void imprimir(ArrayList<FiguraGeometrica> f, String cual) {

		for (FiguraGeometrica figu : f) {
			if (cual.compareToIgnoreCase("CUADRADO") == 0 && figu instanceof Cuadrado) {
				imprimir(figu);
			} else if (cual.compareToIgnoreCase("CUBO") == 0 && figu instanceof Cubo) {
				imprimir(figu);

			} else if (cual.compareToIgnoreCase("CIRCULO") == 0 && figu instanceof Circulo) {
				imprimir(figu);
			} else if (cual.compareToIgnoreCase("RECTANGULO") == 0 && figu instanceof Rectangulo) {
				imprimir(figu);
			}
		}
	}

	public static void imprimir(ArrayList<FiguraGeometrica> f) {
		for (FiguraGeometrica figu : f) {
			System.out.printf("perimetro=%.2f area=%.2f %s\n", figu.perimetro(), figu.area(), figu.toString());
			if (figu instanceof Cubo)
				System.out.println("Volumen=" + ((Cubo) figu).Volumen());
		}
	}

	public static void main(String[] args) {
		ArrayList<FiguraGeometrica> figuras = new ArrayList<FiguraGeometrica>();
		figuras.add(new Cubo(10));
	    String[] opciones= {"1.-Añadir FG","2.-Listar","3.-Listar con tipo","4.-Salir"};
	    String cual="";
	    int op;
	    boolean seguir=true;
	    String basura="";
	    while(seguir) {
		    op=Biblioteca.menu(sc, opciones);
		    switch(op) {
		    case 1: //añadir
		    	submenu(figuras);
		    	break;
		    case 2: //Listar
		    	imprimir(figuras);
		    	break;
		    case 3: //Listar con tipo
		    	System.out.print("Dime el tipo de figura");
		    	cual=sc.nextLine();
		    	imprimir(figuras,cual.toUpperCase());
		    	break;
		    case 4:
		    	seguir=false;
		    	break;
		    }
		    if (op!=4) {
		    	System.out.print("Presione una tecla para continuar ");
		    	basura=sc.nextLine();
		    }
		    
	    }
	    
	    
	    
		

	    
	    
	    
	    
	}

}
