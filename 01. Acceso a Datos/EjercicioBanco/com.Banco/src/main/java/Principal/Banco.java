package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Clases.Biblioteca;
import Clases.Cajero;

public class Banco {

	// VARIABLES GLOBALES
	static Scanner sc = new Scanner(System.in);
	public static ArrayList<Double> valores = new ArrayList<>();
	public static Cajero cajero;

	// INICIALIZACION
	public static void iniValores() {
		int[] nums = { 5, 2, 1 };
		for (int i = 2; i >= -2; i--) {
			for (int n : nums) {
				valores.add(n * Math.pow(10, i));
			}
		}
	}
	
	// CREACION DE BBDD
	public static boolean crearBBDD(String banco) {
		try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
				Statement statement = conexion.createStatement()) {

			String sql = "CREATE DATABASE " + banco;
			statement.executeUpdate(sql);
			return true;
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1007)
				System.out.println("La base de datos " + banco + " ya existe.");
			else
				System.err.format("Error SQL: %s %s\n", ex.getMessage(), ex.getSQLState());
		}
		return false;
	}
	
	// CREACION DE TABLA CAJERO
	public static boolean crearTabla() {
		String SQL_CREATETABLE = " CREATE TABLE IF NOT EXISTS CAJERO(" + "MONEDA DECIMAL(10,2) PRIMARY KEY,"
				+ "CANTIDAD INT CHECK(CANTIDAD>=0))";

		Connection conexion = cajero.getConexion();
		try (PreparedStatement pc = conexion.prepareStatement(SQL_CREATETABLE)) {

			pc.execute();
			return true;

		} catch (SQLException ex) {
			System.err.format("Error SQL: %s %s\n", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// INSERCION DE VALORES PREDETERMINADOS EN LA TABLA CAJERO
	public static boolean insertarValoresTabla() {
		String SQL_INSERTAR = "INSERT INTO CAJERO(MONEDA, CANTIDAD) VALUES (?, ?)";

		Connection conexion = cajero.getConexion();
		try (PreparedStatement pi = conexion.prepareStatement(SQL_INSERTAR)) {

			for (double val : valores) {
				pi.setDouble(1, val);
				pi.setInt(2, 10);
				pi.addBatch();
			}

			pi.executeBatch();
			conexion.commit();
			return true;

		} catch (SQLException ex) {
			System.err.format("%s %s\n", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	// CONEXION CON LA BASE DE DATOS
	public static void conectarBaseDatos() {
		System.out.print("Indique a que BBDD conectarse: ");
		String banco = sc.nextLine().trim();

		cajero = new Cajero(banco);

		if (crearBBDD(banco))
			System.out.println("Base de datos creada correctamente: " + banco);
		else {
			//Si no se ha creado la BBDD, ya existia la base de datos.
			cajero.conectarBBDD();
			return;
		}

		cajero.conectarBBDD();

		if (cajero.getConexion() == null) {
			System.out.println("Ha ocurrido un error al conectarse a la base de datos.");
			return;
		}

		if (!crearTabla()) {
			System.out.println("Ha ocurrido un error al crear la tabla cajero.");
			return;
		}

		if (!insertarValoresTabla()) {
			System.out.println("Ha ocurrido un error al insertar valores a la tabla cajero.");
			return;
		}

		System.out.println("Tabla creada exitosamente.");
		System.out.println("Valores insertados correctamente en la tabla Cajero.");
	}
	
	// LISTAR LAS MONEDAS Y CANTIDADES
	public static void listar() {
	    cajero.leerCajero();
	    double total = 0;

	    System.out.println("╔═══════════════╦══════════╦════════════╗");
	    System.out.println("║   MONEDA (€)  ║ CANTIDAD ║  IMPORTE € ║");
	    System.out.println("╠═══════════════╬══════════╬════════════╣");
	    
	    for (Map.Entry<Double, Integer> entry : cajero.getMapaDinero().entrySet()) {
	        double importe = entry.getKey() * entry.getValue();
	        total += importe;
	        System.out.printf("║ %11.2f € ║ %8d ║ %10.2f ║%n", entry.getKey(), entry.getValue(), importe);
	    }

	    System.out.println("╠═══════════════╬══════════╬════════════╣");
	    System.out.printf("║     TOTAL     ║          ║ %10.2f ║%n", total);
	    System.out.println("╚═══════════════╩══════════╩════════════╝");
	}

	
	// LOGICA RELACIONADA A LA COMPRA.
	public static void compra() {
		double compra = -1;

		do {
			System.out.print("Introduzca cantidad de euros de la compra: ");

			try {
				compra = Biblioteca.truncar(Double.valueOf(sc.nextLine()), 2);
			} catch (NumberFormatException ex) {
				System.out.println("Valor no valido. Introduzca un numero valido");
			}

		} while (compra < 0.01);

		HashMap<Double, Integer> dinero = dineroAPagar();

		if(cajero.realizarCompra(compra, dinero)) System.out.println("Compra realizada");
	}

	// LOGICA RELACIONADA CON EL DINERO QUE EL USUARIO VA A PAGAR.
	public static HashMap<Double, Integer> dineroAPagar() {
		boolean valido = false;
		HashMap<Double, Integer> dinero = new HashMap<>();
		do {
			System.out.print("Introduzca la cantidad y el valor de los billetes/monedas (1-50#2-20...): ");
			String pago = sc.nextLine();
			System.out.println();

			// Separar la String pago primero en # y luego en -
			for (String dinAgrup : pago.split("#")) {

				String[] dinPartes = dinAgrup.split("-");

				if (dinPartes.length != 2) {
					System.out.println("Debes seguir la estructura establecida.");
					continue;
				}
				
				dinero.put(Double.valueOf(dinPartes[1]), Integer.valueOf(dinPartes[0]));
			}

			valido = verificarDinero(dinero);

			if (!valido) System.out.println("El dinero introducido no es valido.");

		} while (!valido);
		return dinero;
	}

	
	public static void introDinero() {
		HashMap <Double,Integer> dinero = dineroAPagar();
		
		if(!verificarDinero(dinero)) {
			System.out.println("El dinero introducido no es valido.");
			return;
		}
		
		if(cajero.introducirDinero(dinero)) {
			System.out.println("Dinero introducido con exito.");
		} else {
			System.out.println("El dinero no se pudo introducir.");
		}
	}

	//VERIFICA QUE EL DINERO INTRODUCIDO EXISTAN EN LA ARRAY CON LOS VALORES DE LA MONEDA.
	public static boolean verificarDinero(Map<Double, Integer> dinero) {

		if (dinero.size() == 0) return false;

		int cont = 0;

		for (Double key : dinero.keySet()) {
			double keyCentimos = Biblioteca.redondear(key * 100, 2);
			
			for (double val : valores) {
				double valCentimos = Biblioteca.redondear(val * 100, 2);
				// Para que no haya problemas con los decimales
				if (Math.abs(keyCentimos - valCentimos) < 0.001) {
					cont++;
					break;
				}
			}
		}

		if (cont == dinero.size())
			return true;

		return false;
	}

	// TRANSFERIR FONDOS DE UN BANCO A OTRO.
	public static void transferirFondos() {
		System.out.print("Introduzca el nombre del banco de origen: ");
		String bancoOrigen = sc.nextLine().trim().toLowerCase();
		System.out.print("Introduzca el nombre del banco de destino: ");
		String bancoDestino = sc.nextLine().trim().toLowerCase();
		
		//Conexion temporal con la base de datos en localhost:3306/
		Cajero c = new Cajero("");
		c.conectarBBDD();
				
		String query1 = "UPDATE " + bancoDestino + ".cajero A SET CANTIDAD = CANTIDAD + "
				+ "(SELECT CANTIDAD FROM " + bancoOrigen + ".cajero B WHERE A.MONEDA = B.MONEDA)";
		String query2 = "UPDATE " + bancoOrigen + ".cajero A SET CANTIDAD = 0";
		
		try(PreparedStatement pu = c.getConexion().prepareStatement(query1);
			PreparedStatement pu2 = c.getConexion().prepareStatement(query2)){
			
			pu.execute();
			pu2.execute();
			
			c.commit();
			
		} catch (SQLException ex) {
			c.rollback();
			System.err.format("Error SQL: %s %s\n", ex.getMessage(), ex.getSQLState());
		} finally {
			c.cerrarConexion();
			
			//Hay que desconectar y conectar de nuevo para que se actualice las monedas y cantidades.
			cajero.cerrarConexion();
			cajero.conectarBBDD();
			System.out.println("Fondos transferidos correctamente de " + bancoOrigen + " a " + bancoDestino);
		}
	}
	
	public static void reset() {
		cajero.cerrarConexion();
		conectarBaseDatos();
		
	}

	public static void main(String[] args) {
		iniValores();
		conectarBaseDatos();
		menu();
		sc.close();
	}

	public static void menu() {
		int op;
		String opciones[] = { "1.Listar", "2.Realizar compra", "3.Introducir dinero", "4.Transferir fondos",
				"5.Cambiar BBDD", "6.Salir" };

		do {
			System.out.println();
			op = Biblioteca.menu(sc, opciones);
			switch (op) {
			case 1:
				listar();
				break;
			case 2:
				compra();
				break;
			case 3:
				introDinero();
				break;
			case 4:
				transferirFondos();
				break;
			case 5:
				reset();
				break;
			case 6:
				break;
			}
			
			if (op != 6) {
				System.out.print("\nPresione una tecla para continuar...");
				sc.nextLine();
			}

		} while (op != 6);
	}
}