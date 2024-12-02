package net.appdojo.demo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.appdojo.demo.models.User;

public class UserDAO extends Database{
	public static void main (String[]args)
	{
		UserDAO dao = new UserDAO();
		User user = dao.auth("bob","Test1234");
		System.out.println(user);
	}
	public User getUser(int id)
	{
		Database db = new Database();
		try {
			ResultSet rs = db.getResultSet("SELECT * FROM users where id="+id);
        	
        	if (rs==null||!rs.next())
        	{
        		System.err.println ("Query failed");
        		return null;
        	}
        	User user = new User();
        	
        	user.setUserId(id);
        	user.setEmail(rs.getString("email"));
        	user.setUsername(rs.getString("username"));
        	user.setFullName(rs.getString("full_name"));
        	user.setStatus(rs.getInt("status"));
        	user.setRoleId(rs.getInt("role_id"));
        	return user;
		}catch (Exception ex)
		{
			return null;
		}
	}
	public User auth(String un, String pw)
	{
		Database db = new Database();
		try {
			ResultSet rs = db.query("SELECT * FROM users where username=? AND password=?",un,pw);
        	
        	if (rs==null||!rs.next())
        	{
        		System.err.println ("Query failed");
        		return null;
        	}
        	User user = new User();
        	
        	user.setUserId(rs.getInt("id"));
        	user.setEmail(rs.getString("email"));
        	user.setUsername(rs.getString("username"));
        	user.setFullName(rs.getString("full_name"));
        	user.setStatus(rs.getInt("status"));
        	user.setRoleId(rs.getInt("role_id"));
        	return user;
		}catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}finally {
			db.close();
		}
	}
	public List<User> getUsers()
	{
		Database db = new Database();
		try {
			ResultSet rs = db.getResultSet("SELECT * FROM users");
        	
        	if (rs==null||!rs.next())
        	{
        		System.err.println ("Query failed");
        		return null;
        	}
        	List<User>users=new ArrayList<User>();
        	do {
	        	User user = new User();
	        	
	        	user.setUserId(rs.getInt("id"));
	        	user.setEmail(rs.getString("email"));
	        	user.setUsername(rs.getString("username"));
	        	user.setFullName(rs.getString("full_name"));
	        	user.setStatus(rs.getInt("status"));
	        	user.setRoleId(rs.getInt("role_id"));
	        	users.add(user);
        	}while (rs.next());
        	return users;
		}catch (Exception ex)
		{
			return null;
		}
	}
	public User save (User user)
	{
		Database db = new Database();
		try {
			String sp="call usp_user_save";
			
			return user;
		}catch (Exception ex)
		{
			return null;
		}
		
	}
}
