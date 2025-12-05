package principal;

import java.sql.Date;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import clases.Biblioteca;
import model.Cliente;
import model.Comercial;
import model.Pedido;
import clases.Apoyo;

public class Principal {
	static Scanner sc = new Scanner(System.in);
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Examen");
	static EntityManager em = emf.createEntityManager();
	static Apoyo apoyo = new Apoyo(em);
	public static void main(String[] args) {
		
		menuPrincipal();
		em.close();
		emf.close();
	}
	
	public static void menuPrincipal() {
		String [] opciones = {"1. Pedido", "2. Cliente", "3. Salir"};
		int opcion = -1;
		while(true) {
			opcion = Biblioteca.menu(sc, opciones);
			switch(opcion) {
			case 1:
				menuPedido();
				break;
			case 2:
				menuCliente();
				break;
			case 3:
				System.out.println("Gracias por utilizar el programa");
				return;
			}
		}
	}
	
	public static void menuPedido() {
		String [] opciones = {"1. Crear pedido", "2. Visualizar pedidos","3. Modificar pedido","4. Eliminar pedido",  "5. Salir"};
		int opcion = -1;
		while(true) {
			opcion = Biblioteca.menu(sc, opciones);
			switch(opcion) {
			case 1:
				crearPedido();
				break;
			case 2:
				apoyo.visualizarPedidos();
				break;
			case 3:
				modificarPedido();
				break;
			case 4:
				eliminarPedido();
				break;
			case 5:
				System.out.println("Volviendo al menú anterior" + "\n");
				return;
			}
			System.out.println("Presione enter para continuar...");
			sc.nextLine();
		}
	}
	
	public static void menuCliente() {
		String [] opciones = {"1. Crear cliente", "2. Visualizar clientes","3. Modificar cliente","4. Eliminar cliente",  "5. Salir"};
		int opcion = -1;
		while(true) {
			opcion = Biblioteca.menu(sc, opciones);
			switch(opcion) {
			case 1:
				crearCliente();
				break;
			case 2:
				apoyo.visualizarClientes();
				break;
			case 3:
				modificarCliente();
				break;
			case 4:
				eliminarCliente();
				break;
			case 5:
				System.out.println("Volviendo al menú anterior" + "\n");
				return;
			}
			System.out.println("Presione enter para continuar...");
			sc.nextLine();
		}
	}
	
	public static void crearCliente() {
		System.out.println("Has seleccionado Crear Cliente");
		System.out.println("==============================");
		
		String nombre = obtenerObligatorio("Indique el nombre: ");
		String apellido1 = obtenerObligatorio("Indique el primer apellido: ");
		
		Cliente c = new Cliente(nombre, apellido1);
		
		String apellido2 = obtenerNoObligatorio("Indique el segundo apellido");
		String ciudad = obtenerNoObligatorio("Indique la ciudad");
		String categoria = obtenerNoObligatorio("Indique la categoría");
		
		if(!esVacio(apellido2)) {
			c.setApellido2(apellido2);
		}
		if(!esVacio(ciudad)) {
			c.setCiudad(ciudad);
		}
		Integer cat;
		try {
			if(esVacio(categoria)) {
				cat = null;
			} else {
				cat = Integer.valueOf(categoria);
			}		
		} catch(Exception e){
			System.err.println(e.getMessage());
			cat = null;
		}
		if(cat != null) {
			c.setCategoría(cat);
		}
		apoyo.anadirCliente(c);
	}
	
