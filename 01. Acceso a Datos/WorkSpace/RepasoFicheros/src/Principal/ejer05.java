package Principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import Clases.equipo;
import Clases.laligaClubFecha;

public class ejer05 {
	static String bbdd = "laliga";
	static String password = "root";
	static String conexion = "jdbc:mysql://localhost:3306/";
	static TreeMap<Integer, equipo> clasificacion = new TreeMap<Integer, equipo>();
    /**********************************************************/
	public static void equipos() {
		try (Connection con = DriverManager.getConnection(conexion + bbdd, "root", password);
			PreparedStatement s = con.prepareStatement("select * from equipos");) {
			ResultSet r = s.executeQuery();
			while (r.next()) {
				clasificacion.put(r.getInt("IDEQUIPO"), new equipo(r.getString("NOMBRE")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
		}
	}
	/**********************************************************/
	public static void cargarpartidos(int jornada) {
	String query="select * from partidos where IDJORNADA<= "+jornada;
	int diferencia;
	equipo eq;
		try (Connection con = DriverManager.getConnection(conexion + bbdd, "root", password);
			PreparedStatement s = con.prepareStatement(query);) {
			ResultSet r = s.executeQuery();
			while (r.next()) {
				
				diferencia=r.getInt("GOL_LOCAL")-r.getInt("GOL_VISITANTE");
				if (diferencia>0) {
					eq=clasificacion.get(r.getInt("IDLOCAL"));
					eq.setPtos(eq.getPtos()+3);
					clasificacion.put(r.getInt("IDLOCAL"), eq);
				}else if(diferencia<0) {
					eq=clasificacion.get(r.getInt("IDVISITANTE"));
					eq.setPtos(eq.getPtos()+3);
					clasificacion.put(r.getInt("IDVISITANTE"), eq);
				}else {
					eq=clasificacion.get(r.getInt("IDLOCAL"));
					eq.setPtos(eq.getPtos()+1);
					clasificacion.put(r.getInt("IDLOCAL"), eq);
					
					eq=clasificacion.get(r.getInt("IDVISITANTE"));
					eq.setPtos(eq.getPtos()+1);
					clasificacion.put(r.getInt("IDVISITANTE"), eq);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
		}
	}
	/**********************************************************/
	public static void main(String[] args) {
        int jornada=38;
		equipos();
		cargarpartidos(jornada);
		
		ArrayList<equipo> lequipos = new ArrayList<equipo>();
		for (Integer e : clasificacion.keySet()) {
			lequipos.add(clasificacion.get(e));
		}
		lequipos.sort(new ordenar());
		
		String escritura="clasificacion"+jornada+".dat";
	       try(BufferedWriter fescritura=new BufferedWriter(new FileWriter(escritura))){
	    
	    	fescritura.write("Clasificación jornada "+jornada+"\n\n");
	    	int c=0;   
	    	for (equipo equipo : lequipos) {
	   	fescritura.write(String.format("%2d %-20s %3d",
	   			++c,equipo.getNombre(),equipo.getPtos())+"\n");
	   		}
	       
	       }catch(IOException e) {
	    	   System.out.println(e.getMessage());
	       }
		
		
		
	}

}

class ordenar implements Comparator<equipo> {
	@Override
	public int compare(equipo o1, equipo o2) {

		return o2.getPtos() - o1.getPtos();
	}
}
