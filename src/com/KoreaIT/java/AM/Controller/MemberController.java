package com.KoreaIT.java.AM.Controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller{
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}
	
	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch(actionMethodName) {
		case "join":
			doJoin();
			break;
		case "list":
			showList();
			break;
		default :
			System.out.println("존재하지 않는 명령어입니다.");
		}
	}
	
	int lastMemberId = 0;
	
	public void doJoin() {
		int id = lastMemberId + 1;
		String loginId = null;
		String loginPw = null;
		while(true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine();
			
			if(isJoinableLoginId(loginId) == false) {
				System.out.println("이미 사용중인 아이디입니다.");
				continue;
			}
			else {
				System.out.println("사용 가능한 아이디입니다.");
				break;
			}
		}
			
		while(true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 재확인: ");
			String loginPw1 = sc.nextLine();
			if(loginPw.equals(loginPw1)) {
				System.out.println("비밀번호가 일치합니다.");
				break;
			} 
			else {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}	
		}
			
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		
		String regDate = Util.getNowDate();
		String updateDate = "";
		
		Member member = new Member(id, loginId, loginPw, name, regDate, updateDate);
		members.add(member);
		
		System.out.printf("%s님 회원가입 되었습니다.\n", name);
		lastMemberId++;
		
	}
	
	public void showList() {
		if(members.size()==0) {
			System.out.println("회원이 존재하지 않습니다.");
		}
		else {
			System.out.println("번호 / 이름        / 아이디     / 비밀번호     / 가입일자     ");
			for(int i = members.size()-1; i >= 0; i--) {
				Member member = members.get(i);
				System.out.printf("%d   / %s      / %s    / %s      / %s     \n", member.id, member.name, member.loginId, member.loginPw, member.regDate);
			}
		}
	}
	
	public boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return true;
			}
		
		return false;
		}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;		
		}
		return -1;
	}
	
	public void maketestData() {
		System.out.println("테스트를 위한 회원 테스트 데이터가 생성되었습니다.");
		members.add(new Member(1, "test1", "test1", "test1", Util.getNowDate(), ""));
		members.add(new Member(2, "test2", "test2", "test2", Util.getNowDate(), ""));
		members.add(new Member(3, "test3", "test3", "test3", Util.getNowDate(), ""));

	}
}
