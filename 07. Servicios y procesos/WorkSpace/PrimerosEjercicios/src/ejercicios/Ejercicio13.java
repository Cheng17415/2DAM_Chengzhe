package ejercicios;

import java.util.Scanner;

public class Ejercicio13 {

	public static void main(String[] args) {
try (//		Nota 1 trimestre Nota final que quiero .Quiero saber cuanto tengo que sacar en el segundo trimestre
				//Nota final = 0.4 * primer + 0.6* segundo
				//Nota final -(0.4 * primer)/ 0.6 -  = segundo
		Scanner sc = new Scanner(System.in)) {
			System.out.println("¿Qué nota has sacado en el primer trimestre?");
			double nota1 = Double.valueOf(sc.nextLine());
			System.out.println("¿Qué nota quieres tener para el final?");
			double notaf = Double.valueOf(sc.nextLine());
			double nota2 = (notaf - 0.4 * nota1) / (0.6);
			if(nota2 < 10 && nota2 > 0) {
				System.out.printf("La nota que necesitas sacar para la segunda evaluación es de %.2f", nota2);
			}
			else {
				System.out.printf("No podrás obtener esa nota ya que necesitas %.2f", nota2);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
