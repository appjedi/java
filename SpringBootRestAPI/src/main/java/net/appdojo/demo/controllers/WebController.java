package net.appdojo.demo.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.appdojo.demo.dao.UserDAO;
import net.appdojo.demo.models.User;

@Controller
public class WebController {
	UserDAO userDAO = new UserDAO();

	  @RequestMapping("/")
	  public String viewBooks(ModelAndView model) {
System.out.println ("WEB CONTROLLER INDEX");
	    model.setViewName("index");
	   // model.addObject("items", itemService.getAll());
	    //return model;
	    return "index";
	  }


	  @RequestMapping(
			  path = "/auth", 
			  method = RequestMethod.POST,
			  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
			  produces = {
			    MediaType.APPLICATION_ATOM_XML_VALUE, 
			    MediaType.APPLICATION_JSON_VALUE
			  })		
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
