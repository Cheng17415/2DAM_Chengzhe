package ficherosRAF;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CListaTfnos {
	private RandomAccessFile fes;
	private int nregs;
	private int tamanoReg = 140;
	/*
	 * Cada registro max 140 bytes El último puede no ocupar el máximo Por ello,
	 * 380/140 = 2,7 Math.ceiling() = 3
	 */
	public CListaTfnos(File fichero) throws IOException {
		if (fichero.exists() && !fichero.isFile()) {
			throw new IOException(fichero.getName() + " no es un fichero");
		}
		fes = new RandomAccessFile(fichero, "rw");
		nregs = (int) Math.ceil((double)fichero.length() / (double)this.tamanoReg);
	}

	/***********************************************************************************/
	public void cerrar()throws IOException {fes.close();}
	/***********************************************************************************/
	public int longitud () {return nregs;}
	/**
	 * @throws IOException *********************************************************************************/
	public void anadir(CPersona p) throws IOException {
		if(ponerValorEn(nregs,p)) nregs++;
	}
	
	public int buscar(String str, int pos) throws IOException{
		CPersona p;
		if(str==null) return -1;
		if(pos<0) pos = 0;
		
		for(int i= pos; i< nregs; i++) {
			p = valorEn(i);
			if(str.equalsIgnoreCase(p.getNombre())) return i;
		}
		return -1;
	}
	
	public boolean eliminar(long tel) throws IOException{
		CPersona p;
		for(int i= 0;i<nregs; i++) {
			p=valorEn(i);
			if(tel == p.getTelefono()) {
				p.setTelefono(0);
				ponerValorEn(i,p);
				return true;
			}
		}
		return false;
	}
	/***********************************************************************************/
	public boolean ponerValorEn(int i, CPersona p) throws IOException {
		
		if (i >= 0 && i <= nregs) {
			
			if (p.tamano() + 4 > tamanoReg) {
				System.err.println("tamaño del registro excedido");
			} else {
				fes.seek(i * tamanoReg);
				fes.writeUTF(p.getNombre());
				fes.writeUTF(p.getDireccion());
				fes.writeLong(p.getTelefono());
				return true;
			}
		} else {
			System.err.println("numero de registro fuera de límites");
		}

		return false;
	}
	/***********************************************************************************/
	public CPersona valorEn(int i) throws IOException{
		if(i>=0 && i<nregs) {
			fes.seek(i * tamanoReg);
			return new CPersona(fes.readUTF(),fes.readUTF(),fes.readLong());
		}
		else {
			System.err.println("numero de registro fuera de límites");
		}
		return null;
	}

}
