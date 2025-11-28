package ejercicio4;

import java.util.Arrays;
import java.util.Scanner;

public class JuegoMasterMind {

    public static Combinacion c;
    public static char[] resultados;
    public static Scanner sc = new Scanner(System.in);
    
    /*El juego de MasterMind consiste en adivinar las posiciones correctas con el color correcto
     * con unos intentos definidos*/
    
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
                	juegoJcJ();
                    break;
                case 2:
                	juegoIA();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            if (opcion != 3) { // Para no mostrar el mensaje al salir
                System.out.println("\nPulse cualquier tecla para continuar...");
                sc.nextLine();
            }
        } while (opcion != 3); 
        sc.close();

    }
    
    public static void juegoJcJ() {
        System.out.println("Jugador 1, introduce la combinación secreta (Ejemplo: RAVZ):");
        char[] secreta = sc.nextLine().toUpperCase().toCharArray();

        // Evitar que el Jugador 2 vea la combinación secreta
        for (int i = 0; i < 50; i++) System.out.println();

        c = new Combinacion(secreta);
        resultados = new char[c.getNumColoresCombinacion()];

        int intentos = 15;
        while (intentos > 0) {
            System.out.println("Jugador 2, introduce tu intento:");
            // Se podría usar leerIntentoValido(c) aquí también
            char[] intento = sc.nextLine().toUpperCase().toCharArray();
            
            // 1. Guardar el resultado en la variable 'resultados'
            resultados = c.EvaluacionCombinacion(intento);
            // 2. Imprimir la variable 'resultados'
            System.out.println("Resultado: " + Arrays.toString(resultados));

            // Comprobamos si ha ganado
            boolean haGanado = true;
            for (char r : resultados) {
                if (r != 'R') {
                    haGanado = false;
                    break;
                }
            }

            if (haGanado) {
                System.out.println("¡Jugador 2 ha acertado la combinación!");
                return;
            }

            intentos--;
            System.out.println("Intentos restantes: " + intentos);
        }

        System.out.println("Jugador 2 no ha conseguido acertar la combinación...");
        System.out.println("La combinación correcta era: " + Arrays.toString(c.getCombSecreto()));
    }

    
    public static void juegoIA() {
        c = new Combinacion();
        resultados = new char[c.getNumColoresCombinacion()];

        int intentos = 20;

        while (intentos > 0) {

            System.out.println("Intentos restantes: " + intentos);
            System.out.println("Colores posibles: " + Arrays.toString(c.tablaColores));

            char[] combi = leerIntentoValido(c);

            resultados = c.EvaluacionCombinacion(combi);
            System.out.println("Resultado: " + Arrays.toString(resultados));

            // Comprobar si el jugador ha ganado
            boolean ganador = true;
            for (char r : resultados) {
                if (r != 'R') {
                    ganador = false;
                    break;
                }
            }

            if (ganador) {
                System.out.println("¡ENHORABUENA! Has acertado la combinación secreta.");
                System.out.println("La combinación era: " + Arrays.toString(c.getCombSecreto()));
                return;
            }

            intentos--;
        }

        System.out.println("Te has quedado sin intentos...");
        System.out.println("La combinación correcta era: " + Arrays.toString(c.getCombSecreto()));
    }

    
    public static char[] leerIntentoValido(Combinacion c) {
        while (true) {
            System.out.println("Introduce tu intento (Ejemplo: RAVZ):");
            String entrada = sc.nextLine().toUpperCase().trim();

            // Validación de longitud
            if (entrada.length() != c.getNumColoresCombinacion()) {
                System.out.println("Error: Deben ser " + c.getNumColoresCombinacion() + " caracteres.");
                continue;
            }

            // Validación de caracteres
            boolean valido = true;
            for (char ch : entrada.toCharArray()) {
                boolean encontrado = false;
                for (char color : c.tablaColores) {
                    if (ch == color) {
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    valido = false;
                    break;
                }
            }

            if (!valido) {
                System.out.println("Error: Usa solo colores válidos " + Arrays.toString(c.tablaColores));
                continue;
            }

            return entrada.toCharArray();
        }
    }

}