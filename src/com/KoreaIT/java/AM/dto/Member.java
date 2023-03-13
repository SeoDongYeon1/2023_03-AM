package com.KoreaIT.java.AM.dto;

public class Member extends Dto{
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(int id, String loginId, String loginPw, String name, String regDate) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = regDate;
	}
}
