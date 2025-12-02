package principal;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;	
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Equipo;
import model.Partido;

public class Principal {
	/*
	 * BD LIGA Equipos Partido Crear un fichero que tenga una clasificacion en una
	 * jornada dada que se pasa como parámetro por teclado. Leer de la base de datos
	 * y grabar 1. Atletico de Madrid 33 Jornada 7. Coger todos los partidos desde
	 * la 1 hasta la 7. Goles a favor y goles en contra clasificacion.dat Cabezera:
	 * Clasificacion en la jornada x:
	 */
	
	/*El JOIN FETCH le indica al proveedor de persistencia (como Hibernate) que no
	 *  solo una a las tablas, sino que también traiga y cargue inmediatamente los 
	 *  datos de la entidad relacionada en la misma consulta SELECT*/
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Map<String, Integer> resultados = new HashMap<>();
		try {
			System.out.println("¿Hasta que jornada quieres ver la clasificación?");
			int jornada = Integer.valueOf(sc.nextLine());
			
			EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAlaLiga");
			EntityManager em = emp.createEntityManager();
			
			// Nos da la consulta hasta determinada jornada
			TypedQuery<Partido> consulta = em.createQuery("SELECT p FROM Partido p"
					+ " JOIN FETCH p.equipo1"
					+ " JOIN FETCH p.equipo2"
					+ " WHERE p.id.idjornada <=:jornada", Partido.class);

			consulta.setParameter("jornada", jornada);
			List<Partido> lista = consulta.getResultList();
			int puntosLocal = 0, puntosVisitante = 0;
			
			for (Partido partido : lista) {
				puntosLocal = calcularPuntos(partido.getGolLocal(), partido.getGolVisitante());
				puntosVisitante =calcularPuntos(partido.getGolVisitante(),partido.getGolLocal());
				
				String nombreEquipo1 = partido.getEquipo1().getNombre();
				String nombreEquipo2 = partido.getEquipo2().getNombre();
	
				sumarPuntos(resultados,nombreEquipo1,puntosLocal);
				sumarPuntos(resultados,nombreEquipo2,puntosVisitante);
			}
			
			Map<String, Integer> ordenadoPorPuntos = resultados.entrySet()
			        .stream()
			        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // mayor a menor
			        .collect(
			                java.util.stream.Collectors.toMap(
			                        Map.Entry::getKey,
			                        Map.Entry::getValue,
			                        (e1, e2) -> e1,
			                        java.util.LinkedHashMap::new
			                )
			       );
			
			escribirFichero(ordenadoPorPuntos, jornada);
			
			em.close();
			emp.close();
			
		} catch (Exception e){
			System.out.println("Error:" + e.getMessage());
		}
	}


	private static void escribirFichero(Map<String, Integer> ordenadoPorPuntos, int jornada) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("clasificacion.dat"))){
			bw.write("Clasificacion jornada " + jornada);
			bw.newLine();
			int i = 1;
			for (Map.Entry<String, Integer> entry : ordenadoPorPuntos.entrySet()) {
				String s = String.format("%2d. %-20s %3d puntos", i++, entry.getKey(), entry.getValue());
				bw.write(s);
				bw.newLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static int calcularPuntos(int golesFavor, int golesContra) {
		int puntos;
		if (golesFavor > golesContra) {
			puntos = 3;
		} else if (golesFavor < golesContra) {
			puntos = 0;
		} else {
			puntos = 1;
		}
		return puntos;
	}
	public static void sumarPuntos(Map<String, Integer> resultados, String nombre, int puntos) {
		if(resultados.containsKey(nombre)) {
			resultados.put(nombre, resultados.get(nombre) + puntos);
		} else {
			resultados.put(nombre, puntos);
		}
	}
}
