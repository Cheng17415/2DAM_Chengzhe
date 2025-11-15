package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.ALUMNOS;

public class ejer01 {

	public static void main(String [] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		em.persist(new ALUMNOS("1111","JOSE","RUIZ",33));
		em.persist(new ALUMNOS("2222","ANA","BOHUE",23));
		em.persist(new ALUMNOS("3333","LESSY","MESSI",45));
		em.persist(new ALUMNOS("4444","JOSE","RUIZ",33));
		
		transaccion.commit();
		em.close();
	}

}
