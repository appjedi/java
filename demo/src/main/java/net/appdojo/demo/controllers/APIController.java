package net.appdojo.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod;
import net.appdojo.demo.models.User;

@RestController
public class APIController {
	
	// One syntax to implement a
	// GET method
	@GetMapping("/")
	public String home()
	{
		String str
			= "<html><body><font color=\"green\">"
			+ "<h1>WELCOME To GeeksForGeeks</h1>"
			+ "</font></body></html>";
		return str;
	}
	@GetMapping("/user")
	public User getUser()
	{
		User user = new User (1,"testerb","Test1234","testerb@test.com",2,1);
		return user;
	}
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id)
	{
		User user = new User (id,"testerb","Test1234","testerb@test.com",2,1);
		return user;
	}
	@GetMapping("/users")
	public List<User> getUsers()
	{
		User user = new User (1,"testerb","Test1234","testerb@test.com",2,1);
		List<User>users=new ArrayList<User>();
		users.add(user);
		user = new User (2,"tester2","Test1234","tester2@test.com",2,1);
		users.add(user);
		return users;
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
