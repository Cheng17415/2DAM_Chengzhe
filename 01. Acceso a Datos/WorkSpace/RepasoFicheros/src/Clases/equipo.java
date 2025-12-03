package Clases;

public class equipo {
  private String nombre;
  private int ptos;
  public equipo(String nombre) {
	super();
	this.nombre = nombre;
	this.ptos = 0;
  }
  public equipo(String nombre, int ptos) {
	super();
	this.nombre = nombre;
	this.ptos = ptos;
  }
  public String getNombre() {
	return nombre;
  }
  public int getPtos() {
	return ptos;
  }
  
  public void setNombre(String nombre) {
	this.nombre = nombre;
}
  public void setPtos(int ptos) {
	this.ptos = ptos;
  }
  @Override
  public String toString() {
	return "equipo [nombre=" + nombre + ", ptos=" + ptos + "]";
  }

  
}
