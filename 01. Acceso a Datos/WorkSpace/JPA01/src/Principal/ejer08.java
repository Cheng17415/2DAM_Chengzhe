package Principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ALUMNOS;
import model.CURSOS;

public class ejer08 {
public static void main(String [] args) {
		
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		ALUMNOS pedro = new ALUMNOS("1","pedro", "gomez",30);
		ALUMNOS maria = new ALUMNOS("2","maria", "perez",25);
		CURSOS cursoJava = new CURSOS("JAVA2", "Introduccion Java", 20, 300, pedro);
		CURSOS cursoNFT = new CURSOS("NFT", "Introduccion NFT", 20, 300, pedro);
		CURSOS cursoPHP = new CURSOS("PHP", "Introduccion PHP", 15, 250, maria);
		
		transaccion.begin();
		em.persist(pedro);
		em.persist(maria);
		em.persist(cursoJava);
		em.persist(cursoNFT);
		em.persist(cursoPHP);
		
		transaccion.commit();
		em.close();
	}
	
}
