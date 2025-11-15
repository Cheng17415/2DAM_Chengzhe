package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.ALUMNOS;

public class ejer02 {

	public static void main(String [] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		ALUMNOS a = em.find(ALUMNOS.class, "1111");
		//ALUMNOS a = em.find(ALUMNOS.class, "111166"); Da null
		System.out.println(a);
		
		transaccion.commit();
		em.close();
	}

}
