package Clases;

public class Cuenta {
	private int saldo;
	private String numCuenta;
	
	public Cuenta(String numCuenta, int saldo) {
		super();
		this.saldo = saldo;
		this.numCuenta = numCuenta;
	}
	
	public synchronized int getSaldo() {
		return this.saldo;
	}
	
	public synchronized void ingresar(int cantidad) {
		this.saldo += cantidad;
	}
	
	public synchronized void sacar(int cantidad) {
		this.saldo -= cantidad;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
}
