package ejercicio3;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionAlmacen {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Articulo> art = new ArrayList<>();

	public static void main(String[] args) {
		//Articulos previamente definidos
		artPred();
		
		menu();
	}

	public static void menu() {
		int opc = -1;
		while (opc != 7) {
			System.out.println("Bienvenido a GESTISIMAL");
			System.out.println("Introduzca la que quieres realizar");
			System.out.println("1.Listado\n2.Alta\n3.Baja\n4.Modificación\n5.Entrada de mercancía\n6.Salida de mercancía\n7.Salir");
			opc = Integer.valueOf(sc.nextLine());
			switch (opc) {
			case 1:
				listado();
				break;
			case 2:
				alta();
				break;
			case 3:
				baja();
				break;
			case 4:
				modificar();
				break;
			case 5:
				opMercancia("entrado", true);
				break;
			case 6:
				opMercancia("salido", false);
				break;
			}
			if (opc != 7) {
				System.out.println("Presione enter para continuar...");
				sc.nextLine();
			} else {
				System.out.println("Gracias por utilizar el sistema");
			}

		}

	}

	public static void artPred() {
		art.add(new Articulo("Laptop HP 15.6\"", 450.00, 699.99, 10));
		art.add(new Articulo("Mouse Inalámbrico Logitech", 15.50, 29.99, 25));
		art.add(new Articulo("Teclado Mecánico RGB", 35.00, 79.99, 15));
		art.add(new Articulo("Monitor 24\" Samsung", 120.00, 199.99, 8));
		art.add(new Articulo("Auriculares Bluetooth Sony", 45.00, 89.99, 12));
	}

	public static void listado() {
		for (Articulo ar : art) {
			System.out.println(ar);
		}
	}

	public static void alta() {
		System.out.println("Introduzca la descripcion");
		String desc = sc.nextLine();
		double precioCompra = introducirPrecio("compra");
		double precioVenta = introducirPrecio("venta");
		int stock = (int) (introducirPrecio("stock"));
		art.add(new Articulo(desc, precioCompra, precioVenta, stock));
		System.out.println("La alta ha sido exitosa");
	}

	public static void baja() {
		System.out.println("Introduzca el codigo del producto a retirar");
		int num = Integer.valueOf(sc.nextLine());
		Articulo artRetirar = encontrado(num);
		if (artRetirar == null)
			System.out.println("No se ha encontrado el articulo");

		else {
			art.remove(artRetirar);
			System.out.println("Artículo retirado");
		}

	}

	public static Articulo encontrado(int id) {
		for (Articulo ar : art) {
			if (id == ar.getCodigo()) {
				return ar;
			}
		}
		return null;
	}

	public static double introducirPrecio(String mensaje) {
		while (true) {
			System.out.println("Introduzca precio de " + mensaje);
			try {
				double precio = Double.parseDouble(sc.nextLine());
				if (precio >= 0) {
					return precio;
				} else {
					System.out.println("Error: El precio no puede ser negativo.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Debe introducir un número válido.");
			}
		}
	}

	public static void modificar() {
		System.out.println("ID del producto a modificar");
		try {
			int id = Integer.valueOf(sc.nextLine());
			Articulo artMod = encontrado(id);
			if (artMod == null) {
				System.out.println("Articulo no encontrado");
				return;
			}
			System.out.println("¿Que quieres modificar de " + artMod + "?");
			System.out.println("1.Descripcion\n2.Precio de compra\n3.Precio de venta\n4.Salir");
			int opc = Integer.valueOf(sc.nextLine());
			boolean exitoso = false;
			switch (opc) {
			case 1:
				System.out.println("Introduzca nueva descripcion");
				String descripcion = sc.nextLine();
				artMod.setDescripcion(descripcion);
				exitoso = true;
				break;
			case 2:
				artMod.setPrecioCompra(introducirPrecio("compra"));
				exitoso = true;
				break;
			case 3:
				artMod.setPrecioVenta(introducirPrecio("venta"));
				exitoso = true;
				break;
			}
			if(exitoso) System.out.println("La operación ha sido exitosa");
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	public static void opMercancia(String mensaje, boolean positivo) {
		System.out.println("Introduzca el id del producto");
		Articulo ar = encontrado(Integer.valueOf(sc.nextLine()));
		if (ar == null) {
			System.out.println("Producto no encontrado");
			return;
		}
		
		System.out.println("¿Cuántas unidades ha " + mensaje +" al almacén?");
		int cantidad = Integer.valueOf(sc.nextLine());
		if(positivo) ar.setStock(ar.getStock() + cantidad);
		else {
			while(ar.getStock() < cantidad) {
				System.out.println("Cantidad inválida. En el almacen hay " + ar.getStock());
				System.out.println("Introduzca unidades a retirar");
				cantidad = Integer.valueOf(sc.nextLine());
			}
			ar.setStock(ar.getStock() - cantidad);
		}
		System.out.println("La nueva cantidad es de " + ar.getStock());
	}

}
