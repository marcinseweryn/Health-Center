package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.News;

public interface NewsService {
	
	public void addNews(News news);
	
	public List<News> getAllNews();
	
	public News findNewsByID(Integer ID);
	
	public void deleteNewsByID(Integer ID);
	
	public void updateNews(News news);
	

}
