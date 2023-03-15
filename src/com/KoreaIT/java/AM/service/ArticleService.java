package com.KoreaIT.java.AM.service;

import java.util.List;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dao.ArticleDao;
import com.KoreaIT.java.AM.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticles(String searchKeyword) {
		
		List<Article> articles = Container.articleDao.getArticles(searchKeyword);
		
		return articles;
	}

	public int setNewId() {
		int id = articleDao.setNewId();
		
		return id;
	}

	public void add(Article article) {
		articleDao.add(article);
	}
	
	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}


}
