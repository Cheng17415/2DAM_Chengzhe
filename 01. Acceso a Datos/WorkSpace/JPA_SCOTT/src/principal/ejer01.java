package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.dept;

public class ejer01 {
	public static void main(String[] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPASCOTT");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		em.persist(new dept(50,"MARKETING","SEVILLA"));
		em.persist(new dept(60,"DESARROLLO","MADRID"));
		em.persist(new dept(70,"I+D","BARCELONA"));
		
		transaccion.commit();
		em.close();
	}
}
