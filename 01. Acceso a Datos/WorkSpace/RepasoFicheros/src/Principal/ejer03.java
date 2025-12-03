package Principal;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Map.Entry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import Clases.Persona;
import Clases.laliga;

public class ejer03 {
	   static String bbdd=null;
	   static String password="root";
	   static String conexion="jdbc:mysql://localhost:3306/";
	 
	   public static void Crear_Schema(String cadena)  {
		   try(
		   Connection con=DriverManager.getConnection(conexion+"sys","root",password);
		   PreparedStatement s=con.prepareStatement(cadena+bbdd); 		   
		   ){
			   s.execute();
			   System.out.printf("Base de datos %s creada\n",bbdd);
		   }catch(SQLException e) {
			   System.out.println(e.getMessage());
		   }catch(Exception e) {}
	   }
	   /**********************************************************/
	   public static boolean ExisteBD(String bdatos)  {
		   int n=0;
		   try(
		   Connection con=DriverManager.getConnection(conexion+"sys","root",password);
		   PreparedStatement s=
	con.prepareStatement("select count(*) nu from INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME= ?"); 		   
		   ){
			   s.setString(1,bdatos);
			   ResultSet r=s.executeQuery();
			   while(r.next())n=r.getInt("nu");
		   }catch(SQLException e) {
			   System.out.println(e.getMessage());
		   }catch(Exception e) {}
		   if (n==0)return false;
		   return true;   
		}
	   /**********************************************************/
	   public static void CrearTabla(String c)  {
		   try(
		   Connection con=DriverManager.getConnection(conexion+bbdd,"root",password);
		   PreparedStatement s=con.prepareStatement(c); 		   
		   ){
			   s.execute();
			   System.out.println("Tabla creada");
		   }catch(SQLException e) {
			   System.out.println(e.getMessage());
		   }catch(Exception e) {}
	   }
	
