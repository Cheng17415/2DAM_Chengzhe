package bbdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Ej_Batch {
	private static final String SQL_DROP="DROP TABLE IF EXISTS EMPLEADOS";
	private static final String SQL_CREATE = 
					" CREATE TABLE IF NOT EXISTS EMPLEADOS(" +
					" ID INT PRIMARY KEY AUTO_INCREMENT,"+
					" NOMBRE VARCHAR(100) NOT NULL,"+
					" SALARIO DECIMAL(10,2) NOT NULL,"+
					" FECHA DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP"+
					" )";
	private static final String SQL_DELETE = "DELETE FROM EMPLEADOS WHERE SALARIO>?";
	private static final String SQL_INSERTAR = "INSERT INTO empleados(nombre, salario, fecha) VALUES (?,?,?)";
	private static final String SQL_ACTUALIZAR = "UPDATE empleados SET salario= ? WHERE NOMBRE = ?";
	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase","root","root");
			PreparedStatement pd = conn.prepareStatement(SQL_DROP);
			PreparedStatement pc = conn.prepareStatement(SQL_CREATE);
			PreparedStatement pi = conn.prepareStatement(SQL_INSERTAR);
			PreparedStatement pu = conn.prepareStatement(SQL_ACTUALIZAR);
			){
			conn.setAutoCommit(false);
			
			//LAS DE CREACION O DROP NO HAY VUELTA ATRÁS, POR ESO NO HAY QUE AÑADIRLO AL BATCH
			pd.execute();
			pc.execute();
			
			pi.setString(1, "carlosppp");
			pi.setBigDecimal(2, new BigDecimal(4444));
			pi.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			
			pi.addBatch();
			
			pi.setString(1, "mariappp");
			pi.setBigDecimal(2, new BigDecimal(5555));
			pi.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			
			pi.addBatch();
			
			pu.setBigDecimal(1, new BigDecimal(8900.66));
			pu.setString(2,"mariappp");
			
			pu.addBatch();
			int [] rows = pi.executeBatch();
			int [] rows2 = pu.executeBatch();
			
			System.out.println(Arrays.toString(rows));
			System.out.println(Arrays.toString(rows2));
			
			conn.commit();
		}catch(SQLException ex){
			System.err.format("%s %s",ex.getMessage(),ex.getSQLState());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
