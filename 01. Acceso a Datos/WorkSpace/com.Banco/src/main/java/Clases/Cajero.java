package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Cajero {
	
	String banco;
	TreeMap<Double, Integer> mapaDinero;
	Connection conexion;

	// CONSTRUCTOR
	public Cajero(String banco) {
		super();
		this.banco = banco;
		this.mapaDinero = new TreeMap<>(Collections.reverseOrder());
	}
	
	// GETTER Y SETTER
	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public TreeMap<Double, Integer> getMapaDinero() {
		return mapaDinero;
	}

	public void setMapaDinero(TreeMap<Double, Integer> mapaDinero) {
		this.mapaDinero = mapaDinero;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	// OTROS METODOS
	public void conectarBBDD() {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + banco.toLowerCase(), "root",
					"root");
			conexion.setAutoCommit(false);

		} catch (SQLException ex) {
			System.err.println("No se pudo conectar a la base de datos" + ex.getMessage());
		}
	}

	public void leerCajero() {
		//Borrar todo antes de leer.
		mapaDinero.clear();
		
		String SQL_QUERY = "SELECT * FROM CAJERO ORDER BY MONEDA DESC";

		try (PreparedStatement ps = conexion.prepareStatement(SQL_QUERY)) {

			ResultSet resultados = ps.executeQuery();
			while (resultados.next())
				//Double -> Moneda	Int -> Cantidad
				mapaDinero.put(resultados.getDouble(1), resultados.getInt(2));

		} catch (SQLException ex) {
			System.err.format("%s %s\n", ex.getMessage(), ex.getSQLState());
		}
	}

	public boolean realizarCompra(double importeCompra, Map<Double, Integer> pago) {
		leerCajero();
		double totalPagado = 0;

		//Primero obtener el pago total y
		//comprobar si la cantidad que he recibido no sea menor al importe de la compra.

		for (Map.Entry<Double, Integer> entry : pago.entrySet()) {
			totalPagado += entry.getValue() * entry.getKey();
		}

		if (totalPagado < importeCompra) {
			System.out.printf("No se pudo realizar la compra. %.2f€ de %.2f€\n", totalPagado, importeCompra);
			return false;
		}
		
		//Intenta meter el dinero dentro del cajero sin hacer commit.
		if (!actualizarCajero(pago, false)) {
			System.err.println("Error al registrar el pago en el cajero.");
			return false;
		}
		
		//Intenta devolver el cambio correspondiente
		if (!devolverCambio(totalPagado - importeCompra)) {
			rollback();
			System.out.println("No se pudo realizar la compra ya que no disponemos de cambio.");
			return false;
		}

		commit();
		return true;

	}

	public boolean devolverCambio(double cambio) {

		System.out.printf("Dinero a devolver %.2f€\n", cambio);
		if (cambio < 0.01) return true;

		//Trabajar en céntimos para que no haya problemas de redondeo.
		cambio = Math.round(cambio * 100);
		
		//Ordenar el TreeMap de más valor a menos
		TreeMap<Double, Integer> usado = new TreeMap<>(Collections.reverseOrder());

		String SQL_UPDATE = "UPDATE CAJERO SET CANTIDAD = CANTIDAD + ? WHERE MONEDA = ?";
		try (PreparedStatement pu = conexion.prepareStatement(SQL_UPDATE)) {

			for (Map.Entry<Double, Integer> entry : mapaDinero.entrySet()) {

				double moneda = entry.getKey() * 100;
				int cantidad = entry.getValue();

				// Si el valor de la moneda es mayor que el cambio a dar 
				// o no queda de esa moneda, pasar a la siguiente moneda.
				if (cantidad <= 0 || moneda > cambio) continue;

				//Calcular la cantidad que se necesita
				int usar = (int) (cambio / moneda);
				if (usar > cantidad)
					usar = cantidad;

				//Actualizar el cambio a dar.
				cambio -= moneda * usar;
				usado.put(moneda / 100, usar);

				pu.setInt(1, -usar);
				pu.setDouble(2, moneda / 100);
				pu.addBatch();
				
				//Como estamos trabajando en centimos, seria menor que un centimo
				if (cambio < 1)
					break;
			}

			if (cambio < 1) {
				pu.executeBatch();
				System.out.println("Cambio entregado:");
				for (Map.Entry<Double, Integer> temp : usado.entrySet()) {
					System.out.printf("%d x %.2f\n", temp.getValue(), temp.getKey());
				}
				return true;
			}

			System.out.println("No hay suficiente cambio disponible.");
			return false;

		} catch (SQLException ex) {
			System.err.format("Error SQL: %s", ex.getMessage());
		}

		return false;
	}

	public boolean introducirDinero(Map<Double, Integer> pago) {
		leerCajero();
		return actualizarCajero(pago, true);
	}

	public boolean actualizarCajero(Map<Double, Integer> pago, boolean autocommit) {
		String SQL_UPDATE = "UPDATE CAJERO SET CANTIDAD = CANTIDAD + ? WHERE MONEDA = ?";
		try (PreparedStatement pu = conexion.prepareStatement(SQL_UPDATE)) {

			for (Map.Entry<Double, Integer> entry : pago.entrySet()) {
				pu.setInt(1, entry.getValue());
				pu.setDouble(2, entry.getKey());
				pu.addBatch();
			}
			pu.executeBatch();
			if (autocommit) commit();

			return true;

		} catch (SQLException ex) {
			System.err.format("%s %s\n", ex.getMessage(), ex.getSQLState());
		}
		return false;

	}

	// METODOS DE APOYO
	public void rollback() {
		try {
			conexion.rollback();
		} catch (SQLException ex) {
			System.err.format("%s %s\n", ex.getMessage(), ex.getSQLState());
		}
	}

	public void commit() {
		try {
			conexion.commit();
		} catch (SQLException ex) {
			System.err.format("%s %s\n", ex.getMessage(), ex.getSQLState());
		}
	}

	public void cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException ex) {
			System.err.format("Error SQL: %s %s\n", ex.getMessage(), ex.getSQLState());
		}
	}

}
