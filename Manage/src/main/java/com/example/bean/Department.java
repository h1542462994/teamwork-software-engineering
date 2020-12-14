package main.java.com.example.bean;
//这个是1.4的任务，但是是我朱佰亿做（我是1.5任务，需要用到）的，因为没什么事情所以想做一做，但是好不好不太清楚，因为我的Visio坏了。
import org.hibernate.mapping.PrimaryKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class Department {
	private String dname;//部门名字
	private String did;//部门id号
	private List<String> lowerdepartment;//下级部门集合
	private String upperdepartment;//上级部门
	private Integer dchargeperonid;//主管理员
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public List<String> getLowerdepartment() {
		return lowerdepartment;
	}
	public void setLowerdepartment(List<String> lowerdepartment) {
		this.lowerdepartment = lowerdepartment;
	}
	public String getUpperdepartment() {
		return upperdepartment;
	}
	public void setUpperdepartment(String upperdepartment) {
		this.upperdepartment = upperdepartment;
	}
	public Integer getDchargeperonid() {
		return dchargeperonid;
	}
	public void setDchargeperonid(Integer dchargeperonid) {
		this.dchargeperonid = dchargeperonid;
	}
	@Override
	public String toString() {
		return "Department [dname=" + dname + ", did=" + did + ", lowerdepartment=" + lowerdepartment
				+ ", upperdepartment=" + upperdepartment + ", dchargeperonid=" + dchargeperonid + "]";
	}
	public Department(String dname, String did, List<String> lowerdepartment, String upperdepartment,
			Integer dchargeperonid) {
		super();
		this.dname = dname;
		this.did = did;
		this.lowerdepartment = lowerdepartment;
		this.upperdepartment = upperdepartment;
		this.dchargeperonid = dchargeperonid;
	}
	public Department() {
		super();
	}
	
}
