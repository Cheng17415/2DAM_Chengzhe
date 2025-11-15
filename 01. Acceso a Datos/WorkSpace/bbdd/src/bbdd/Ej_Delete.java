package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ej_Delete {
	private static final String SQL_DELETE = "DELETE FROM EMPLEADOS WHERE SALARIO>?;";

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
				PreparedStatement sentencia = conn.prepareStatement(SQL_DELETE);) {
			sentencia.setDouble(1, 11000);

			int row = sentencia.executeUpdate();
			System.out.println("FILAS borradas " + row);
		} catch (SQLException ex) {
			System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}