	   public static void Insertar_datos(HashMap<String,Integer> eq)  {
		   int row;
		   String c="INSERT INTO EQUIPOS VALUES(?,?)";
			   try(
			   Connection con=DriverManager.getConnection(conexion+bbdd,"root",password);
			   PreparedStatement s=con.prepareStatement(c); 		   
			   ){
				   for (String equipo : eq.keySet()) {
					   s.setInt(1, eq.get(equipo));
					   s.setString(2, equipo);
				   	   row=s.executeUpdate();
				   }
			   }catch(SQLException e) {
				   System.out.println(e.getMessage());
			   }catch(Exception e) {}
		   }
	   public static void Insertar_datos(ArrayList<laliga> li,HashMap<String,Integer> eq)  {
		   int row;
		   String c="INSERT INTO LIGA VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			   try(
			   Connection con=DriverManager.getConnection(conexion+bbdd,"root",password);
			   PreparedStatement s=con.prepareStatement(c); 		   
			   ){
				for (laliga l : li) {
					s.setString(1,l.getfull_name()) ;
					s.setString(2,l.getage()) ;
					s.setString(3,l.getbirthday()) ;
					s.setString(4,l.getbirthday_gmt()) ;
					s.setString(5,l.getleague()) ;
					s.setString(6,l.getseason()) ;
					s.setString(7,l.getposition()) ;
					s.setInt(8,eq.get(l.getclub())) ;
					s.setString(9,l.getminutes_played_overall()) ;
					s.setString(10,l.getminutes_played_home()) ;
					s.setString(11,l.getminutes_played_away()) ;
					s.setString(12,l.getnationality()) ;
					s.setString(13,l.getappearances_overall()) ;
					s.setString(14,l.getappearances_home()) ;
					s.setString(15,l.getappearances_away()) ;
					s.setString(16,l.getgoals_overall()) ;
					s.setString(17,l.getgoals_home()) ;
					s.setString(18,l.getgoals_away()) ;
					s.setString(19,l.getassists_overall()) ;
					s.setString(20,l.getassists_home()) ;
					s.setString(21,l.getassists_away()) ;
					s.setString(22,l.getpenalty_goals()) ;
					s.setString(23,l.getpenalty_misses()) ;
					s.setString(24,l.getclean_sheets_overall()) ;
					s.setString(25,l.getclean_sheets_home()) ;
					s.setString(26,l.getclean_sheets_away()) ;
					s.setString(27,l.getconceded_overall()) ;
					s.setString(28,l.getconceded_home()) ;
					s.setString(29,l.getconceded_away()) ;
					s.setString(30,l.getyellow_cards_overall()) ;
					s.setString(31,l.getred_cards_overall()) ;
					s.setString(32,l.getgoals_involved_per_90_overall()) ;
					s.setString(33,l.getassists_per_90_overall()) ;
					s.setString(34,l.getgoals_per_90_overall()) ;
					s.setString(35,l.getgoals_per_90_home()) ;
					s.setString(36,l.getgoals_per_90_away()) ;
					s.setString(37,l.getmin_per_goal_overall()) ;
					s.setString(38,l.getconceded_per_90_overall()) ;
					s.setString(39,l.getmin_per_conceded_overall()) ;
					s.setString(40,l.getmin_per_match()) ;
					s.setString(41,l.getmin_per_card_overall()) ;
					s.setString(42,l.getmin_per_assist_overall()) ;
					s.setString(43,l.getcards_per_90_overall()) ;
					s.setString(44,l.getrank_in_league_top_attackers()) ;
					s.setString(45,l.getrank_in_league_top_midfielders()) ;
					s.setString(46,l.getrank_in_league_top_defenders()) ;
					s.setString(47,l.getrank_in_club_top_scorer());
				   	   row=s.executeUpdate();

				}
				   
				   
				   
			   }catch(SQLException e) {
				   System.out.println(e.getMessage());
			   }catch(Exception e) {}
		   }
	public static void main(String[] args) {
        String lectura="jugadores.csv";
        String linea;
        ArrayList<laliga> lista=new ArrayList<laliga>();
        HashMap<String,Integer> equipos=new HashMap<String,Integer>();
        
       try(BufferedReader flectura=new BufferedReader(new FileReader(lectura))){
    	   linea=flectura.readLine();
    	   while(linea!=null) {
    		   String[] campos=linea.split(",");
    		   lista.add(new laliga(
    				   campos[0],campos[1],campos[2],
    				   campos[3],campos[4],campos[5],
    				   campos[6],campos[7], campos[8],
    				   campos[9],campos[10],campos[11],
    				   campos[12],campos[13],campos[14],
    				   campos[15],campos[16],campos[17],
    				   campos[18],campos[19], campos[20],
    				   campos[21],campos[22],
    				   campos[23],campos[24],campos[25],
    				   campos[26],campos[27], campos[28],
    				   campos[29],campos[30],campos[31],
    				   campos[32],campos[33],campos[34],
    				   campos[35],campos[36],campos[37],
    				   campos[38],campos[39], campos[40],
    				   campos[41],campos[42], campos[43],
    				   campos[44],campos[45], campos[46]));
    		   if (!equipos.containsKey(campos[7]))
    			 equipos.put(campos[7], equipos.size());
    		   
    		   linea=flectura.readLine();
    	   }
       }catch(IOException e) {
    	   System.out.println(e.getMessage());
       }
       /**************************************************************/
       for (String equipo : equipos.keySet()) {
    	   System.out.println(equipo+" "+equipos.get(equipo));
		
	}
       
       bbdd="PREMIER";
       Crear_Schema("DROP SCHEMA IF EXISTS ");
       Crear_Schema("CREATE SCHEMA IF NOT EXISTS ");
       String c="CREATE TABLE EQUIPOS(ID INT AUTO_INCREMENT PRIMARY KEY,NOMBRE VARCHAR(40))";
       CrearTabla(c);  
       Insertar_datos(equipos);
       c="CREATE TABLE LIGA( "
          		+ "full_name VARCHAR(40), "
          		+ "age VARCHAR(40), "
          		+ "birthday VARCHAR(40), "
          		+ "birthday_GMT VARCHAR(40), "
          		+ "league VARCHAR(40), "
          		+ "season VARCHAR(40), "
          		+ "position VARCHAR(40), "
          		+ "Club INT , "
          		+ "minutes_played_overall VARCHAR(40), "
          		+ "minutes_played_home VARCHAR(40), "
          		+ "minutes_played_away VARCHAR(40), "
          		+ "nationality VARCHAR(40), "
          		+ "appearances_overall VARCHAR(40), "
          		+ "appearances_home VARCHAR(40), "
          		+ "appearances_away VARCHAR(40), "
          		+ "goals_overall VARCHAR(40), "
          		+ "goals_home VARCHAR(40), "
          		+ "goals_away VARCHAR(40), "
          		+ "assists_overall VARCHAR(40), "
          		+ "assists_home VARCHAR(40), "
          		+ "assists_away VARCHAR(40), "
          		+ "penalty_goals VARCHAR(40), "
          		+ "penalty_misses VARCHAR(40), "
          		+ "clean_sheets_overall VARCHAR(40), "
          		+ "clean_sheets_home VARCHAR(40), "
          		+ "clean_sheets_away VARCHAR(40), "
          		+ "conceded_overall VARCHAR(40), "
          		+ "conceded_home VARCHAR(40), "
          		+ "conceded_away VARCHAR(40), "
          		+ "yellow_cards_overall VARCHAR(40), "
          		+ "red_cards_overall VARCHAR(40), "
          		+ "goals_involved_per_90_overall VARCHAR(40), "
          		+ "assists_per_90_overall VARCHAR(40), "
          		+ "goals_per_90_overall VARCHAR(40), "
          		+ "goals_per_90_home VARCHAR(40), "
          		+ "goals_per_90_away VARCHAR(40), "
          		+ "min_per_goal_overall VARCHAR(40), "
          		+ "conceded_per_90_overall VARCHAR(40), "
          		+ "min_per_conceded_overall VARCHAR(40), "
          		+ "min_per_match VARCHAR(40), "
          		+ "min_per_card_overall VARCHAR(40), "
          		+ "min_per_assist_overall VARCHAR(40), "
          		+ "cards_per_90_overall VARCHAR(40), "
          		+ "rank_in_league_top_attackers VARCHAR(40), "
          		+ "rank_in_league_top_midfielders VARCHAR(40), "
          		+ "rank_in_league_top_defenders VARCHAR(40), "
          		+ "rank_in_club_top_scorer VARCHAR(40) "
          		+ ")";
       CrearTabla(c);
       Insertar_datos(lista,equipos);
       	}

}
