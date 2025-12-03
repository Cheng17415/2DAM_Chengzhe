package principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import clases.Equipo;

public class EjerLaliga2 {
	/*NO TERMINADO!!! COPIAR DEL PROFE EN ACCESO A DATOS*/
	static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	static String bbdd = "laliga";
	static String password = "root";
	static String conexion = "jdbc:mysql://localhost:3306/";
	static TreeMap<Integer, Equipo> clasificacion = new TreeMap<>();
	
	public static void equipos() {	
		try (Connection con = DriverManager.getConnection(conexion + "sys", "root", password);
			PreparedStatement s = con.prepareStatement("SELECT * FROM equipo"), Equipo.class) {
			
		clasificacion.put(r.getInt("IDEQUIPO"), new Equipo());
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	} catch (Exception e) {
	}
}
	
	public static void cargarPartidos(int jornada) {
		String query = "select * from partidos WHERE IDJORNADA<=" + jornada;
		int diferencia;
		Equipo eqLocal;
		try (Connection con = DriverManager.getConnection(conexion + "sys", "root", password);
				PreparedStatement s = con.prepareStatement(query)){
			ResultSet r = s.executeQuery();
			while(r.next()) {
				diferencia = r.getInt("GOL_LOCAL") - r.getInt("GOL_VISITANTE");
				if(diferencia>0) {
					eqLocal = clasificacion.get(r.getInt("IDLOCAL"));
					eqLocal.setPuntos(eqLocal.getPuntos()+3);
					clasificacion.put(r.getInt("IDLOCAL"), eqLocal);
				} else if(diferencia<0) {
					eqLocal = clasificacion.get(r.getInt("IDVISITANTE"));
					eqLocal.setPuntos(eqLocal.getPuntos()+3);
					clasificacion.put(r.getInt("IDVISITANTE"), eqLocal);
				} else {
					eqLocal = clasificacion.get(r.getInt("IDLOCAL"));
					eqLocal.setPuntos(eqLocal.getPuntos()+1);
					clasificacion.put(r.getInt("IDLOCAL"), eqLocal);
					
					eqLocal = clasificacion.get(r.getInt("IDVISITANTE"));
					eqLocal.setPuntos(eqLocal.getPuntos()+3);
					clasificacion.put(r.getInt("IDVISITANTE"), eqLocal);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		int jornada = 7;
		equipos();

	}
}
