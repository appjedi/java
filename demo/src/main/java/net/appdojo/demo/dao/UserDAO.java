package net.appdojo.demo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.appdojo.demo.models.User;

public class UserDAO extends Database{
	public User getUser(int id)
	{
		Database db = new Database();
		try {
			ResultSet rs = db.getResultSet("SELECT * FROM users");
        	
        	if (rs==null||!rs.next())
        	{
        		System.err.println ("Query failed");
        		return null;
        	}
        	User user = new User();
        	
        	user.setUserId(id);
        	user.setEmail(rs.getString("email"));
        	user.setUsername(rs.getString("username"));
        	user.setStatus(rs.getInt("status"));
        	user.setRoleId(rs.getInt("role_id"));
        	return user;
		}catch (Exception ex)
		{
			return null;
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
	        	
	        	user.setUserId(rs.getInt("user_id"));
	        	user.setEmail(rs.getString("email"));
	        	user.setUsername(rs.getString("username"));
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
}