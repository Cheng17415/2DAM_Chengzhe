package Principal;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class ejer12 {

	public static void main(String [] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		Query suma = em.createQuery("SELECT sum(c.precio) FROM CURSOS c");
		Double total = (Double) suma.getSingleResult();
		System.out.println(total);
		em.close();
	}

}
