package principal;

import clases.Almacen;
import clases.Cliente;
import clases.Puerta;

public class Principal {
	public static void main(String[] args) {
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
