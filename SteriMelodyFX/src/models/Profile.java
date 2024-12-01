package models;

public class Profile {
	int id;
	String name;
	String goals;
	int highestRank;
	int gamesWon;
	String image;
	
	String username;
	String password;
	String phone;
	String email;
	int status;
	int roleId;
	
	public Profile() {}
	public Profile(int id,String name, String goals, int highestRank, int gamesWon, String image) {
		super();
		this.id=id;
		this.name = name;
		this.goals = goals;
		this.highestRank = highestRank;
		this.gamesWon = gamesWon;
		this.image=image;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getPassword() {
		return password;
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
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getHighestRank() {
		return highestRank;
	}
	public void setHighestRank(int highestRank) {
		this.highestRank = highestRank;
	}
	public int getGamesWon() {
		return gamesWon;
	}
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	@Override
	public String toString() {
		return "Profile [id=" + id + ", name=" + name + ", goals=" + goals + ", highestRank=" + highestRank
				+ ", gamesWon=" + gamesWon + ", image=" + image + ", username=" + username + ", email=" + email
				+ ", status=" + status + ", roleId=" + roleId + "]";
	}

}
