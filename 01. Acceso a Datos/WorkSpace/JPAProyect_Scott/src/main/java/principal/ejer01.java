package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Dept;

public class ejer01 {
	public static void main(String[] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAScott");
		EntityManager em = emp.createEntityManager();
		
		TypedQuery<Dept> consulta = (TypedQuery<Dept>) em.createNamedQuery("Dept.findAll");
		List<Dept> lista = consulta.getResultList();
		for (Dept d : lista) {
			System.out.println(d);
		}
		em.close();
	}
}
