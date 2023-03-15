package com.KoreaIT.java.AM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Article;

public class ArticleDao extends Dao{
	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
	
	@Override
	public int getLastId() {
		return lastId;
	}

	public int setNewId() {
		return lastId + 1;
	}
	
	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

	public Article get(int index) {
		return articles.get(index);
	}

}
