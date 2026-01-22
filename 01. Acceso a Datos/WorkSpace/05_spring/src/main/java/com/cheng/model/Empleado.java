package com.cheng.model;

import java.time.LocalDate;

public class Empleado {
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Double sal;
	private Integer comm;
	private Integer deptno;
	private LocalDate hiredate;
	private String img = "no-image.png";
	
	public Empleado() {
		super();
	}
	
	public Empleado(int empno, String ename, String job, Integer mgr, double d, Integer comm, Integer deptno,
			LocalDate hiredate, String img) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.sal = d;
		this.comm = comm;
		this.deptno = deptno;
		this.hiredate = hiredate;
		this.img = img;
	}

	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getMgr() {
		return mgr;
	}
	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	public Integer getComm() {
		return comm;
	}
	public void setComm(Integer comm) {
		this.comm = comm;
	}
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public LocalDate getHiredate() {
		return hiredate;
	}
	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Empleado [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", sal=" + sal
				+ ", comm=" + comm + ", deptno=" + deptno + ", hiredate=" + hiredate + ", img=" + img + "]";
	}
	
	
}
