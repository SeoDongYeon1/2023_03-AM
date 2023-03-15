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

	public List<Article> getArticles(String searchKeyword) {
		if (searchKeyword.length() != 0 && searchKeyword != null) {
			System.out.println("searchKeyword : " + searchKeyword);
			
			List<Article> forPrintArticles = new ArrayList<>();
			
			if(searchKeyword.length() > 0) {
				for (Article article : articles) {
					if (article.title.contains(searchKeyword)) {
						forPrintArticles.add(article);
					}
				}
			}
			return forPrintArticles;
			
		}
		return articles;
	}

	
	public int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}

}
