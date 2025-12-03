package principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import clases.Equipo;

public class EjerLaliga {
	public static void main(String[] args) {
		TreeMap <Integer, Equipo> clasificacion = new TreeMap<>();
		clasificacion.put(1, new Equipo("AT.Madrid", 30));
		clasificacion.put(2, new Equipo("Real Madrid", 33));
		clasificacion.put(3, new Equipo("Barcelona", 31));
		
		ArrayList<Equipo> lequipos = new ArrayList<>();
		for (Integer e : clasificacion.keySet()) {
			lequipos.add(clasificacion.get(e));
		}
		lequipos.sort(new Ordenar());
		for (Equipo equipo : lequipos) {
			System.out.println(equipo);
		}
	}
}

class Ordenar implements Comparator<Equipo>{
	@Override
	public int compare(Equipo o1, Equipo o2) {
		
		return o1.getPuntos() - o2.getPuntos();
		
	}
}
