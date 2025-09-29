package com.msg.spring._seq_demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.msg.spring._seq_demo.Dao.UserRepo;
import com.msg.spring._seq_demo.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
		
	}

}
