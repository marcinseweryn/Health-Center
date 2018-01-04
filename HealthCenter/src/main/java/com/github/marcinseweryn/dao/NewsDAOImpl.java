package com.github.marcinseweryn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.News;

@Repository
class NewsDAOImpl implements NewsDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addNews(News news) {
		entityManager.persist(news);
	}

	@Override
	public List<News> getAllNews() {
		
		Query query = entityManager.createQuery("FROM News ORDER BY date DESC");	
		
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteNewsByID(Integer ID) {
		
		Query query = entityManager.createQuery("DELETE FROM News WHERE ID =" + ID);
		query.executeUpdate();
	}

	@Override
	public News findNewsByID(Integer ID) {
	
		return entityManager.find(News.class, ID);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateNews(News news) {
		
		entityManager.merge(news);
		
	}

}
