package Principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ALUMNOS;

public class ejer05 {

	public static void main(String [] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		TypedQuery<ALUMNOS> consulta = (TypedQuery<ALUMNOS>) em.createNamedQuery("ALUMNOS.todos");
		List<ALUMNOS> lista = consulta.getResultList();
		for (ALUMNOS alumno : lista) {
			System.out.println(alumno);
		}
		em.close();
	}

}
