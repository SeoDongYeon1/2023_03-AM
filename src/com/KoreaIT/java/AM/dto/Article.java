package com.KoreaIT.java.AM.dto;

public class Article extends Dto{
	public String title;
	public String body;
	public int hit;
	public int memberId;
	public String membername;

	public Article(int id, String regDate, String updateDate, int memberId, String membername, String title, String body) {
		this(id, regDate, updateDate, memberId, membername, title, body, 0);
	}
	public Article(int id, String regDate, String updateDate, int memberId, String membername, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.memberId = memberId;
		this.membername = membername;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void IncreaseHit() {
		this.hit++;
	}
}
