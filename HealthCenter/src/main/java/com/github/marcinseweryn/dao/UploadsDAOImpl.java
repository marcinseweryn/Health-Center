package com.github.marcinseweryn.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.Uploads;

@Repository
public class UploadsDAOImpl implements UploadsDAO{

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Uploads file) {

		entityManager.persist(file);
	}

	@Override
	public Uploads read(String fileName) {
		
		Query query = entityManager.createQuery("FROM Uploads WHERE fileName = '" + fileName + "'");
		
		Uploads file;
		try{
			file = (Uploads) query.getSingleResult();
		}catch(javax.persistence.NoResultException e){
			return null;
		}
	
		return file;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateFileByFileName(String fileName, byte[] file) {

		Query query = entityManager.createQuery("UPDATE Uploads SET file = :file"
				+ " WHERE fileName = '" + fileName +"'");
		query.setParameter("file", file);
		query.executeUpdate();
	}
}
