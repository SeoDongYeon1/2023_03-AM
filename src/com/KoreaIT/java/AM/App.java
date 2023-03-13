package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Controller.ArticleController;
import com.KoreaIT.java.AM.Controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;

public class App {
	
	static List<Article> articles;
	static List<Member> members;
	
	public App() { // 시작하자마자 리스트 생성
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);
		
		articleController.maketestData();

		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) 
			{
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (command.equals("exit")) 
			{
				break;
			}
			
			if (command.startsWith("article list")) 
			{
				articleController.showList(command);
			} 
			else if (command.equals("article write")) 
			{
				articleController.doWrite(command);
			} 
			else if (command.startsWith("article detail"))
			{
				articleController.showDetail(command);
			}
			else if (command.startsWith("article delete")) 
			{
				articleController.doDelete(command);
			}
			else if (command.startsWith("article modify")) 
			{
				articleController.doModify(command);
			}
			else if (command.equals("member join")) 
			{	
				memberController.doJoin();
			}
			else if (command.equals("member list")) 
			{
				memberController.showList();
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		System.out.println("==프로그램 끝==");

		sc.close();
		
	}
}
