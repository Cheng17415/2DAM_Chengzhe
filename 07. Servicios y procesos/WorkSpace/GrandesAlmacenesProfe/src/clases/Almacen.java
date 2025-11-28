package clases;

public class Almacen {
	private int nProductos;

	public Almacen(int nProductos) {
		super();
		this.nProductos = nProductos;
	}

	public int getnProductos() {
		return nProductos;
	}

	public void setnProductos(int nProductos) {
		this.nProductos = nProductos;
	}

	@Override
	public String toString() {
		return "Almacen [nProductos=" + nProductos + "]";
	}
	
	
	public synchronized boolean cogerProducto() {
		if(nProductos>0) {
			nProductos--;
			return true;
		}
		return false;
		
	}
}
