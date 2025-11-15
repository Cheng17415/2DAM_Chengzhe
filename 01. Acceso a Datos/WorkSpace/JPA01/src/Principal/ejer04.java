package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.ALUMNOS;

public class ejer04 {

	public static void main(String [] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPAnuevo");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		//Merge añade si no está, modifica si está
		//Está
		ALUMNOS a = new ALUMNOS("3333","Maria","RUIZ",43);
		a.setEdad(22);
		em.merge(a);
		//No está
		ALUMNOS b = new ALUMNOS("5555","PEDRO","PORROS", 55);
		em.merge(b);
		transaccion.commit();
		em.close();
	}

}
