package com.cheng.model;

public class Departamento {
	private Integer deptno;
	private String dname;
	private String loc;
	private String img = "no-img.png";
	
	public Departamento(Integer deptno, String dname, String loc, String img) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
		this.img = img;
	}
	public Departamento() {
		super();
	}
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Departamento [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + ", img=" + img + "]";
	}
	
}
