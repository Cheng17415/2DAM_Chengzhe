package com.rayosoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sp12Application {

	public static void main(String[] args) {
		SpringApplication.run(Sp12Application.class, args);
	}

	/*
	findBy								Inicio de consulta (selección)			findByName(String name)
	And									Condición “Y” (AND)						findByNameAndStatus(...)
	Or									Condición “O” (OR)						findByNameOrEmail(...)
	Is, Equals							Igualdad (opcional)						findByNameIs(String name)
	Between								Rango de valores						findByAgeBetween(int a, int b)
	LessThan, 	LessThanEqual			< y ≤ comparación						findByAgeLessThan(30)
	GreaterThan, GreaterThanEqual		> y ≥ comparación						findByAgeGreaterThan(18)
	Before, After						Fechas antes/después					findByDateAfter(LocalDate d)
	In, NotIn							Valores dentro/fuera de colección		findByIdIn(...)
	Containing, StartsWith, EndsWith	Patrones de texto (LIKE)				findByEmailContaining(...)
	IgnoreCase							Comparación sin distinguir mayúsculas	findByNameIgnoreCase(...)
	IsNull, IsNotNull					NULL / NOT NULL							findByAddressIsNull()
	OrderBy								Ordenación								findByStatusOrderByCreatedDesc()
	Top, First							Limitar resultados						findTop3ByOrderByDateDesc()*/
}
