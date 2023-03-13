package com.KoreaIT.java.AM.dto;

public class Member extends Dto{
	public String loginId;
	public String loginPw;
	public String name;
	public String updateDate;
	
	public Member(int id, String loginId, String loginPw, String name, String regDate, String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}
}
