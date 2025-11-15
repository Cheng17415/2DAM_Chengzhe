package bbdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import clases.Empleado;

public class Ej_InsertarArrayList {
	
	private static final String SQL_INSERTAR = "INSERT INTO empleados(nombre, salario, fecha) VALUES (?,?,?);";
	
	private static void llenar(ArrayList<Empleado> l) {
		l.add(new Empleado("Juan Ignacio",5000,LocalDateTime.of(2025,8,10,12,23,20)));
		l.add(new Empleado("Juan Carlos",4000,LocalDateTime.of(2015,9,10,12,23,20)));
		l.add(new Empleado("Juan Andres",6000,LocalDateTime.of(2022,10,10,12,23,20)));
		l.add(new Empleado("Pedro Porras",7000,LocalDateTime.of(2020,11,10,12,23,20)));
		l.add(new Empleado("Burrito Sabanero",1000,LocalDateTime.of(2020,12,10,12,23,20)));
	}
	
	public static void main(String[] args) {
		ArrayList <Empleado> listaEmp = new ArrayList<>();
		llenar(listaEmp);
		int filas = 0;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
				PreparedStatement sentencia = conn.prepareStatement(SQL_INSERTAR);) {
			for(Empleado e: listaEmp) {
				sentencia.setString(1, e.getNombre());
				sentencia.setBigDecimal(2, new BigDecimal(e.getSalario()));
				sentencia.setTimestamp(3, Timestamp.valueOf(e.getFecha()));
				filas += sentencia.executeUpdate();
			}
			
			System.out.println("FILAS insertadas " + filas);
		} catch (SQLException ex) {
			System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
