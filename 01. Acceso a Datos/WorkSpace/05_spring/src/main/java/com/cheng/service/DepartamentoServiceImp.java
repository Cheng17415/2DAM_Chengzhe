package com.cheng.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cheng.model.Departamento;
@Service
public class DepartamentoServiceImp implements IDepartamentoService {
	
	List<Departamento> lista = null;
	
	public DepartamentoServiceImp() {
		lista = new LinkedList<>();
		Departamento dept1 = new Departamento(10,"VENTAS","MADRID","dept_01.jpg");
		Departamento dept2 = new Departamento(20,"PRODUCCION","BARCELONA","dept_02.jpg");
		Departamento dept3 = new Departamento(30,"CENTRAL","MADRID", "dept_03.jpg");
		lista.add(dept1);
		lista.add(dept2);
		lista.add(dept3);
	}

	@Override
	public void guardar(Departamento dept) {
		lista.add(dept);
	}

	@Override
	public List<Departamento> buscarTodos() {
		return lista;
	}

	@Override
	public Departamento buscarPorId(Integer idDept) {
		for(Departamento dept : lista) {
			if(dept.getDeptno().equals(idDept)) {
				return dept;
			}
		}
		return null;
	}

}
