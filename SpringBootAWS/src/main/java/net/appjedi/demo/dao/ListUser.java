package net.appjedi.demo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.appjedi.demo.models.User;

public class ListUser extends ListBase {
	List<User>list;
	
	@Override
	public int process (ResultSet rs)
	{
		try {
			list=new ArrayList<User>();
			while (rs.next())
			{
				User user = new User();
				user.setUserId(rs.getInt("id"));
	        	user.setEmail(rs.getString("email"));
	        	user.setUsername(rs.getString("username"));
	        	user.setFullName(rs.getString("full_name"));
	        	user.setStatus(rs.getInt("status"));
	        	user.setRoleId(rs.getInt("role_id"));
	        	list.add(user);
			}
			return list.size();
		}catch (Exception ex)
		{
			ex.printStackTrace();
			return -1;
		}
	}
	public List<User>getList(){return this.list;}
	public int size() {return list==null?0:list.size();}
}
