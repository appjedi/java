package net.appdojo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import net.appdojo.demo.models.User;
import net.appdojo.demo.services.MainService;

@Controller
public class WebController {
	@Autowired
	MainService service;

	@GetMapping("/")
	public ModelAndView viewIndex(ModelAndView model) {
		model.setViewName("index");
		model.addObject("test", "Hello World");
		return model;
	}

	@GetMapping("/users")
	public ModelAndView viewUsers(ModelAndView model) {
		model.setViewName("users");
		model.addObject("userList", service.getUsers());
		model.addObject("test", "Hello World");
		return model;
	}

	@GetMapping("/login")
	public ModelAndView viewLogin(ModelAndView model) {
		model.addObject("errorMessage", "");

		model.setViewName("login");
		return model;
	}

	@PostMapping(path = "/auth", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView auth(User user, ModelAndView model) {
		try {
			System.out.printf("1. post auth: \n%s\n", user);
			User authUser = service.auth(user.getUsername(), user._pw());
			System.out.printf("2. post auth: \n%s\n", authUser);
			if (authUser == null) {
				user.setStatus(0);
				user.setUserId(0);
				user.setRoleId(0);
				user.setFullName("Auth Failed!");
				model.setViewName("login");
				model.addObject("errorMessage", "Invalid login");
				return model;
			}
			model.setViewName("dashboard");
			model.addObject("user", authUser);
			return model;
		} catch (Exception ex) {
			System.err.println("post auth error:" + ex);
			user.setStatus(-1);
			user.setUserId(0);
			user.setRoleId(0);
			user.setFullName("error:" + ex.getMessage());
			model.setViewName("login");
			model.addObject("errorMessage", ex.getMessage());
			return model;

		}
	}
}
