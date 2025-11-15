package bbdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Ej_Insertar {
	
	private static final String SQL_INSERTAR = "INSERT INTO empleados(nombre, salario, fecha) VALUES (?,?,?);";

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
				PreparedStatement sentencia = conn.prepareStatement(SQL_INSERTAR);) {
			sentencia.setString(1, "Ana Maria");
			sentencia.setBigDecimal(2, new BigDecimal(5708.55));
			sentencia.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

			int row = sentencia.executeUpdate();
			System.out.println("FILAS insertadas " + row);
		} catch (SQLException ex) {
			System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
