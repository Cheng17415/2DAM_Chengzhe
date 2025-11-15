package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ej_CrearTabla {
	
	private static final String SQL_CREATE = 
					" CREATE TABLE IF NOT EXISTS EMPLEADOS(" +
					" ID INT PRIMARY KEY AUTO_INCREMENT,"+
					" NOMBRE VARCHAR(100) NOT NULL,"+
					" SALARIO DECIMAL(10,2) NOT NULL,"+
					" FECHA DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP"+
					" )";
	
	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase","root","root");
			PreparedStatement sentencia = conn.prepareStatement(SQL_CREATE);
			){
			sentencia.execute();
			System.out.println("Tabla creada");
		}catch(SQLException ex){
			System.err.format("%s %s",ex.getMessage(),ex.getSQLState());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
