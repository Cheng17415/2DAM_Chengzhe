package ficherosRAF;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Clases.Biblioteca;

public class Inicio {
	public static Scanner sc = new Scanner(System.in);
	/**
	 * @throws IOException *************************************************************************************************/
	public static void anadirListin(CListaTfnos lis) throws IOException {
		String nombre, direccion;
		long telefono;
		System.out.println("Nombre "); nombre = sc.nextLine();
		System.out.println("Direccion "); direccion = sc.nextLine();
		System.out.println("Telefono "); telefono = Long.valueOf(sc.nextLine());
		
		lis.anadir(new CPersona(nombre,direccion,telefono));
	}
	/***************************************************************************************************/
	public static void listar(CListaTfnos lis) throws IOException {
		for (int i = 0; i < lis.longitud(); i++) System.out.println(lis.valorEn(i));
	}
	
	/***************************************************************************************************/
	public static int buscarNombre(CListaTfnos lis, int pos, String nom) throws IOException{
		int n = lis.buscar(nom, pos);
		if(n<0) {
			System.out.println("Nombre no encontrado");
		}else {
			System.out.println(lis.valorEn(n));
		}
		return n;
	}
	/***************************************************************************************************/
	public static void modificar(CListaTfnos lis) throws IOException{
		System.out.println("Nombre de la persona a modificar"); String nom = sc.nextLine();
		
		int n = lis.buscar(nom, 0);
		if(n<0) {
			System.out.println("Nombre no encontrado");
			return;
		}
		CPersona p = lis.valorEn(n);
		
		String nombre, direccion, telefono;
		System.out.print("Nombre(" + p.getNombre() + ")"); nombre = sc.nextLine();
		System.out.print("Direccion(" + p.getDireccion() + ")"); direccion = sc.nextLine();
		System.out.print("Telefono(" + p.getTelefono() + ")"); telefono = sc.nextLine();
		
		if(!nombre.equals("")) p.setNombre(nombre);
		if(!direccion.equals("")) p.setDireccion(direccion);
		if(!telefono.equals("")) p.setTelefono(Long.valueOf(telefono));
		
		lis.ponerValorEn(n, p);
		
	}

	 public static void actualizar(File factual,CListaTfnos lis) throws IOException {
	    	File fnuevo=new File("temporal.tmp");
	    	CListaTfnos nuevo=new CListaTfnos(fnuevo);
	    	CPersona p;
	    	for(int i=0;i<lis.longitud();i++) {
	    		p=lis.valorEn(i);
	    		if (p.getTelefono()!=0)nuevo.anadir(p);
	    	}
	    	lis.cerrar();
	    	nuevo.cerrar();
	    	factual.delete();
	    	if (!fnuevo.renameTo(factual)) throw new IOException("no se renombro el fichero");
	    	
	    }
	/***************************************************************************************************/
	public static void main(String[] args) throws IOException {
		Boolean borrado = false;
		File fichero = new File("listfn.dat");
		CListaTfnos listin = new CListaTfnos(fichero);
		
		int op, pos = 0;
		String nombre = null;
		long tel;
		String opciones [] = {"1.-Buscar","2.-Buscar siguiente","3.-Modificar","4.-Añadir","5.-Eliminar",
				"6.-Listar","7.-Salir"};
		do{
			op=Biblioteca.menu(sc,opciones);
			switch(op) {
			case 1: //Buscar por nombre
				System.out.println("Nombre de la persona a buscar");
				nombre = sc.nextLine();
				pos = buscarNombre(listin,0,nombre);
				break;
			case 2: //Buscar siguiente
				if(nombre == null) {
					System.out.println("Nombre de la persona a buscar");
					nombre = sc.nextLine();
					pos = -1;
				}
				pos = buscarNombre(listin,pos + 1,nombre);
				break;
			case 3: //Modificar
				modificar(listin);
				break;
			case 4://Añadir registro
				anadirListin(listin); 
				break;
			case 5: //Eliminar
				System.out.println("Introduzca el teléfono de la persona a eliminar");
				tel = Long.valueOf(sc.nextLine());
				if(listin.eliminar(tel)) borrado = true;
				
				break;
			case 6: //Listar
				listar(listin);
				break;
			case 7://Salir
				if(borrado) actualizar(fichero, listin);
				break;
			default:
				System.out.println("Pon un número correcto");
			}
			if(op!=7) {
				System.out.println("Presione una tecla para continuar");
				sc.nextLine();
			}
		}while(op!=7);
		
	}
	
}
