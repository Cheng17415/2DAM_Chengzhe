package clases;

import java.time.LocalDate;

public class Jugador {
	private String club;
	private LocalDate fechaCumple;
	private String lineaOriginal;
	
	public Jugador(String club, LocalDate fechaCumple, String lineaOriginal) {
		super();
		this.club = club;
		this.fechaCumple = fechaCumple;
		this.lineaOriginal = lineaOriginal;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public LocalDate getFechaCumple() {
		return fechaCumple;
	}
	public void setFechaCumple(LocalDate fechaCumple) {
		this.fechaCumple = fechaCumple;
	}
	
	public String getLineaOriginal() {
		return lineaOriginal;
	}
	public void setLineaOriginal(String lineaOriginal) {
		this.lineaOriginal = lineaOriginal;
	}
	@Override
	public String toString() {
		return "Jugador [club=" + club + ", fechaCumple=" + fechaCumple + "]";
	}
	
}
