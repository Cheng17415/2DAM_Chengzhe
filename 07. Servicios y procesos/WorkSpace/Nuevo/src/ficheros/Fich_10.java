package ficheros;

import java.io.File;


public class Fich_10 {
	public static void main(String[] args) {
		//Borrar los csv que están en ese directorio
		//C:\\WorkspaceCheng\\Nuevo
		String camino = "C:\\WorkspaceCheng\\Nuevo";
		File fich = new File(camino);
		if(!fich.exists()) {
			fich.mkdir();
			System.out.println("Fichero creado con éxito");
		}
		else {
			if(fich.isFile()) System.out.println("Es un archivo");
			else {
				System.out.println("Fichero ya existe");
				File [] subFich = fich.listFiles();
				for (File file : subFich) if(file.isFile() &&file.getName().endsWith(".csv")) file.delete();
			}
			
			}
		
		}
	}
