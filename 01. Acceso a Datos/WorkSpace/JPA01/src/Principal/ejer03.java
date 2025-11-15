package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.ALUMNOS;

public class ejer03 {

	public static void main(String [] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		ALUMNOS a = em.find(ALUMNOS.class, "1111");
		if (a!=null) {
			em.remove(a);
		} else {
			System.out.println("No existe");
		}
		
		transaccion.commit();
		em.close();
	}

}
