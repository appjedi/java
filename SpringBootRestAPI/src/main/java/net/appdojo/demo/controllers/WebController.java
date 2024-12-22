package net.appdojo.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.appdojo.demo.dao.UserDAO;
import net.appdojo.demo.models.User;

@Controller
public class WebController {
	UserDAO userDAO = new UserDAO();

	  @GetMapping("/")
	  public ModelAndView viewIndex(ModelAndView model) {
		  System.out.println ("WEB CONTROLLER INDEX");
	    model.setViewName("index");
	    model.addObject("test","Hello World");
	    return model;
	    //return "index";
	  }
	  @GetMapping("/users")
	  public ModelAndView viewUsers(ModelAndView model) {
		  System.out.println ("WEB CONTROLLER USERS");
	    model.setViewName("users");
	    model.addObject("userList", userDAO.getUsers());
	    model.addObject("test","Hello World");
	    return model;
	    //return "index";
	  }
	  @GetMapping("/login")
	  public ModelAndView viewLogin(ModelAndView model) {
	    model.setViewName("login");
	    return model;
	    //return "index";
	  }
	  @PostMapping(path = "/auth",consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	  @ResponseBody
	  public String  auth(User user) {
			try {
				System.out.printf("1. post auth: \n%s\n", user);
				User authUser = userDAO.auth(user.getUsername(), user._pw());
				System.out.printf("2. post auth: \n%s\n", authUser);
				if (authUser == null) {
					user.setStatus(0);
					user.setUserId(0);
					user.setRoleId(0);
					user.setFullName("Auth Failed!");
					return user.toString();
				}
				return authUser.toString();
			} catch (Exception ex) {
				System.err.println("post auth error:" + ex);
				user.setStatus(-1);
				user.setUserId(0);
				user.setRoleId(0);
				user.setFullName("error:" + ex.getMessage());
				return user.toString();
			}
		}
}
