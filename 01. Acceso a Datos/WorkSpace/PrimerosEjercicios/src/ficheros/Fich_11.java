package ficheros;

import java.io.File;

public class Fich_11 {
	//Si tiene permisos de escritura, lectura y ejecutable
	public static void main(String[] args) {
		String directorio = "C:\\Ficheros",permisos;
		File fich = new File(directorio);
		if(!fich.exists()) {
			fich.mkdir();
			System.out.println("Fichero creado con éxito");
		}
		else {
			if(fich.isDirectory()) {
				File [] files = fich.listFiles(); 
				for (File file: files) {
					permisos = file.isDirectory() ? "d":"-";
					permisos += file.canRead() ? "r":"-";
					permisos += file.canWrite() ? "w":"-";
					permisos += file.canExecute() ? "x":"-";
					System.out.println(permisos+" " + file.getName());
				}
			}
			
			}
	}
}
