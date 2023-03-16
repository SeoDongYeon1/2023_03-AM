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

	public Member getMemberByloginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index != -1) {
			return members.get(index);
		}
		return null;
	}
	
	public int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;		
		}
		return -1;
	}
	
	public boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return true;
		}
		
		return false;
	}

	public List<Member> getMembers() {
		return members;
	}
}
