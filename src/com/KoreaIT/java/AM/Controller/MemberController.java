package com.KoreaIT.java.AM.Controller;

import java.util.Scanner;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.service.MemberService;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller{
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private MemberService memberService;
	
	public MemberController(Scanner sc) {
		memberService = Container.memberService;
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
	
	private void doJoin() {
		int id = memberService.setNewId();
		String loginId = null;
		String loginPw = null;
		String loginPw1 = null;
		String name = null;
		
		while(true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine();
			
			if(loginId.length()==0) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			
			if(memberService.isJoinableLoginId(loginId) == false) {
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
			if(loginPw.length()==0) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			
			while(true) {
				System.out.printf("비밀번호 재확인: ");
				loginPw1 = sc.nextLine();
				
				if(loginPw1.length()==0) {
					System.out.println("필수 정보입니다.");
					continue;
				}
				break;
			}
			
			if(loginPw.equals(loginPw1)) {
				System.out.println("비밀번호가 일치합니다.");
				break;
			} 
			else {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}	
		}
		while(true) {
			System.out.printf("이름 : ");
			name = sc.nextLine();
			
			if(name.length()==0) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			break;
		}

		String regDate = Util.getNowDate();
		String updateDate = "";
		
		Member member = new Member(id, loginId, loginPw, name, regDate, updateDate);
		memberService.add(member);
		
		System.out.printf("%s님 회원가입 되었습니다.\n", name);
		
	}
	
	private void showList() {
		if(memberService.getMembers().size() == 0) {
			System.out.println("회원이 존재하지 않습니다.");
		}
		else {
			System.out.println("번호 | 이름        | 아이디     | 가입일자     ");
			for(int i = memberService.getMembers().size()-1; i >= 0; i--) {
				Member member = memberService.getMembers().get(i);
				System.out.printf("%d   | %s      | %s    | %s     \n", member.id, member.name, member.loginId, member.regDate);
			}
		}
	}
	
	private void doLogin() {
		Member member = null;
		String loginId = null;
		String loginPw = null;
		
		while(true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();
			if(loginId.length()==0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			break;
		}
		while(true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine().trim();
			if(loginPw.length()==0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			break;
		}
			
		member = memberService.getMemberByloginId(loginId);
			
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
	}
	
	private void showProfile() {
		System.out.println("== 현재 로그인 한 회원의 정보 ==");
		System.out.printf("나의 회원번호 : %d\n", loginedMember.id);
		System.out.printf("로그인 아이디 : %s \n", loginedMember.loginId);
		System.out.printf("이름 : %s \n", loginedMember.name);
	}

	public void maketestData() {
		System.out.println("테스트를 위한 회원 데이터가 생성되었습니다.");
		memberService.add(new Member(1, "test1", "test1", "test1", Util.getNowDate(), ""));
		memberService.add(new Member(2, "test2", "test2", "test2", Util.getNowDate(), ""));
		memberService.add(new Member(3, "test3", "test3", "test3", Util.getNowDate(), ""));

	}
}
