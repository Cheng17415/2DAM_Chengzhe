package ejercicio4;

import java.util.Arrays;
import java.util.Scanner;

public class JuegoMasterMind {

    public static Combinacion c;
    public static char[] resultados;
    public static Scanner sc = new Scanner(System.in);
    
    
    
    public static void main(String[] args) {
        
        /*char[] combSecreta = {'R', 'V', 'A', 'R'};
        c = new Combinacion(combSecreta); */
         
        //Arrays.fill(resultados, '_');
        //char[] combinacion = {'A', 'R', 'V', 'R'};
        menu();
    }

    public static void menu() {

        int opcion;
        do {
            System.out.println("====== Master Mind ======");
            System.out.println("1. Jugador contra Jugador");
            System.out.println("2. Jugador contra IA");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    break;
                case 2:
                	juegoIA();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            System.out.println("\nPulse cualquier tecla para continuar...");
            sc.nextLine();
        } while (opcion != 0);
        sc.close();

    }
    public static void juegoIA() {
    	c = new Combinacion();
    	System.out.println(c.getCombSecreto());
    	resultados= new char[c.getNumColoresCombinacion()];
    	int intento = 20;
    	do {
    		System.out.println("Las posibles opciones son " + Arrays.toString(c.tablaColores));
        	System.out.println("Introduzca tu intento(Ejemplo RRAM)");
        	char[] combi = sc.nextLine().toUpperCase().toCharArray();
        	System.out.println(c.EvaluacionCombinacion(combi, resultados));
        	intento --;
    	} while(intento > 0);
    	
    }
}
