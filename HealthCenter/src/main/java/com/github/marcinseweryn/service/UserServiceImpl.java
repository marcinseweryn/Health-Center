package com.github.marcinseweryn.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");	

	public void addUser(User user){	

		if(!user.getPesel().equals("")){
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			
			user.setEnabled("1");
			user.setPassword(hashedPassword);
			userDAO.addUser(user);
		}
	}


	public List<User> findAllUsers() {
		return userDAO.findAllUsers();
	}


	public void deleteUsers(List<Integer> usersIDs) {

		if(usersIDs.size() != 0){
			userDAO.deleteUsers(usersIDs);	
		}
	}


	@Override
	public void updateUsers(List<Integer> usersIDs, User user) {	
		String columns = "";
		
		if(user.getPesel() != null){
			if(!user.getPesel().equals("")){
				columns += "pesel = '" + user.getPesel() + "' ,"; 
			}
		}
		if(user.getGender() != null){
			if(!user.getGender().equals("")){
				columns += "gender = '" + user.getGender() + "' ,";
			}
		}
		if(user.getName() != null){
			if(!user.getName().equals("")){
				columns += "name = '" + user.getName() + "' ,";
			}
		}
		if(user.getSurname() != null){
			if(!user.getSurname().equals("")){
				columns += "surname = '" + user.getSurname() + "' ,";
			}
		}
		if(user.getBirthDate() != null){
			columns += "birthDate = '" + df.format(user.getBirthDate()) + "' ,";
		}
		if(user.getPassword() != null){
			if(!user.getPassword().equals("")){
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(user.getPassword());
				columns += "password = '" + hashedPassword + "' ,";
			}
		}
		if(user.getStreetAddress() != null){
			if(!user.getStreetAddress().equals("")){
				columns += "streetAddress = '" + user.getStreetAddress() + "' ,";
			}
		}
		if(user.getCity() != null){
			if(!user.getCity().equals("")){
				columns += "city = '" + user.getCity() + "' ,";
			}
		}
		if(user.getPostalCode() != null){
			if(!user.getPostalCode().equals("")){
				columns += "postalCode = '" + user.getPostalCode() + "' ,";
			}
		}
		if(user.getPhone() != null){
			if(!user.getPhone().equals("")){
				columns += "phone = '" + user.getPhone() + "' ,";
			}
		}
		if(user.getEmail() != null){
			if(!user.getEmail().equals("")){
				columns += "email = '" + user.getEmail() + "' ,";
			}
		}
		if(user.getRole() != null){
			if(!user.getRole().equals("")){
				columns += "role = '" + user.getRole() + "' ,";
			}
		}
		if(columns.length() > 0){
			columns = columns.substring(0, columns.length() - 1);
		}
		
		if(!columns.equals("")){
			userDAO.updateUsers(usersIDs, columns);
		}
	}


	@Override
	public List<User> findUsers(User user) {
		String columns = "";

		if(user.getID() != null){
			columns += "ID = '" + user.getID() + "' and "; 
		}
		if(user.getPesel() != null){
			if(!user.getPesel().equals("")){
				columns += "pesel = '" + user.getPesel() + "' and "; 
			}
		}
		if(user.getGender() != null){
			if(!user.getGender().equals("")){
				columns += "gender = '" + user.getGender() + "' and ";
			}
		}
		if(user.getName() != null){
			if(!user.getName().equals("")){
				columns += "name = '" + user.getName() + "' and ";
			}
		}
		if(user.getSurname() != null){
			if(!user.getSurname().equals("")){
				columns += "surname = '" + user.getSurname() + "' and ";
			}
		}
		if(user.getBirthDate() != null){
			columns += "birthDate = '" + df.format(user.getBirthDate()) + "' and ";
		}
		if(user.getPassword() != null){
			if(!user.getPassword().equals("")){
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(user.getPassword());
				columns += "password = '" + hashedPassword + "' and ";
			}
		}
		if(user.getStreetAddress() != null){
			if(!user.getStreetAddress().equals("")){
				columns += "streetAddress = '" + user.getStreetAddress() + "' and ";
			}
		}
		if(user.getCity() != null){
			if(!user.getCity().equals("")){
				columns += "city = '" + user.getCity() + "' and ";
			}
		}
		if(user.getPostalCode() != null){
			if(!user.getPostalCode().equals("")){
				columns += "postalCode = '" + user.getPostalCode() + "' and ";
			}
		}
		if(user.getPhone() != null){
			if(!user.getPhone().equals("")){
				columns += "phone = '" + user.getPhone() + "' and ";
			}
		}
		if(user.getEmail() != null){
			if(!user.getEmail().equals("")){
				columns += "email = '" + user.getEmail() + "' and ";
			}
		}
		if(user.getRole() != null){
			if(!user.getRole().equals("")){
				columns += "role = '" + user.getRole() + "' and ";
			}
		}
		
		
		if(columns.length() > 0){
			columns = columns.substring(0, columns.length() - 4);
		}
		
		if(columns.equals("")){
			return null;
		}else{
			return userDAO.findUsers(columns);
		}		
	}


	@Override
	public User findUserByID(Integer ID) {
		return userDAO.findUserByID(ID);
	}


	@Override
	public List<User> findUsersByIDs(List<Integer> usersIDs) {
		if(usersIDs.size() != 0){
			return userDAO.findUsersByIDs(usersIDs);
		}
		return null;
	}
}
