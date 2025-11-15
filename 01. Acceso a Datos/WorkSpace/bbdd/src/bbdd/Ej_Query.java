package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import clases.Empleado;


public class Ej_Query {
	
	private static final String SQL_QUERY = "SELECT * FROM EMPLEADOS";
	
	public static void main(String[] args) {
		Empleado emp = null;
		int id;
		String nombre;
		double salario;
		Timestamp fecha;
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
				PreparedStatement sentencia = conn.prepareStatement(SQL_QUERY);) {
			ResultSet resultados = sentencia.executeQuery();
			while(resultados.next()) {
				id = resultados.getInt(1);
				nombre = resultados.getString(2);
				salario = resultados.getDouble(3);
				fecha = resultados.getTimestamp(4);
				emp = new Empleado(id,nombre,salario,fecha.toLocalDateTime());
				System.out.println(emp);
			}
			
		} catch (SQLException ex) {
			System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
