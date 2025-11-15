package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ej_CrearSCHEMA {
	private static final String SQL_DELETE = " DROP DATABASE IF EXISTS CAJERO";
	private static final String SQL_CREATE = " CREATE DATABASE IF NOT EXISTS CAJERO";
	

public static void main(String[] args) {
try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
	PreparedStatement sentencia0 = conn.prepareStatement(SQL_DELETE);
	PreparedStatement sentencia = conn.prepareStatement(SQL_CREATE);
	){
	sentencia0.execute();
	sentencia.execute();
	System.out.println("SCHEMA creada");
}catch(SQLException ex){
	System.err.format("%s %s",ex.getMessage(),ex.getSQLState());
} catch(Exception ex) {
	ex.printStackTrace();
}
}

}
