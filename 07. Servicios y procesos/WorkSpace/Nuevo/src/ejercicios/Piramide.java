package ejercicios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Piramide {
	//Pedir el numero de niveles de una pirámide. Quiero grabar en un fichero la pirámide
	public static void main(String[] args) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("piramide.dat"))) {
			Scanner sc = new Scanner(System.in);
			String s;
			System.out.println("Dime la cantidad de niveles de una pirámide");
			int niveles = Integer.parseInt(sc.nextLine());
			for (int i = 1; i <= niveles; i++) {
				int estrellas = (i * 2) -1;
				int blancos = niveles - i;
				s = (" ".repeat(blancos) + "*".repeat(estrellas) + "\n");
				System.out.print(s);
				bw.write(s);
			}
			sc.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
