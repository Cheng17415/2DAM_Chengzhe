package Principal;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.ALUMNOS;

public class ejer09 {
public static void main(String [] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		
		TypedQuery<ALUMNOS> consulta = 
				(TypedQuery<ALUMNOS>) em.createNamedQuery("ALUMNOS.todos");
		
		for (ALUMNOS alumno : consulta.getResultList()) {
			System.out.println(alumno);
		}
		
		em.close();
	}
	
}
