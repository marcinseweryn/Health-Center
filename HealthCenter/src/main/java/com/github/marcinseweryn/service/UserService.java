package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.User;

public interface UserService {
	
	public void addUser(User user);
	
	public List<User> findAllUsers();
	
	public void deleteUsers(List<Integer> usersIDs);
	
	public void updateUsers(List<Integer> usersIDs, User user);
	
	public List<User> findUsers(User user);
	
	public User findUserByID(Integer ID);
	
	public List<User> findUsersByIDs(List<Integer> usersIDs);
}
