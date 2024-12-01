package net.appdojo.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod;
import net.appdojo.demo.dao.UserDAO;
import net.appdojo.demo.models.User;

@RestController
public class APIController {
	UserDAO userDAO=new UserDAO();
	// One syntax to implement a
	// GET method
	@GetMapping("/")
	public String home()
	{
		String str
			= "<html><body><font color=\"green\">"
			+ "<h1>Login:</h1>"
			+"<form action='user' method='post'>"
			+"<div><input type='text' name='username' placeholder='username'/></div>"
			+"<div><input type='password' name='password' placeholder='password'/></div>"
			+"<div><button>Login</button></form>"
			+ "</font></body></html>";
		return str;
	}
	@GetMapping("/login")
	public String loginForm()
	{
		String str
			= "<html><body><font color=\"green\">"
			+ "<h1>Login:</h1>"
			+"<form action='/auth' method='post'>"
			+"<div><input type='text' name='username' placeholder='username'/></div>"
			+"<div><input type='password' name='password' placeholder='password'/></div>"
			+"<div><input type='submit' value='Login'/></div></form>"
			+ "</font></body></html>";
		return str;
	}
	@PostMapping("/auth")
	@CrossOrigin(origins = "http://localhost:9000")
	public String auth(@RequestBody User user)
	{
		try {
			System.out.printf("post auth: \n%s\n",user);
			return user.toString();
		}catch (Exception ex)
		{
			System.err.println ("post auth error:"+ex);
			return "post auth error";
		}
	}
	@GetMapping("/user")
	public User getUser()
	{
		User user = new User (1,"testerb","Test1234","testerb@test.com","Bob Tester",2,1);
		return user;
	}
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id)
	{
		User user=new User();
		
		try {
			user = userDAO.getUser(id);
			System.out.println(user);
			return user;
		}catch (Exception ex)
		{
			return user;
		}
	}
	@GetMapping("/users")
	public List<User> getUsers()
	{
		
		try {
			List<User>users = userDAO.getUsers();
			return users;
		}catch (Exception ex)
		{
			return null;
		}

	}
	// Another syntax to implement a
	// GET method
	@GetMapping("/info")
	public String info()
	{
		String str2
			= "<html><body><font color=\"green\">"
			+ "<h2>GeeksForGeeks is a Computer"
			+ " Science portal for Geeks. "
			+ "This portal has been "
			+ "created to provide well written, "
			+ "well thought and well explained "
			+ "solutions for selected questions."
			+ "</h2></font></body></html>";
		return str2;
	}
}
