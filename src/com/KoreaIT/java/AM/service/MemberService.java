package com.KoreaIT.java.AM.service;

import java.util.List;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dao.MemberDao;
import com.KoreaIT.java.AM.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public int setNewId() {
		int id = memberDao.setNewId();
		return id;
	}

	public Member getMemberByloginId(String loginId) {
		return memberDao.getMemberByloginId(loginId);
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public List<Member> getMembers() {
		return memberDao.getMembers();
	}
}
