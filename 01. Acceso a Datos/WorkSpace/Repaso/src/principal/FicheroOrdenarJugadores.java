package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import clases.Jugador;

public class FicheroOrdenarJugadores {
	//Ordenar el fichero jugadores.csv por el Club y por el Birthday_GMT
	public static void main(String[] args){
		List <Jugador> lista = new ArrayList<>();
		String ficheroEntrada = "jugadores.csv";
		String ficheroSalida = "jugadoresOrdenados.csv";
		String header = "";
		try(BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada))){
			String linea;
			boolean temp = false;
			while((linea = br.readLine()) != null) {
				String [] t = linea.split(",");
				if(temp == false) {
					header = linea;
					temp = true;
					continue;
				}
				if(t.length >7) {
					//Fecha Cumpleaños lo divido en YYYY, MM, DD
					String [] fecha = t[3].split("/");
					System.out.println(Arrays.toString(fecha));
					lista.add(new Jugador(t[7], LocalDate.of(Integer.valueOf(fecha[0]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[2]))));
				}
				
			}
			
			Collections.sort(lista, new Comparator<Jugador>(){
				@Override
				public int compare(Jugador o1, Jugador o2) {
					int n = o1.getClub().compareTo(o2.getClub());
					if(n!=0) return n;
					return o1.getFechaCumple().compareTo(o2.getFechaCumple());
				}
			});
		} catch(IOException e) {
			System.out.print("Fichero no encontrado");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
			bw.write(header + "\n");
			for (Jugador p : lista) {
				bw.write(p.getClub() + "," + p.getFechaCumple() +"\n");
			}
			System.out.println("Ordenado con éxito");
		} catch(IOException e) {
			System.out.print("Fichero no encontrado");
		}
	}
}

