package com.github.marcinseweryn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.UserDAO;
import com.github.marcinseweryn.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	public void addUser(User user){	
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(hashedPassword);
		
		userDAO.addUser(user);
	}


	public List<User> findAllUsers() {
		return userDAO.findAllUsers();
	}
}
