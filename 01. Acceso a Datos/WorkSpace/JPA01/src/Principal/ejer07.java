package Principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ALUMNOS;

public class ejer07 {

	public static void main(String [] args) {
		
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		TypedQuery<ALUMNOS> consulta = em.createQuery("SELECT d FROM ALUMNOS d WHERE d.edad >:vedad", ALUMNOS.class);
		consulta.setParameter("vedad", 40);
		List<ALUMNOS> lista = consulta.getResultList();
		for (ALUMNOS alumno : lista) {
			System.out.println(alumno);
		}
		em.close();
	}

}
