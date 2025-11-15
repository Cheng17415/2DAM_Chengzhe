package bbdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ej_Update {
	private static final String SQL_UPDATE = "UPDATE empleados SET SALARIO= ? WHERE NOMBRE= ?";

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
				PreparedStatement sentencia = conn.prepareStatement(SQL_UPDATE);) {
			sentencia.setBigDecimal(1, new BigDecimal(8900.55));
			sentencia.setString(2, "Ana Maria");

			int row = sentencia.executeUpdate();
			System.out.println("FILAS actualizadas " + row);
		} catch (SQLException ex) {
			System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}