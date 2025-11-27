package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Alumno;
import model.Curso;

public class ejer02 {
	public static void main(String[] args) {
		Alumno pedro = new Alumno("3","pedro","gomez",30);
		Alumno maria = new Alumno("4","maria","perez",22);
		Curso cursoJava = new Curso("JAVA3",300,"Introduccion a Java");
		Curso cursoNET = new Curso("NET3",250,"Introduccion a NET2");
		
		pedro.add(cursoJava);
		pedro.add(cursoNET);
		maria.add(cursoNET);
		
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAN_N");
		EntityManager em = emp.createEntityManager();
		em.getTransaction().begin();
		em.persist(pedro);
		em.persist(maria);
		em.getTransaction().commit();
		em.close();
		System.out.println("Terminada");
	}
}
