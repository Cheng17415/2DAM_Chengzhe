package clases;

import java.time.LocalDate;

public class Jugador {
	private String club;
	private LocalDate fechaCumple;
	
	public Jugador(String club, LocalDate fechaCumple) {
		super();
		this.club = club;
		this.fechaCumple = fechaCumple;
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
	@Override
	public String toString() {
		return "Jugador [club=" + club + ", fechaCumple=" + fechaCumple + "]";
	}
	
}
