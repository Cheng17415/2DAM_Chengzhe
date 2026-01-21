package com.cheng.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cheng.demo.model.Vacante;

@Service
public class VacanteServiceImplementacion implements IVacantesService {
	
	private List<Vacante> lista = null;
	
	public VacanteServiceImplementacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacante>();
		try {
			// Creamos la oferta de Trabajo 1
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("Solicitamos Ing. Civil para");
			vacante1.setFecha(sdf.parse("08-02-2019"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");

			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Publico");
			vacante2.setDescripcion("Empresa importante solicita");
			vacante2.setFecha(sdf.parse("11-06-2012"));
			vacante2.setSalario(7000.0);
			vacante2.setDestacado(1);
			vacante2.setImagen("empresa2.png");

			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Guardia Civil");
			vacante3.setDescripcion("Solicitamos Guardia Civil para");
			vacante3.setFecha(sdf.parse("08-02-1999"));
			vacante3.setSalario(8500.0);
			vacante3.setDestacado(0);
			vacante3.setImagen("empresa3.png");

			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Grafico");
			vacante4.setDescripcion("Solicitamos Guardia Civil para");
			vacante4.setFecha(sdf.parse("08-02-2019"));
			vacante4.setSalario(8500.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("logo.png");

			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
		} catch (ParseException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	@Override
	public List<Vacante> buscarTodas() {
		return lista;
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for(Vacante v:lista) {
			if(idVacante.equals(v.getId())) return v;
		}
		return null;
	}

	@Override
	public List<Vacante> buscarPorYear(Integer year) {
		List<Vacante> listaPorYear = new LinkedList<>();
		for(Vacante v : lista) {
			if(v.getFecha().getYear() + 1900 == year) {
				listaPorYear.add(v);
				
			}
		}
		return listaPorYear;
	}

	@Override
	public Vacante eliminarVacante(Integer idVacante) {
		Vacante v = buscarPorId(idVacante);
		if(v != null) {
			lista.remove(v);
		}
		return v;
	}

}
