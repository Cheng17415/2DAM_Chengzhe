package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ejer01 {

	public static void main(String[] args) {
        String lectura="ficheroRead.dat";
        String linea;
        ArrayList<String> lista=new ArrayList<String>();
        
       try(BufferedReader flectura=new BufferedReader(new FileReader(lectura))){
    	   linea=flectura.readLine();
    	   while(linea!=null) {
    		   lista.add(linea);
    		   linea=flectura.readLine();
    	   }
       }catch(IOException e) {
    	   System.out.println(e.getMessage());
       }
       /**************************************************************/
       Collections.sort(lista);
       /**************************************************************/
       String escritura="ficheroWrite.dat";
       try(BufferedWriter fescritura=new BufferedWriter(new FileWriter(escritura))){
    	   for (String li : lista)	fescritura.write(li+"\n");
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
