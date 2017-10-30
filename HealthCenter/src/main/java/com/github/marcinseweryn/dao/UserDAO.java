package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.User;

public interface UserDAO {
	
	public void addUser(User user);
	
	public List<User> findAllUsers();
	
	public void deleteUsers(List<Integer> usersIDs);
	
	public void updateUsers(List<Integer> usersIDs, String columns);
	
	public List<User> findUsers(String columns);
	
	public User findUserByID(Integer ID);
	
	public List<User> findUsersByIDs(List<Integer> usersIDs);
	
	public User findUserByEmail(String email);
	
	public User findUserByPesel(String pesel);

}
