package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.dept;
public class ejer02 {
	public static void main(String[] args) {
		//Todos los departamentos que estén en madrid
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPASCOTT");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		TypedQuery<dept> consulta = em.createQuery("SELECT d FROM dept d WHERE d.loc LIKE :vloc", dept.class);
		consulta.setParameter("vloc", "MADRID");
		List<dept> lista = consulta.getResultList();
		for (dept alumno : lista) {
			System.out.println(alumno);
		}
		em.close();
	}
}
