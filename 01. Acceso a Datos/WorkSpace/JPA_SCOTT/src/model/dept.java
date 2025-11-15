package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class dept implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	int deptno;
	String dname;
	String loc;
	
	
	public dept() {
		super();
	}

	public dept(int deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "dept [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(deptno, dname, loc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		dept other = (dept) obj;
		return deptno == other.deptno && Objects.equals(dname, other.dname) && Objects.equals(loc, other.loc);
	}
	
	
}
