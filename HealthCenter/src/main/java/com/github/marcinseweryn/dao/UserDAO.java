package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.User;

public interface UserDAO {
	
	public void addUser(User user);
	
	public List<User> findAllUsers();
	
	public void deleteUsers(List<Integer> usersIDs);

}
