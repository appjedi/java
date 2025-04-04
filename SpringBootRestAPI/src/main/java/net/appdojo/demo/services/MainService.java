package net.appdojo.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.appdojo.demo.dao.UserDAO;
import net.appdojo.demo.models.User;

@Service
public class MainService {
	@Autowired
	UserDAO userDAO;

	public User getUser(int id)
	{
		return userDAO.getUser(id);
	}
	public User auth(String un, String pw)
	{
		return userDAO.auth(un,pw);
	}
	public List<User> getUsers()
	{
		return userDAO.getUsers();
	}
	public User save (User user)
	{
		return userDAO.save(user);
	}
}
