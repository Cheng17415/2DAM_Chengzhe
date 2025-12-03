package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import Clases.Persona;
import Clases.laligaClubFecha;

public class ejer04 {

	public static void main(String[] args) {
		SimpleDateFormat f=new SimpleDateFormat("yyyy/mm/dd");
        String lectura="jugadores.csv";
        String linea,cabecera="";
        ArrayList<laligaClubFecha> lista=new ArrayList<laligaClubFecha>();
        
       try(BufferedReader flectura=new BufferedReader(new FileReader(lectura))){
    	   cabecera=flectura.readLine();
    	   linea=flectura.readLine();
    	   while(linea!=null) {
    		   String[] campos=linea.split(",");
    		   
    		    String[] fechas=campos[3].split("/");
    		   LocalDate ff=LocalDate.of(
    				   Integer.valueOf(fechas[0]),
    				   Integer.valueOf(fechas[1]),
    				   Integer.valueOf(fechas[2])
    						   );
    		   
    		   lista.add(new laligaClubFecha(campos[7],ff,linea));
    		   linea=flectura.readLine();
    	   }
       }catch(IOException e) {
    	   System.out.println(e.getMessage());
       }
       /**************************************************************/
       Collections.sort(lista,new Comparator<laligaClubFecha>() {

		@Override
		public int compare(laligaClubFecha o1, laligaClubFecha o2) {
			int n= -1*(o2.getClub().compareTo(o1.getClub()));
			if (n!=0)return n;
			return -1*(o2.getFecha().compareTo(o1.getFecha()));
		}
    	   
       });
       /**************************************************************/
       String escritura="ficheroWrite.dat";
       try(BufferedWriter fescritura=new BufferedWriter(new FileWriter(escritura))){
    	   fescritura.write(cabecera+"\n");
    	   for (laligaClubFecha li : lista)	
    	  fescritura.write(li.getLinea()+"\n");
		}catch(IOException e) {
    	   System.out.println(e.getMessage());
       }
       /**************************************************************/
       File fficheroRead=new File("jugadores.csv");
       File fficheroWrite=new File("ficheroWrite.dat");
       if (fficheroRead.delete()) {
    	   System.out.println("Fichero Borrado");
    	   boolean pudo=fficheroWrite.renameTo(fficheroRead);
    	   if (pudo) {
    		   System.out.println("Se renombro.");
    	   }else {
    		   System.out.println("No pudo renombrarse.");
    	   }
       }else {
    	   System.out.println("No se pudo borrar el fichero.");
       }
	}

}
