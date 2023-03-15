package com.KoreaIT.java.AM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Member;

public class MemberDao extends Dao{
	public List<Member> members;
	
	@Override
	public int getLastId() {
		return lastId;
	}
	
	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
		lastId++;
	}

	public int setNewId() {
		return lastId + 1;
	}

	public Member get(int index) {
		return members.get(index);
	}
}
