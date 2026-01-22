package com.cheng.service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cheng.model.Empleado;

@Service
public class EmpleadoServiceImp implements IEmpleadoService {
	
	private List<Empleado> lista = null;
	
	

	public EmpleadoServiceImp() {
		lista = new LinkedList<>();
		Empleado emp1 = new Empleado(7369,"SMITH","CLERK",7902,7332.6,500,30,LocalDate.parse("2044-04-02"),"emp_01.jpg");
		Empleado emp2 = new Empleado(7499,"ALLEN","SALESMAN",7698,1936.0,300,20,LocalDate.parse("2044-08-10"),"emp_02.jpg");
		Empleado emp3 = new Empleado(7521,"WARD","SALESMAN",7698,1512.5,500,10,LocalDate.parse("2044-09-01"),"emp_03.jpg");
		lista.add(emp1);
		lista.add(emp2);
		lista.add(emp3);
	}

	@Override
	public void guardar(Empleado emp) {
		lista.add(emp);
	}

	@Override
	public List<Empleado> buscarTodos() {
		return lista;
	}

	@Override
	public Empleado buscarPorId(Integer idEmpleado) {
		for(Empleado e : lista) {
			if(e.getEmpno().equals(idEmpleado)) {
				return e;
			}
		}
		return null;
	}

}
