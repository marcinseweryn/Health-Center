package com.github.marcinseweryn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.UserDAO;
import com.github.marcinseweryn.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public void addUser(User user){		
		userDAO.addUser(user);
	}
}
