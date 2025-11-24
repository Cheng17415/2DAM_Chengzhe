package primero;

import java.util.Scanner;

public class NotaAlumno {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

	/*
	 * Hacer un programa que nos pida: Nota del primer trimestre Notal final de la
	 * asignatura (60%) Queremos saber qué nota tenemos que sacar en el segundo
	 * trimestre. Teniendo en cuenta que la nota del primer trimestres es 0.4 (40%)
	 * 
	 * p.s.final final=0.4*p+0.6*s
	 */
		Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce la nota del primer trimestre (0 a 10): ");
        double notaPrimerTrimestre = scanner.nextDouble();

        System.out.print("Introduce la nota final deseada (0 a 10): ");
        double notaFinalDeseada = scanner.nextDouble();

        double notaSegundoTrimestre = (notaFinalDeseada - 0.4 * notaPrimerTrimestre) / 0.6;

        System.out.printf("Necesitas sacar un %.2f en el segundo trimestre.%n", notaSegundoTrimestre);

        scanner.close();
	}
	
}