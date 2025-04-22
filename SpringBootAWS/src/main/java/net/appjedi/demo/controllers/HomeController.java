package net.appjedi.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.appjedi.demo.services.MainService;
import net.appjedi.demo.models.User;

@RestController
@RequestMapping("/api")

public class HomeController {
	@Autowired
	MainService service;
	
	@GetMapping
	public ResponseEntity<String>home()
	{
		return ResponseEntity.ok("Hello World!");
	}
	@GetMapping("/users")
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
	@PostMapping("/user/{id}")
	@CrossOrigin()
	public String postUser(@PathVariable int id, @RequestBody User user) {
		try {
			//service.save(user);
			//return user;
			String retval =String.format("{id: %d, %s}",id,user);
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
