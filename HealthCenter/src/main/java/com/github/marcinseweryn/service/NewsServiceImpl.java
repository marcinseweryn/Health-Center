package com.github.marcinseweryn.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.NewsDAO;
import com.github.marcinseweryn.model.News;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsDAO newsDAO;
	
	
	@Override
	public void addNews(News news) {
		
		news.setDate(new Timestamp(System.currentTimeMillis()));
		newsDAO.addNews(news);
	}

	@Override
	public List<News> getAllNews() {
		
		return newsDAO.getAllNews();
	}

	@Override
	public void deleteNewsByID(Integer ID) {
		
		newsDAO.deleteNewsByID(ID);
	}

}
