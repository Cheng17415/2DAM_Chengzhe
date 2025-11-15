package com.cheng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Ej_CrearSCHEMA {
	private static final String SQL_DELETE = " DROP DATABASE IF EXISTS BANCO";
	private static final String SQL_CREATE = " CREATE DATABASE IF NOT EXISTS BANCO";
	private static final String SQL_CREATETABLE = " CREATE TABLE IF NOT EXISTS CAJERO(" + "ID DECIMAL(10,2) PRIMARY KEY,"
			+ "CANTIDAD INT NOT NULL)";
	private static final String SQL_INSERTAR = "INSERT INTO CAJERO(ID, CANTIDAD) VALUES (?, ?)";
	// Dentro de banco quiero crear tabla cajero, PK moneda, cantidad.
	// Rellenar esa tabla con 10 monedas y billetes. 500€ 200€ 100€...0.01€

	public static void main(String[] args) {
		int [] valores = {5,2,1};
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase","root","root");
				PreparedStatement pd = conn.prepareStatement(SQL_DELETE);
				PreparedStatement pc = conn.prepareStatement(SQL_CREATE);
				PreparedStatement pct = conn.prepareStatement(SQL_CREATETABLE);
				PreparedStatement pu = conn.prepareStatement(SQL_INSERTAR);
				){
			pd.execute();
			pc.execute();
			pct.execute();
			
			conn.setAutoCommit(false);
			for(int i = 2; i>=-2; i--) {
				for (int val : valores) {
					pu.setDouble(1, val * Math.pow(10, i));
					pu.setInt(2,10);
					pu.addBatch();
				}
			}
			
			int[] rows= pu.executeBatch();
			System.out.println(Arrays.toString(rows));
			
			conn.commit();
		}catch(SQLException ex){
			System.err.format("%s %s",ex.getMessage(),ex.getSQLState());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
