package Principal;

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

import Generales.Biblioteca;

public class cajero {
   static BufferedReader sc=
   new BufferedReader(new InputStreamReader(System.in));
   static String bbdd=null;
   static String password="root";
   static String conexion="jdbc:mysql://localhost:3306/";
   /**********************************************************/
   public static boolean IsNatural(int n) { return n>=0; }
   /**
 * @throws SQLException ********************************************************/
   public static boolean IsMoneda(double moneda)  {
	   int n=0;
	   try(
	   Connection con=DriverManager.getConnection(conexion+bbdd,"root",password);
	   PreparedStatement s=con.prepareStatement("select count(*) nu from cajero where moneda= ?"); 		   
	   ){
		   s.setDouble(1,moneda);
		   ResultSet r=s.executeQuery();
		   while(r.next())n=r.getInt("nu");
	   }catch(SQLException e) {
		   System.out.println(e.getMessage());
	   }catch(Exception e) {}
	   if (n==0)return false;
	   return true;
   }
   /**********************************************************/
   public static void Crear_Schema()  {
	   try(
	   Connection con=DriverManager.getConnection(conexion+"sys","root",password);
	   PreparedStatement s=con.prepareStatement("CREATE SCHEMA IF NOT EXISTS "+bbdd); 		   
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
   public static void CrearTabla()  {
   String c="CREATE TABLE CAJERO(MONEDA DOUBLE PRIMARY KEY,CANTIDAD INT CHECK(CANTIDAD>=0))";
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
   /**********************************************************/
   public static void Insertar_datos()  {
   String c="INSERT INTO CAJERO VALUES(?,?)";
   int[] numero= {5,2,1};
   int row;
	   try(
	   Connection con=DriverManager.getConnection(conexion+bbdd,"root",password);
	   PreparedStatement s=con.prepareStatement(c); 		   
	   ){
		   for(int i=2;i>=-2;i--) {
			   for(int n:numero) {
				   s.setDouble(1, Math.pow(10,i)*n);
				   s.setInt(2, 10);
				   row=s.executeUpdate();
			   }
		   }
	   }catch(SQLException e) {
		   System.out.println(e.getMessage());
	   }catch(Exception e) {}
   }
   /**
 * @throws IOException ********************************************************/
   public static void cambio() throws IOException {
	   System.out.print("Indique la BBDD "); bbdd=sc.readLine();
	   if (!ExisteBD(bbdd)) {
		   Crear_Schema();
		   CrearTabla();
		   Insertar_datos();
	   }
	   
   }
   /********************************************************/
   public static void listar() {
	String ca="SELECT * FROM CAJERO ORDER BY MONEDA DESC";
	String cadena="";
	int n=4,c=0;
	try(
			   Connection con=DriverManager.getConnection(conexion+bbdd,"root",password);
			   PreparedStatement s=con.prepareStatement(ca); 		   
			   ){
		           ResultSet r=s.executeQuery();
		           while(r.next()) {
		              c++;
		              cadena +=String.format("%10.2f (%5d)",
		            		  r.getDouble("MONEDA"),r.getInt("CANTIDAD"));
		              if (c%n==0) {
		            	  System.out.println(cadena);
		            	  cadena="";
		              }
		           }
				   System.out.println(cadena);
			   }catch(SQLException e) {
				   System.out.println(e.getMessage());
			   }catch(Exception e) {}
   }
   /**
 * @throws IOException ******************************************************/
   public static void Introducir_Dinero() throws IOException {
	double suma=0,moneda;
    int cantidad;
	String ca="UPDATE CAJERO SET CANTIDAD=CANTIDAD+? WHERE MONEDA=?";
	System.out.print("Dime el dinero a introducir ");String pago=sc.readLine();
	//1-500#10-50#100-0.05
	
	try(
			   Connection con=DriverManager.getConnection(conexion+bbdd,"root",password);
			   PreparedStatement s=con.prepareStatement(ca); 		   
			   ){
		          con.setAutoCommit(false);
		          for(String item:pago.split("#")) {
		        	  String partes2[]=item.split("-");
		        	  cantidad=Integer.valueOf(partes2[0]);
		        	  moneda=Double.valueOf(partes2[1]);
		        	  if (IsNatural(cantidad) && IsMoneda(moneda)) {
		        		  suma +=cantidad*moneda;
		        		  s.setInt(1, cantidad);
		        		  s.setDouble(2, moneda);
		        		  s.addBatch();
		        	  }
		          }
		          int[] rows=s.executeBatch();
		          System.out.println("\nHa introducido usted en la caja "+suma+"\n");
		          con.commit();
			   }catch(SQLException e) {
				   System.out.println(e.getMessage());
			   }catch(Exception e) {}
   }
   /**
 * @throws IOException * 
 * @throws SQLException ********************************************************/
    public static void Transferir() throws IOException, SQLException {
    	String origen,destino;
    	
    	System.out.print("Indique el origen de la transacción "); origen=sc.readLine();
    	if (!ExisteBD(origen)) {
    		System.out.println("La BBDD "+origen+" no existe.");
    		return;
    	}
    	System.out.print("Indique el destino de la transacción "); destino=sc.readLine();
    	if (!ExisteBD(destino)) {
    		System.out.println("La BBDD "+destino+" no existe.");
    		return;
    	}
    	String c="UPDATE "+destino+".CAJERO A SET CANTIDAD=CANTIDAD+(SELECT CANTIDAD FROM "+origen+".cajero B WHERE A.MONEDA=B.MONEDA)";
    	Connection con=null;
    	try{
 			   con=DriverManager.getConnection(conexion+"sys","root",password);
 			   PreparedStatement p1=con.prepareStatement(c);
 			   PreparedStatement p2=con.prepareStatement("UPDATE "+origen+".cajero SET CANTIDAD=0");
 			       con.setAutoCommit(false);
 			       int r1=p1.executeUpdate();
 			       int r2=p2.executeUpdate();
 			       con.commit();
 			   }catch(SQLException e) {
 				   con.rollback();
 				   System.out.println(e.getMessage());
 			   }catch(Exception e) {}
    	
    }
    /**
     * @throws IOException ******************************************************/
       public static void Realizar_Compra() throws NumberFormatException, IOException, SQLException {
    		TreeMap<Double,Integer> caja=new TreeMap<Double,Integer>();
    		
    		System.out.print("Dime el importe de la compra ");
    		double importe=Double.valueOf(sc.readLine());
    		
    		System.out.print("Dime la forma de pago ");
    		String pago=sc.readLine();
    		
    		double suma = 0,moneda,devolver;
    		int cantidad;

    		    for( String item:  pago.split("#") ) {
    		    	String partes2[] = item.split("-");
    		    	cantidad= Integer.valueOf(partes2[0]);
    		    	moneda=Double.valueOf(partes2[1]);
    		    	if (IsNatural(cantidad) && IsMoneda(moneda)) {
    		    		suma +=cantidad*moneda;
    		    		caja.put(moneda,cantidad);
    		    	}
    		    }
            devolver=Biblioteca.redondear(suma-importe,2);
    		 if (devolver<0 ) {
    			 System.out.println("Falta dinero "+Math.abs(devolver));
    			 return;
    		 }
    		
    		String query="update cajero set cantidad=cantidad+? where moneda=?";
    		String query1="select * from cajero where moneda<=? and cantidad>0 order by moneda desc";
    		
       	 try(Connection conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bbdd, "root", password);
           		 PreparedStatement pu = conn.prepareStatement(query);
           		 PreparedStatement pu1 = conn.prepareStatement(query1);){
       		 
       		 conn.setAutoCommit(false);
       		 
       		 for( Entry<Double,Integer> item:  caja.entrySet()) {
       			 pu.setInt(1,(Integer)item.getValue());
       			 pu.setDouble(2,(Double)item.getKey());
       			 pu.addBatch();
       		 }
       		 
       		 int[] rows = pu.executeBatch();
       		 if (devolver>=0 && devolver<0.01) {
       			 System.out.println("Precio exacto");
       			 conn.commit();
       			 return;
       		 }
       		 pu1.setDouble(1, devolver);
       		 ResultSet r = pu1.executeQuery();
                String adevolver="A devolver "+devolver+"\n";
                int n;
       	        while (r.next() && devolver > 0.009) {
                       moneda=r.getDouble("MONEDA");
                       cantidad=r.getInt("CANTIDAD");
                       if (!(moneda > devolver || cantidad == 0)) {	
                       	n = (int) (devolver / moneda);
                       	if (cantidad < n)	n = cantidad;
                       	devolver = Biblioteca.redondear(devolver - (n * moneda), 2);
                       	adevolver += String.format("%2d %6.2f", n, moneda) + "\n";
              			    pu.setInt(1,-n);
           			    pu.setDouble(2,moneda);
           			    pu.addBatch();
                       }	
       	        }
       	     rows = pu.executeBatch();
       	     
       	     if (devolver > 0.01) {
       	    	 System.out.println("No tengo cambio.");
       	    	 conn.rollback();
       	    	 return;
       	     }
       		 	
       		 conn.commit();
       		 System.out.println(adevolver);

           } catch (SQLException e) {
               System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
             
           } catch (Exception e) {
               e.printStackTrace();
           }
    	}

   /**
 * @throws SQLException *******************************************************/
	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException, SQLException {
     String[] opciones= {"1.-Listar","2.-Realizar Compra",
    		 "3.-Introducir dinero","4.-Transferir fondos",
    		 "5.-Cambiar BBDD","6.-Salir"};
     String pausa;
     int op;
     cambio();
     do {
    	 Biblioteca.ClearConsole();
    	 System.out.println("\nEsta usted en la BBDD "+bbdd+"\n");
    	 op=Biblioteca.menu(opciones, sc);
    	 switch(op) {
    	 case 1: // listar
    		 listar();
    		 break;
    	 case 3: // Introducir dinero
    		 Introducir_Dinero();
    		 break;
    	 case 4: // tranferir fondos
    		 Transferir();
    		 break;	 
    	 case 5: // cambiar bbdd
    		 cambio();
    		 break;	 
    		 
    
    	 }
         if (op!=6) {	 
    	   System.out.println("Presione una tecla para continuar");
    	   pausa=sc.readLine();
         }else {
        	 break;
         }
    	 
     }while(true);

	}

}
