package principal;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Alumno;
import model.Curso;
import jakarta.persistence.EntityManager;

public class ejer01 {
	public static void main(String[] args) {
		Alumno pedro = new Alumno("1","pedro","gomez",30);
		Alumno maria = new Alumno("2","maria","perez",22);
		Curso cursoJava = new Curso("JAVA2",300,"Introduccion a Java");
		Curso cursoNET = new Curso("NET2",250,"Introduccion a NET2");
		
		pedro.getCursos().add(cursoJava);
		cursoJava.getAlumnos().add(pedro);
		
		pedro.getCursos().add(cursoNET);
		cursoNET.getAlumnos().add(pedro);
		
		maria.getCursos().add(cursoNET);
		cursoNET.getAlumnos().add(maria);
		
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAN_N");
		EntityManager em = emp.createEntityManager();
		em.getTransaction().begin();
		em.persist(pedro);
		em.persist(maria);
		em.persist(cursoJava);
		em.persist(cursoNET);
		em.getTransaction().commit();
		em.close();
		System.out.println("Terminada");
	}
}
