package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ej_SubirSalario {
	private static final String SQL_UPDATE = "UPDATE EMPLEADOS SET SALARIO = SALARIO * ?;";

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
				PreparedStatement sentencia = conn.prepareStatement(SQL_UPDATE);) {
			sentencia.setDouble(1, 1.2);

			int row = sentencia.executeUpdate();
			System.out.println("FILAS actualizadas " + row);
		} catch (SQLException ex) {
			System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}