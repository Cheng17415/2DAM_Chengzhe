package principal;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Equipo;

public class Principal {
	/*BD LIGA
	Equipos
	Partido
	Crear un fichero que tenga una clasificacion en una jornada dada que se pasa como parámetro por teclado.
	Leer de la base de datos y grabar 1. Atletico de Madrid 33
	Jornada 7. Coger todos los partidos desde la 1 hasta la 7. Goles a favor y goles en contra
	clasificacion.dat
	Cabezera: Clasificacion en la jornada x:
	Para cada jornada, unirlo*/
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		try {
			System.out.println("¿Hasta que jornada quieres ver la clasificación?");
			int jornada = Integer.valueOf(sc.nextLine());
			
			EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAlaLiga");
			EntityManager em = emp.createEntityManager();
			EntityTransaction transaccion = em.getTransaction();
			transaccion.begin();
			// WHERE p.IDLOCAL = e.IDEQUIPO"" AND p.IDJORNADA <= 1
			TypedQuery<Equipo> consulta = em.createQuery("SELECT e FROM Equipo e JOIN e.partidos1 p WHERE p.id.idjornada <=:jornada", Equipo.class);
			consulta.setParameter("jornada", jornada);
			List<Equipo> lista = consulta.getResultList();
			for (Equipo alumno : lista) {
				System.out.println(alumno);
			}
			em.close();
			emp.close();
		} catch (Exception e){
			System.out.println("Error:" + e.getMessage());
		}
		
		
	}

}
