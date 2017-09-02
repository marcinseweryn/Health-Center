package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.User;

public interface UserDAO {
	
	public void addUser(User user);
	
	public List<User> findAllUsers();
	
	public void deleteUsers(List<Integer> usersIDs);
	
	public void updateUsers(List<Integer> usersIDs, User user, String columns);
	
	public List<User> findUsers(User user, String columns);
	
	public User findUser(String pesel);
	
	public List<User> findUsersByIDs(List<Integer> usersIDs);

}
