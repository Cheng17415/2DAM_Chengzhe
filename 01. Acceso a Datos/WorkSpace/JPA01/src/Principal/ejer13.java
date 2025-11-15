package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.ALUMNOS;
import model.CURSOS;

public class ejer13 {
public static void main(String [] args) {
		
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		ALUMNOS pedro = new ALUMNOS("10","pedro2", "gomez",30);
		ALUMNOS maria = new ALUMNOS("20","maria2", "perez",25);
		
		CURSOS cursoJava2 = new CURSOS("JAVA22", "Introduccion Java", 20, 300, pedro);
		CURSOS cursoNFT2 = new CURSOS("NFT22", "Introduccion NFT", 20, 300, pedro);
		CURSOS cursoPHP2 = new CURSOS("PHP22", "Introduccion PHP", 15, 250, maria);
		
		pedro.add(cursoJava2);
		pedro.add(cursoNFT2);
		pedro.add(cursoPHP2);
		
		transaccion.begin();
		
		em.persist(pedro);
		em.persist(maria);
		
		transaccion.commit();
		em.close();
	}
	
}
