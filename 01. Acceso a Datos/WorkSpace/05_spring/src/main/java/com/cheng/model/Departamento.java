package com.cheng.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "DEPT")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="deptno")
	private Integer deptno;

	private String dname;

	private String loc;

	//bi-directional many-to-one association to Emp
	@OneToMany(mappedBy="dept")
	private List<Empleado> emps;

	public Departamento() {
	}


	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public List<Empleado> getEmps() {
		return this.emps;
	}

	public void setEmps(List<Empleado> emps) {
		this.emps = emps;
	}

	public Empleado addEmp(Empleado emp) {
		getEmps().add(emp);
		emp.setDept(this);

		return emp;
	}

	public Empleado removeEmp(Empleado emp) {
		getEmps().remove(emp);
		emp.setDept(null);

		return emp;
	}

	@Override
	public String toString() {
		String cadena =  "deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "\n";
		return cadena;
	}
	
}