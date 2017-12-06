package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.News;

public interface NewsDAO {
	
	public void addNews(News news);
	
	public List<News> getAllNews();
	
	public void deleteNewsByID(Integer ID);

}
