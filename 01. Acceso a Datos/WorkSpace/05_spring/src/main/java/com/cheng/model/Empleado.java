package com.cheng.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "EMP")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="empno")
	private Integer empno;

	private Float comm;

	private String ename;

	private LocalDate hiredate;

	private String job;

	private Integer mgr;

	private Float sal;
	
	//bi-directional many-to-one association to Dept
	@ManyToOne
	@JoinColumn(name="deptno")
	private Departamento dept;

	public Empleado() {
	}

	public Empleado(int empno, String ename, String job, Integer mgr, Float sal, Float comm, LocalDate hiredate){
		super();
		this.empno = empno;
		this.comm = comm;
		this.ename = ename;
		this.hiredate = hiredate;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
	}

	public Integer getEmpno() {
		return this.empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public Float getComm() {
		return this.comm;
	}

	public void setComm(Float comm) {
		this.comm = comm;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public LocalDate getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return this.mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Float getSal() {
		return this.sal;
	}

	public void setSal(Float sal) {
		this.sal = sal;
	}

	public Departamento getDepartamento() {
	    return dept;
	}

	public void setDepartamento(Departamento departamento) {
	    this.dept = departamento;
	}


	@Override
	public String toString() {
		return "empno=" + empno + ", comm=" + comm + ", ename=" + ename + ", hiredate=" + hiredate + ", job=" + job
				+ ", mgr=" + mgr + ", sal=" + sal;
	}

}