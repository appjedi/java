package net.appdojo.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import net.appdojo.demo.dao.UserDAO;
import net.appdojo.demo.models.User;

@Controller
public class MainController {
	UserDAO userDAO = new UserDAO();
	@PostMapping("/auth")
	@CrossOrigin()
	public User auth(@RequestBody User user) {
		try {
			System.out.printf("1. post auth: \n%s\n", user);
			User authUser = userDAO.auth(user.getUsername(), user._pw());
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
}
