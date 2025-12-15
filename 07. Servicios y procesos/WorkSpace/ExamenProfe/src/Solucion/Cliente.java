package Solucion;

import java.util.List;

public class Cliente {
	private final String nombre;
	private final List<Integer> tiemposProducto;
	
	public Cliente(String nombre, List<Integer> tiemposProducto) {
		this.nombre = nombre;
		this.tiemposProducto = tiemposProducto;
	}
	public String getNombre() { return nombre;}
	public List<Integer> getTiemposProducto() {return tiemposProducto;}
	
}
