package com.cheng.demo.model;

import java.util.LinkedList;
import java.util.List;

public class Biblioteca {
	public static List<Departamento> getDeps(){
		List <Departamento> departamentos = new LinkedList<>();
		Departamento dep1 = new Departamento();
		dep1.setId(1);
		dep1.setNombre("RRHH");
		dep1.setLocalizacion("Madrid");
		dep1.setImagen("empresa1.png");
		departamentos.add(dep1);
		
		Departamento dep2 = new Departamento();
		dep2.setId(2);
		dep2.setNombre("Financiera");
		dep2.setLocalizacion("Barcelona");
		dep2.setImagen("empresa2.png");
		departamentos.add(dep2);
		
		Departamento dep3 = new Departamento();
		dep3.setId(3);
		dep3.setNombre("Marketing");
		dep3.setLocalizacion("Sevilla");
		dep3.setImagen("empresa3.png");
		departamentos.add(dep3);
		
		Departamento dep4 = new Departamento();
		dep4.setId(4);
		dep4.setNombre("Progamador");
		dep4.setLocalizacion("Madrid");
		dep4.setImagen("logo.png");
		departamentos.add(dep4);
		return departamentos;
	}
	
	public static int alea(int li, int ls) {
		return (int)(Math.random() *(ls - li + 1)) + li;
	}
}
