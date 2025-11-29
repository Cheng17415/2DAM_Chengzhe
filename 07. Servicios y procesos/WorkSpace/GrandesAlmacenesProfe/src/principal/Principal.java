package principal;

import clases.Almacen;
import clases.Cliente;
import clases.Puerta;

public class Principal {
	public static void main(String[] args) {
		/*Hay que simular unos almacenes, en donde hay 300 clientes y 100 productos.
		 * En la puerta solo cabe 1 cliente a la vez. Van a intentar entrar 10 veces
		 * por la puerta, si no lo consigue se va.*/
		final int N_CLIENTES = 300;
		final int N_PRODUCTOS = 100;
		
		Cliente[] clientes = new Cliente[N_CLIENTES];
		Almacen almacen = new Almacen(N_PRODUCTOS);
		Puerta puerta = new Puerta();
		
		for(int i = 0; i< N_CLIENTES; i++) {
			clientes[i] = new Cliente(puerta, almacen, "Cliente " + i);
			clientes[i].start();
		}
	}
}
