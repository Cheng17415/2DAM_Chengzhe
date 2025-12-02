package Principal;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Clases.Biblioteca;
import Clases.CListaRegistros;
import Clases.CRegistro;

public class Inicio {
	/*String cadena = "Hola", cadena1 = "Hola";
	if(cadena.equals(cadena1)) {
		System.out.println("Son iguales");
	}else {
		System.out.println("Son distintos");
	}
	CRegistro c1 = new CRegistro("1AAAA",125.30);
	CRegistro c2 = new CRegistro("1AAAA",125.30);
	
	System.out.println(c1.hashCode());
	System.out.println(c2.hashCode());
	
	if(c1.equals(c2)) {
		System.out.println("Son iguales");
	}else {
		System.out.println("Son distintos");
	}*/
	
	/*ArrayList<CRegistro> lista = new ArrayList<CRegistro>();
	lista.add(new CRegistro("1AA",120.50));
	lista.add(new CRegistro("2AA",220.50));
	
	lista.remove(new CRegistro("2AA",220.50)); //Lo quita ya que tenemos HashCode y para la máquina es el mismo objeto
	
	 for (CRegistro cRegistro : lista) {
		System.out.println(cRegistro);
	}*/
	 public static Scanner sc=new Scanner(System.in);

	    /*********************************************************************************************/
	    public static void actualizar(File factual,CListaRegistros lis) throws IOException {
	    	File fnuevo=new File("temporal.tmp");
	    	CListaRegistros nuevo=new CListaRegistros(fnuevo);
	    	CRegistro p;
	    	for(int i=0;i<lis.longitud();i++) {
	    		p=lis.valorEn(i);
	    		if (p.getPrecio()!=-100)nuevo.añadir(p);
	    	}
	    	lis.cerrar();
	    	nuevo.cerrar();
	    	factual.delete();
	    	if (!fnuevo.renameTo(factual)) throw new IOException("no se renombro el fichero");
	    	
	    }
	    /*********************************************************************************************/
	      public static void añadirListin(CListaRegistros lis) throws IOException {
	     	 String referencia;
	     	 double precio;
	     	 System.out.print("Referencia: ");referencia=sc.nextLine() ;
	     	 System.out.print("Precio: ");precio=Double.valueOf(sc.nextLine()) ;
	     	 
	     	 lis.añadir(new CRegistro(referencia,precio));
	      }
	 
	    /*********************************************************************************************/
	     public static void listar(CListaRegistros lis) throws IOException {
	    	 for(int i=0;i<lis.longitud();i++) System.out.println( lis.valorEn(i));
	     }
	     /*********************************************************************************************/
			public static int buscarReferencia(CListaRegistros lis,int posicion,String ref) throws IOException {
				int n= lis.buscar(ref,posicion);
				if (n<0) {
					System.out.println("Referencia no encontrada");
				}else {
					System.out.println(lis.valorEn(n));
				}
				return n;
			}
			/*********************************************************************************************/
			public static void modificar(CListaRegistros lis) throws IOException {
				System.out.print("Dime la referencia ");String ref=sc.nextLine();
				
				int n= lis.buscar(ref,0);
				if (n<0) {
					System.out.println("Referencia no encontrado");
					return ;
				}
				
				CRegistro p=lis.valorEn(n);
				
				String referencia,precio;
		    	 System.out.print("Referencia ");referencia=sc.nextLine() ;
		    	 System.out.print("Precio ");precio=sc.nextLine() ;

				 if (!referencia.equals(""))p.setReferencia(referencia);

				 if (!precio.equals(""))p.setPrecio(Double.valueOf(precio));

				 
				 lis.ponerValorEn(n, p);
				
				
			}	
	     /*********************************************************************************************/

		public static void main(String[] args) throws IOException {
		boolean eliminado=false;
		
		File fichero= nuevoFichero();	
		CListaRegistros listin=new CListaRegistros(fichero);
		
		int op,pos=0;	
		String referencia=null;
		String opciones[]= {"1.-Buscar","2.-Buscar siguiente","3.-Modificar","4.-Añadir","5.-Eliminar",
				"6.-Listar","7.-Cambiar nombre archivo","8.-Salir"};
		
		    do {
		    	op=Biblioteca.menu(sc, opciones);
		    	switch(op) {
		    	case 1: //buscar
		    		System.out.print("Dime la referencia ");referencia=sc.nextLine() ;
		    		pos=buscarReferencia(listin,0,referencia);
		    		
		    		break;
		    	case 2: // buscar siguiente
		    		if (referencia==null) {
		    			System.out.print("Dime la referencia ");referencia=sc.nextLine();
		    			pos=-1;
		    		}
		    		pos=buscarReferencia(listin,pos+1,referencia);
		    		break;
		    	case 3: //Modificar
		    		modificar(listin);
		    		break;
		    	case 4: //añadir registros
		    		añadirListin(listin);
		    		break;
		    	case 5: //eliminar
		    		System.out.print("Dime la referencia a eliminar");
		    		referencia=sc.nextLine();
		    		
		    		if (listin.eliminar(referencia)) eliminado=true;
		    		break;
		    	case 6: //listar
		    		listar(listin);
		    		break;
		    	case 7:
		    		listin.cerrar();
		    		fichero = nuevoFichero();
		    		listin=new CListaRegistros(fichero);
		    		pos = 0;
		    		referencia = null;
		    		eliminado = false;
		    		break;
		    	case 8: //salir
		    		if (eliminado)actualizar(fichero,listin);
		    		break;
		    	}	
		    	if (op!=8) {
		    		System.out.print("Presione una tecla para continuar");
		    		sc.nextLine();
		    	}
		
		    }while(op!=8);
		}
		
		public static File nuevoFichero() {
			String nombre = "";
			do{
				System.out.println("¿Qué nombre y extensión tiene el fichero? Ej:(listRegistros.dat)");
				nombre = sc.nextLine();
			} while (!nombre.contains("."));
			
			return new File(nombre);
		}

	}
