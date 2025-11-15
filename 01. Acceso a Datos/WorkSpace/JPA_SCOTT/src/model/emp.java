package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="EMP.todos", query ="SELECT e FROM emp e")
public class emp implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	int empno;
	String ename;
	String job;
	Integer mgr;
	double sal;
	Float comm;
	int deptno;
	Date hireDate;
	
	
	public emp() {
		super();
	}


	public emp(int empno, String ename, String job, int mgr, double sal, float comm, int deptno, Date hireDate) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
		this.hireDate = hireDate;
	}


	public int getEmpno() {
		return empno;
	}


	public void setEmpno(int empno) {
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


	public int getMgr() {
		return mgr;
	}


	public void setMgr(int mgr) {
		this.mgr = mgr;
	}


	public double getSal() {
		return sal;
	}


	public void setSal(double sal) {
		this.sal = sal;
	}


	public float getComm() {
		return comm;
	}


	public void setComm(float comm) {
		this.comm = comm;
	}


	public int getDeptno() {
		return deptno;
	}


	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}


	public Date getHireDate() {
		return hireDate;
	}


	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}


	@Override
	public String toString() {
		return "emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", sal=" + sal + ", comm="
				+ comm + ", deptno=" + deptno + ", hireDate=" + hireDate + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(comm, deptno, empno, ename, hireDate, job, mgr, sal);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		emp other = (emp) obj;
		return Float.floatToIntBits(comm) == Float.floatToIntBits(other.comm) && deptno == other.deptno
				&& empno == other.empno && Objects.equals(ename, other.ename)
				&& Objects.equals(hireDate, other.hireDate) && Objects.equals(job, other.job) && mgr == other.mgr
				&& Double.doubleToLongBits(sal) == Double.doubleToLongBits(other.sal);
	}
	
	
}
