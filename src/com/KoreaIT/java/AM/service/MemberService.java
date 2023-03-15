package com.KoreaIT.java.AM.service;

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

	public int size() {
		return memberDao.members.size();
	}

	public Member get(int i) {
		return memberDao.members.get(i);
	}
}
