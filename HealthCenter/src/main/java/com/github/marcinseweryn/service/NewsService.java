package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.News;

public interface NewsService {
	
	public void addNews(News news);
	
	public List<News> getAllNews();
	
	public void deleteNewsByID(Integer ID);

}
