package com.github.marcinseweryn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.UserDAOImpl;
import com.github.marcinseweryn.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAOImpl userDAOImpl;
	
	public void addUser(User user){		
		userDAOImpl.addUser(user);
	}
}
