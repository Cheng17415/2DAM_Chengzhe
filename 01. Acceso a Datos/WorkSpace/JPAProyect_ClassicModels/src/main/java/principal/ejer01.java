package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Order;

public class ejer01 {
	public static void main(String[] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAProyect_ClassicModels");
		EntityManager em = emp.createEntityManager();
		
		TypedQuery<Order> consulta = (TypedQuery<Order>) em.createNamedQuery("Order.findAll");
		List<Order> lista = consulta.getResultList();
		for (Order o : lista) {
			System.out.println(o);
		}
		em.close();
	}
}
