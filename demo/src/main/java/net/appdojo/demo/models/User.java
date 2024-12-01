package net.appdojo.demo.models;

public class User {
	int userId;
	private String username;
	private String password;
	private String email;
	private String fullName;
	

	private int roleId;
	private int status;
	
	public User () {
		
	}
	public User(int userId,String username, String password, String email, String fullName,int roleId, int status) {
		super();
		this.userId=userId;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", fullName=" + fullName + ", roleId=" + roleId + ", status=" + status + "]";
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return "*********";
	}
	public String _pw() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
