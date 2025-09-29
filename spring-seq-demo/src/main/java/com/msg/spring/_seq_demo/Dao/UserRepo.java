package com.msg.spring._seq_demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msg.spring._seq_demo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
	User findByUsername(String username);

}
