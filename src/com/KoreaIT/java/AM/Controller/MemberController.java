package com.KoreaIT.java.AM.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller{
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	
	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
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
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			showProfile();
			break;
		default :
			System.out.println("존재하지 않는 명령어입니다.");
		}
	}
	
	int lastMemberId = 3;
	
	private void doJoin() {
		if(isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		
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
	
	private void showList() {
		if(members.size() == 0) {
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
	
	private void doLogin() {
		if(isLogined()) {
			System.out.println("이미 로그인 상태입니다.");
			return;
		}
		
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
			
		Member member = getMemberByloginId(loginId);
			
		if(member == null) {
			System.out.println("아이디 또는 비밀번호를 잘못 입력했습니다.");
			return;
		}
			
		if(member.loginPw.equals(loginPw)) {
			loginedMember = member;
			System.out.printf("%s님 로그인 되었습니다.\n", loginedMember.name);
		}
		else {
			System.out.println("아이디 또는 비밀번호를 잘못 입력했습니다.");
		}
	}
	
	private void doLogout() {
		if(isLogined()) {
			System.out.printf("로그아웃 되었습니다.\n");
			loginedMember = null;
		}
		else {
			System.out.println("로그인 후 이용해주세요.");
		}
	}
	
	private void showProfile() {
		if(isLogined()==false) {
			System.out.println("로그인 상태가 아닙니다."); 
		}
		else {
			System.out.println("== 현재 로그인 한 회원의 정보 ==");
			System.out.printf("로그인 아이디 : %s \n", loginedMember.loginId);
			System.out.printf("이름 : %s \n", loginedMember.name);
		}
	}
	
	private Member getMemberByloginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index != -1) {
			return members.get(index);
		}
		return null;
	}
	
	private boolean isJoinableLoginId(String loginId) {
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
		System.out.println("테스트를 위한 회원 데이터가 생성되었습니다.");
		members.add(new Member(1, "test1", "test1", "test1", Util.getNowDate(), ""));
		members.add(new Member(2, "test2", "test2", "test2", Util.getNowDate(), ""));
		members.add(new Member(3, "test3", "test3", "test3", Util.getNowDate(), ""));

	}
}
