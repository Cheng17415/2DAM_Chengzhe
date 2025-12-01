package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CrearBBDD {
	static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	static String bbdd = "premier";
	static String password = "root";
	static String conexion = "jdbc:mysql://localhost:3306/";
	static ArrayList<String []> jugadores = new ArrayList<>();
	public static void main(String[] args) {
		if(!existeBD(bbdd)) {
			crear_schema();
			crearTabla();
			leerFichero();
			insertarDatos();
		}
	}

	public static boolean existeBD(String bdatos) {
		int n = 0;
		try (Connection con = DriverManager.getConnection(conexion + "sys", "root", password);
				PreparedStatement s = con.prepareStatement(
						"select count(*) nu from INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME= ?");) {
			s.setString(1, bdatos);
			ResultSet r = s.executeQuery();
			while (r.next())
				n = r.getInt("nu");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
		}
		if (n == 0)
			return false;
		return true;
	}
	public static void crear_schema() {
		try (Connection con = DriverManager.getConnection(conexion + "sys", "root", password);
				PreparedStatement s = con.prepareStatement("CREATE SCHEMA IF NOT EXISTS " + bbdd)) {
			s.execute();
			System.out.printf("Base de datos %s creada\n", bbdd);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
		}
	}

	public static void crearTabla() {
		String c = "CREATE TABLE JUGADOR(" + "jugador_id INT NOT NULL PRIMARY KEY,"
				+ "full_name VARCHAR(50) NOT NULL," + "age SMALLINT CHECK(age >= 0 ),"
				+ "birthday_GMT DATE," + "league VARCHAR(40)," + "season VARCHAR(10)," + "position VARCHAR(20),"
				+ "club VARCHAR(40) NOT NULL," + "nationality VARCHAR(30)," + "goals_overall SMALLINT,"
				+ "goals_home SMALLINT," + "goals_away SMALLINT," + "penaly_goals SMALLINT,"
				+ "penalty_misses SMALLINT)";
		try (Connection con = DriverManager.getConnection(conexion + bbdd, "root", password);
				PreparedStatement s = con.prepareStatement(c);) {
			s.execute();
			System.out.println("Tabla creada");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {}
	}
	
	public static void insertarDatos() {
		String c = "INSERT INTO JUGADOR VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection con = DriverManager.getConnection(conexion + bbdd, "root", password);
				PreparedStatement s = con.prepareStatement(c)){
			con.setAutoCommit(false);
			int cont = 1;
			for(String[] jugador: jugadores) {
				s.setInt(1, cont++);
				s.setString(2, jugador[0]);
				s.setInt(3,Integer.parseInt(jugador[1]));
				String [] fecha = jugador[3].split("/");
				s.setDate(4, new Date(Integer.valueOf(fecha[0]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[2]))); //jugador[3]
				s.setString(5,jugador[4]);
				s.setString(6,jugador[5]);
				s.setString(7,jugador[6]);
				s.setString(8, jugador[7]);
				s.setString(9,jugador[11]);
				s.setInt(10, Integer.parseInt(jugador[15]));
				s.setInt(11, Integer.parseInt(jugador[16]));
				s.setInt(12, Integer.parseInt(jugador[17]));
				s.setInt(13, Integer.parseInt(jugador[21]));
				s.setInt(14, Integer.parseInt(jugador[22]));
				s.addBatch();
			}
			s.executeBatch();
			con.commit();
			System.out.println("Introducido datos con éxito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void leerFichero() {
		String fich = "jugadores.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(fich))){
			String linea = br.readLine();
			while((linea = br.readLine())!= null) {
				String [] datos=linea.split(",");
				jugadores.add(datos);
			}
			System.out.println("Archivo jugadores leido correctamente");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}


}
