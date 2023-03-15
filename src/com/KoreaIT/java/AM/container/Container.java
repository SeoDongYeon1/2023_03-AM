package com.KoreaIT.java.AM.container;

import com.KoreaIT.java.AM.container.dao.MemberDao;
import com.KoreaIT.java.AM.dao.ArticleDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}
