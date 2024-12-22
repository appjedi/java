package net.appdojo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.appdojo.demo.models.User;
import net.appdojo.demo.services.MainService;

@RestController
public class APIController {
	@Autowired
	MainService service;

	@PostMapping("/api/auth")
	@CrossOrigin()
	public User auth(@RequestBody User user) {
		try {
			System.out.printf("1. post auth: \n%s\n", user);
			User authUser = service.auth(user.getUsername(), user._pw());
			System.out.printf("2. post auth: \n%s\n", authUser);
			if (authUser == null) {
				user.setStatus(0);
				user.setUserId(0);
				user.setRoleId(0);
				user.setFullName("Auth Failed!");
				return user;
			}
			return authUser;
		} catch (Exception ex) {
			System.err.println("post auth error:" + ex);
			user.setStatus(-1);
			user.setUserId(0);
			user.setRoleId(0);
			user.setFullName("error:" + ex.getMessage());
			return user;
		}
	}

	@PostMapping("/user")
	@CrossOrigin()
	public User postUser(@RequestBody User user) {
		try {
			service.save(user);
			return user;
		} catch (Exception ex) {
			System.err.println("post auth error:" + ex);
			user.setStatus(-1);
			user.setUserId(0);
			user.setRoleId(0);
			user.setFullName("error:" + ex.getMessage());
			return user;
		}
	}

	@GetMapping("/user")
	public User getUser() {
		User user = new User(1, "testerb", "Test1234", "testerb@test.com", "Bob Tester", 2, 1);
		return user;
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
		User user = new User();

		try {
			user = service.getUser(id);
			System.out.println(user);
			return user;
		} catch (Exception ex) {
			return user;
		}
	}

	@GetMapping("/api/users")
	// @CrossOrigin(origins = "http://127.0.0.1:5500")
	@CrossOrigin()
	public List<User> getUsers() {

		try {
			List<User> users = service.getUsers();
			return users;
		} catch (Exception ex) {
			return null;
		}

	}

	// Another syntax to implement a
	// GET method
	@GetMapping("/info")
	public String info() {
		String str2 = "<html><body><font color=\"green\">" + "<h2>GeeksForGeeks is a Computer"
				+ " Science portal for Geeks. " + "This portal has been " + "created to provide well written, "
				+ "well thought and well explained " + "solutions for selected questions."
				+ "</h2></font></body></html>";
		return str2;
	}
}