	public static void crearPedido() {
		System.out.println("Has seleccionado Crear Pedido");
		System.out.println("==============================");
		double total = -1;
		while(total < 0) {
			try {
				System.out.println("Indique el total: ");
				total = Double.valueOf(sc.nextLine());
			} catch(NumberFormatException e){
			}
			if(total < 0) System.out.println("Debes introducir un numero válido");
		}
		String idCliente = "", idComercial = "";
		Cliente c = null;
		Comercial co = null;
		while(esVacio(idCliente) || c == null) {
			try {
				idCliente = obtenerObligatorio("Indique el ID del cliente: ");
				c = apoyo.buscarCliente(Integer.valueOf(idCliente));
			} catch(NumberFormatException e) {
				System.out.println("Debes introducir un ID válido");
			}
			if(c == null) {
				System.out.println("No se ha encontrado al cliente");
			}
		}
		
		while(esVacio(idComercial) || co == null) {
			try {
				idComercial = obtenerObligatorio("Indique el ID del comercial: ");
				co = apoyo.buscarComercial(Integer.valueOf(idComercial));
			} catch(NumberFormatException e) {
				System.out.println("Debes introducir un ID válido");
			}
			if(co == null) {
				System.out.println("No se ha encontrado al comercial");
			}
		}
		
		Pedido p = new Pedido(total,c,co);
		System.out.println("¿Desea agregar fecha? Y/N");
		String respuesta = sc.nextLine().strip();
		if(respuesta.equalsIgnoreCase("Y")) {
			Date fecha = obtenerFecha();
			if(fecha != null) {
				p.setFecha(fecha);
			}
		}
		apoyo.anadirPedido(p);
	}
	public static Date obtenerFecha() {
		int anio = -1, mes = -1, dia = -1;
		while(anio < 1 || anio >9999) {
			try {
				System.out.println("Indique el año: ");
				anio = Integer.valueOf(sc.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Debes introducir un año válido");
			}
		}
		while(mes < 1 || mes > 12) {
			try {
				System.out.println("Indique el mes: ");
				mes = Integer.valueOf(sc.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Debes introducir un mes válido");
			}
		}
		while(dia < 1 || dia >31) {
			try {
				System.out.println("Indique el dia: ");
				dia = Integer.valueOf(sc.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Debes introducir un mes válido");
			}
		}
		
		try {
			Date date = new Date(anio, mes, dia);
			return date;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void modificarPedido() {
		int id = -1;
		Pedido c = null;
		while(id <1) {
			try {
				id = Integer.valueOf(obtenerObligatorio("Introduzca la ID del pedido a modificar"));
				c = apoyo.buscarPedido(id);
			} catch(NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		}
		if(c == null) {
			return;
		}
		
		
	}
	public static void modificarCliente() {
		int id = -1;
		Cliente c = null;
		while(id <1) {
			try {
				id = Integer.valueOf(obtenerObligatorio("Introduzca la ID del cliente a modificar"));
				c = apoyo.buscarCliente(id);
			} catch(NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		}
		if(c == null) {
			return;
		}
		System.out.println(c.getNombre() + " (No escribas para no modificar): ");
		String nombre = sc.nextLine().strip();
		if(esVacio(nombre)) {
			nombre = c.getNombre();
		}
		System.out.println(c.getApellido1() + " (No escribas para no modificar): ");
		String apellido1 = sc.nextLine().strip();
		if(esVacio(apellido1)) {
			apellido1 = c.getApellido1();
		}
		System.out.println(c.getApellido2() + " (No escribas para no modificar): ");
		String apellido2 = sc.nextLine().strip();
		if(esVacio(apellido2)) {
			apellido2 = c.getApellido2();
		}
		System.out.println(c.getCiudad() + " (No escribas para no modificar): ");
		String ciudad = sc.nextLine().strip();
		if(esVacio(ciudad)) {
			ciudad = c.getCiudad();
		}
		System.out.println(c.getCategoría() + " (No escribas para no modificar");
		String categoria =sc.nextLine();
		Integer cat = null;
		if(esVacio(categoria)) {
			cat = c.getCategoría();
		} else {
			cat = Integer.valueOf(categoria);
		}
		c = new Cliente(nombre, apellido1);
		c.setApellido2(apellido2);
		c.setCiudad(ciudad);
		c.setCategoría(cat);
		apoyo.actualizarCliente(id, c);
		
	}
	public static void eliminarCliente() {
		int id = -1;
		while(id <1) {
			try {
				id = Integer.valueOf(obtenerObligatorio("Introduzca la ID del cliente a eliminar"));
				apoyo.eliminarCliente(id);
			} catch(NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	public static void eliminarPedido() {
		int id = -1;
		while(id <1) {
			try {
				id = Integer.valueOf(obtenerObligatorio("Introduzca la ID del pedido a eliminar"));
				apoyo.eliminarPedido(id);
			} catch(NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		}
	}	
	public static boolean esVacio(String s) {
		if(s.isEmpty()) return true;
		return false;
	}
	
	public static String obtenerObligatorio(String mensaje) {
		String s;
		do {
			System.out.println(mensaje);
			s = sc.nextLine().strip();
		} while(esVacio(s));
		return s;
	}
	
	public static String obtenerNoObligatorio(String mensaje) {
		System.out.println(mensaje + " (Puede no tener): ");
		return sc.nextLine().strip();
	}
}
