package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.emp;
public class ejer03 {
	public static void main(String[] args) {
		//Todos los departamentos que estén en madrid
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPASCOTT");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		TypedQuery<emp> consulta = (TypedQuery<emp>) em.createNamedQuery("EMP.todos");
		List<emp> lista = consulta.getResultList();
		for (emp empleado : lista) {
			empleado.setSal(empleado.getSal() * 1.1);
		}
		transaccion.commit();
		em.close();
	}
}
