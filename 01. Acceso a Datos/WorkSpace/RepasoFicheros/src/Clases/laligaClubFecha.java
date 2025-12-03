package Clases;

import java.time.LocalDate;

public class laligaClubFecha {
  private String club;
  private LocalDate fecha;
  private String linea;
  
  public laligaClubFecha(String club, LocalDate fecha, String linea) {
	super();
	this.club = club;
	this.fecha = fecha;
	this.linea = linea;
  }

  public String getClub() {
	return club;
  }

  public void setClub(String club) {
	this.club = club;
  }

  public LocalDate getFecha() {
	return fecha;
  }

  public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
  }

  public String getLinea() {
	return linea;
  }
  
}
