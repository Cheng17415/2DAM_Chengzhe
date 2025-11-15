package clases;

import java.time.LocalDateTime;

public class Empleado {
	private int id;
	private String nombre;
	private double salario;
	private LocalDateTime fecha;
	
	public Empleado(int id, String nombre, double salario, LocalDateTime fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.salario = salario;
		this.fecha = fecha;
	}
	public Empleado(String nombre, double salario, LocalDateTime fecha) {
		super();
		this.nombre = nombre;
		this.salario = salario;
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", salario=" + salario + ", fecha=" + fecha + "]";
	}
	
	
	
	
}
