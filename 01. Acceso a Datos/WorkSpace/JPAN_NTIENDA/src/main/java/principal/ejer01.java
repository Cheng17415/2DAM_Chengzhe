package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Cliente;
import model.Producto;

public class ejer01 {
	public static void main(String[] args) {
		Cliente juan = new Cliente("juan");
		Producto peras = new Producto("peras");
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAN_NTIENDA");
		EntityManager em = emp.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(juan);
		em.persist(peras);
		
		em.getTransaction().commit();
		em.close();
		System.out.println("Terminada");
	}
}
