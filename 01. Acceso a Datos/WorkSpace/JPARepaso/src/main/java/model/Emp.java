package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


@Entity
@NamedQuery(name="Emp.findAll", query="SELECT e FROM Emp e")
public class Emp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int empno;

	private Float comm;

	private String ename;

	@Temporal(TemporalType.DATE)
	private Date hiredate;

	private String job;

	private Integer mgr;

	private float sal;

	//bi-directional many-to-one association to Dept
	@ManyToOne
	@JoinColumn(name="DEPTNO")
	private Dept dept;

	public Emp() {
	}

	public int getEmpno() {
		return this.empno;
	}

	public void setEmpno(int empno) {
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

	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
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

	public float getSal() {
		return this.sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	public static String cabezera() {
		return "empno:comm:ename:hiredate:job:mgr:sal";
	}
	
	@Override
	public String toString() {
		return empno + ":" + comm + ":" + ename + ":" + hiredate + ":" + job
				+ ":" + mgr + ":" + sal;
	}
}