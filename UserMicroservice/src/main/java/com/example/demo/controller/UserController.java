package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/addUser")
	public String addUser(@RequestBody User user) {
		logger.info("in UserController addUser");
		userService.addUser(user);
		return "user added successfully";
	}
	
	@DeleteMapping("/deleteUser/{uid}")
	public String deleteUser(@PathVariable("uid") int userId) {
		logger.info("in UserController deleteUser");
		userService.deleteUser(userId);
		return "user deleted successfully";
	}
}
