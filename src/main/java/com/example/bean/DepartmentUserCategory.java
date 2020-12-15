package main.java.com.example.bean;
//朱佰亿写的，用于记录那些人参加了那些部门，相当于是数据库里m:n的用于帮助数据更加结构化的类
public class DepartmentUserCategory {
	private Integer id;//参加部门的人的id
	private String dId;//部门id号
	private String dIdentity;//在部门的身份，最开始的想法是有mainad,subad,normal三种，分别是著管理员，此管理员，普通用户
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getdIdentity() {
		return dIdentity;
	}
	public void setdIdentity(String dIdentity) {
		this.dIdentity = dIdentity;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDid() {
		return dId;
	}
	public void setDid(String did) {
		this.dId = did;
	}
	public DepartmentUserCategory(Integer id, String did) {
		super();
		this.id = id;
		this.dId = did;
	}
	@Override
	public String toString() {
		return "DepartmentUserCategory [id=" + id + ", did=" + dId + "]";
	}
	public DepartmentUserCategory() {
		super();
	}
	
}
