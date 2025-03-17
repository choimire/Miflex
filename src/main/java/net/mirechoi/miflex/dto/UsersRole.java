package net.mirechoi.miflex.dto;


public class UsersRole {
	private int id;
	private int userid;
	private String userRole;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UsersRole [id=" + id + ", userid=" + userid + ", userRole=" + userRole + "]";
	}
	
}
