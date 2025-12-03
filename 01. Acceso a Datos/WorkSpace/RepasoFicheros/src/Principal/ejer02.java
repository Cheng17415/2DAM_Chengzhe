package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Clases.Persona;

public class ejer02 {

	public static void main(String[] args) {
        String lectura="jugadores.csv";
        String linea;
        ArrayList<Persona> lista=new ArrayList<Persona>();
        
       try(BufferedReader flectura=new BufferedReader(new FileReader(lectura))){
    	   linea=flectura.readLine();
    	   while(linea!=null) {
    		   String[] campos=linea.split(",");
    		   lista.add(new Persona(campos[0],Integer.valueOf(campos[1])));
    		   linea=flectura.readLine();
    	   }
       }catch(IOException e) {
    	   System.out.println(e.getMessage());
       }
       /**************************************************************/
       Collections.sort(lista,new Comparator<Persona>() {

		@Override
		public int compare(Persona o1, Persona o2) {
			int n= -1*(o2.getEdad()-o1.getEdad());
			if (n!=0)return n;
			return -1*(o2.getNombre().compareTo(o1.getNombre()));
		}
    	   
       });
       /**************************************************************/
       String escritura="ficheroWrite.dat";
       try(BufferedWriter fescritura=new BufferedWriter(new FileWriter(escritura))){
    	   for (Persona li : lista)	
    	  fescritura.write(li.getNombre()+":"+li.getEdad()+"\n");
		}catch(IOException e) {
    	   System.out.println(e.getMessage());
       }
       /**************************************************************/
       File fficheroRead=new File("ficheroRead.dat");
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
