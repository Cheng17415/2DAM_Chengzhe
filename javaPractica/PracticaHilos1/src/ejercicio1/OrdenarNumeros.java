package ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OrdenarNumeros {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		String input = "";
		ArrayList<Integer> numeros = new ArrayList<>();
		System.out.println("Introduzca numeros para ordenarlos. Si no quieres continuar, pulse enter:");
		do {
			System.out.println("Introduzca numero: ");
			input = sc.nextLine();
			if(input.equals("")) break;
			try {
				int num = Integer.parseInt(input);
				numeros.add(num);
			} catch (Exception e) {
				System.out.println("Debes introducir un numero valido");
			}
		}while(input != "");
		Collections.sort(numeros);
		System.out.println("Numeros ordenados:");
		for (Integer num : numeros) {
			System.out.println(num);
		}
	}
}
