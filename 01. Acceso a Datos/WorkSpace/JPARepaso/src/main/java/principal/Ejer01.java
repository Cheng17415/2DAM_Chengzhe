package principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Dept;
import model.Emp;

public class Ejer01 {
	/*Leyendo de la base de datos scott, crear 2 ficheros csv,
	 * que contenga la informacion que contenga la informacion de la bbdd separado por : y arriba una cabezera.*/
	public static void main(String[] args) {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPARepaso");
		EntityManager em = emp.createEntityManager();

		List<Dept>departamentos = em.createNamedQuery("Dept.findAll").getResultList();
		List<Emp> empleados = em.createNamedQuery("Emp.findAll").getResultList();
		escribirFichero(departamentos, empleados);
		em.close();
		emp.close();
	}
	
	public static void escribirFichero(List<Dept> departamentos, List<Emp> empleados ) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("dept.txt"));
				BufferedWriter bw2 = new BufferedWriter(new FileWriter("emp.txt"))){
			
			//Escritura Departamentos
			bw.write(Dept.cabezera());
			bw.newLine();
			for(Dept dep : departamentos) {
				bw.write(dep.toString());
				bw.newLine();
			}
			System.out.println("Escritura en el archivo dept.txt con exito");
			
			//Escritura Empleados
			bw2.write(Emp.cabezera());
			bw2.newLine();
			for(Emp empleado : empleados) {
				bw2.write(empleado.toString());
				bw2.newLine();
			}
			System.out.println("Escritura en el archivo emp.txt con exito");
		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero dept" + e.getMessage());
		}
	}
}
