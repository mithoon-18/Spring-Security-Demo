package com.msg.spring._seq_demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msg.spring._seq_demo.Service.UserService;
import com.msg.spring._seq_demo.model.User;

@RestController
public class UserController {

	@Autowired
	private UserService serive;
	
	@PostMapping("register")
	public User register(@RequestBody User user)
	{
		serive.saveUser(user);
		return user;
	}
}
