package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Fich_02 {
	/*Leer dos ficheros de texto y obtener un tercer fichero
	 *que sea las lineas alternas de los dos ficheros
	 */
	public static void main(String[] args) throws IOException{
		ArrayList<String> lista = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("verduras.dat"));
			BufferedReader br2 = new BufferedReader(new FileReader("frutas.dat"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("juntos.dat"));){
			String texto = br.readLine();
			String texto2 = br2.readLine();

			while(texto != null && texto2 != null) {
				lista.add(texto);
				lista.add(texto2);
				texto = br.readLine();
				texto2 = br2.readLine();
			}
			while(texto != null) {
				lista.add(texto);
				texto = br.readLine();
			}
			while(texto2 != null) {
				lista.add(texto2);
				texto2 = br2.readLine();
			}
			
			for(String s: lista) {
				System.out.println(s);
				bw.write(s + "\n");
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		}

	}

}
