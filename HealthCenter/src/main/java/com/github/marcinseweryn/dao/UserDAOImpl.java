package com.github.marcinseweryn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void addUser(User user) {
		entityManager.persist(user);
		
	}

	@Override
	public List<User> findAllUsers() {
		
		Query query = entityManager.createQuery("from User");
		
		List<User> list = query.getResultList();
		
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUsers(List<Integer> usersIDs) {
		String IDs = usersIDs.toString().substring(1, usersIDs.toString().length() - 1);
		System.out.println(IDs);
		
		for(Integer ID:usersIDs){
			Query query = entityManager.createQuery("delete from User u"
					+ " WHERE u.pesel IN(" + IDs + ")");
			System.out.println(ID);
			query.executeUpdate();
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUsers(List<Integer> usersIDs, User user, String columns) {
		String IDs = usersIDs.toString().substring(1, usersIDs.toString().length() - 1);
		System.out.println(IDs);
		
		Query query = entityManager.createQuery("UPDATE User SET " + columns
				+ " WHERE pesel IN(" + IDs + ")");
		System.out.println(IDs);
		query.executeUpdate();
	}

}
