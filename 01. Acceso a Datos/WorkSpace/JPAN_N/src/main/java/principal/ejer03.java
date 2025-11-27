package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Alumno;
import model.Curso;

public class ejer03 {
	public static void main(String[] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAN_N");
		EntityManager em = emp.createEntityManager();
		em.getTransaction().begin();
		//pedro
		Alumno pedro = em.find(Alumno.class,"3");
		Curso cursoNET = em.find(Curso.class, "NET3");
		if(pedro!= null && cursoNET != null) {
			pedro.remove(cursoNET);
			System.out.println("Relación eliminada");
		}else {
			System.out.println("No existe");
		}
		em.getTransaction().commit();
		em.close();
		System.out.println("Terminada");
	}
}
