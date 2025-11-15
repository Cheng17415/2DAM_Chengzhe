package Clases;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CListaTfnos {
   private RandomAccessFile fes;
   private int nregs;
   private int tamañoReg=140;
   
   public CListaTfnos(File fichero) throws IOException {
     
	   if (fichero.exists() && !fichero.isFile())
		   throw new IOException(fichero.getName()+" no es un fichero");
	   
	   fes= new RandomAccessFile(fichero,"rw");
	   
	   nregs=   (int) Math.ceil((double)fichero.length()/(double)this.tamañoReg);
	   
   }
   /*****************************************************************************/
   public void cerrar() throws IOException { fes.close();}
   /*****************************************************************************/
   public int longitud() {return nregs; }
   /****************************************************************************/
   public void añadir(CPersona p) throws IOException {
	   if (ponerValorEn(nregs,p)) nregs++;
   }
   /*****************************************************************************/
   public int buscar(String str,int pos) throws IOException {
	   CPersona p;
	   if (str==null)return -1;
	   if (pos<0)pos=0;
	   
	   for(int i=pos;i<nregs;i++) {
		   p=valorEn(i);
		   if (str.equalsIgnoreCase(p.getNombre())) return i;
	   }
	   return -1;
   }
   /**
 * @throws IOException ***************************************************************************/
   public boolean eliminar(long tel) throws IOException {
	   CPersona p;
	   for(int i=0;i<nregs;i++) {
		   p=valorEn(i);
		   if (tel==p.getTelefono()) {
			   p.setTelefono(0);
			   ponerValorEn(i,p);
			   return true;
		   }
	   }
	   return false;
   }
   /*****************************************************************************/
   public boolean ponerValorEn(int i,CPersona p) throws IOException {
	  
	 if (i>=0 && i<=nregs) {
	 
		if (p.tamaño()+4>tamañoReg) {
			System.err.println("tamaño del registro excedido");
		}else {
		   fes.seek(i*tamañoReg);
		   fes.writeUTF(p.getNombre());
		   fes.writeUTF(p.getDireccion());
		   fes.writeLong(p.getTelefono());
	       return true; 
		}
	 }else {
		 System.err.println("numero de registro fuera de limites");
	 }
	   
	 return false;
   }
   /****************************************************************************************/
   public CPersona valorEn(int i) throws IOException {
	   
	   if (i>=0 && i<nregs) {
		   fes.seek(i*tamañoReg);
		   return new CPersona(fes.readUTF(),fes.readUTF(),fes.readLong());
	   
	   }else {
		   System.err.println("numero de registro fuera de limites");
	   }
	   return null;
   }
   
}
