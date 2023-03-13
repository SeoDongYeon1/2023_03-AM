package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App {
	
	static List<Article> articles;
	static List<Member> members;
	
	public App() { // 시작하자마자 리스트 생성
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("==프로그램 시작==");

		maketestData();

		int lastArticleId = 3;
		

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(members, sc);

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
				if (articles.size() > 0) 
				{
					String searchKeyword = command.substring("article list".length()).trim();

					List<Article> forPrintArticles = articles;

					if (searchKeyword.length() > 0) {
						System.out.println("searchKeyword : " + searchKeyword);
						forPrintArticles = new ArrayList<>();

						for (Article article : articles) {
							if (article.title.contains(searchKeyword)) {
								forPrintArticles.add(article);
							}
						}
						if (forPrintArticles.size() == 0) {
							System.out.println("검색 결과가 없습니다");
							continue;
						}
					}
					System.out.println("  번호  /      제목      /         작성 날짜         /  조회수");
					for (int i = forPrintArticles.size() - 1; i >= 0; i--) 
					{
						Article artilce = articles.get(i);
						System.out.printf("   %d   /     %s       /   %s   /   %d \n", artilce.id, artilce.title, artilce.regDate, artilce.hit);
					}
				} 
				else 
				{
					System.out.println("게시글이 없습니다.");
				}
			} 
			else if (command.equals("article write")) 
			{
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getNowDate();
				String updateDate = "";

				Article article = new Article(id, regDate, updateDate, title, body);
				articles.add(article);

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastArticleId++;
			} 
			else if (command.startsWith("article detail"))
			{
				String[] commandBits = command.split(" ");

				if (commandBits.length < 3) 
				{
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				try {
					int id = Integer.parseInt(commandBits[2]);

					Article foundArticle = getArticleById(id);

					if (foundArticle == null) 
					{
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					}
					else {
						foundArticle.IncreaseHit();
						System.out.printf("번호 : %d \n", foundArticle.id);
						System.out.printf("제목 : %s \n", foundArticle.title);
						System.out.printf("내용 : %s \n", foundArticle.body);
						System.out.printf("작성 날짜 : %s \n", foundArticle.regDate);
						System.out.printf("수정된 날짜 : %s \n", foundArticle.updateDate);
						System.out.printf("조회수 : %d \n", foundArticle.hit);
					}
				} catch (NumberFormatException e) {
					System.out.println("article detail (숫자)를 입력하세요.");
					continue;
				}

			}
			else if (command.startsWith("article delete")) 
			{
				String[] commandBits = command.split(" ");

				if (commandBits.length < 3) 
				{
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				try {
					int id = Integer.parseInt(commandBits[2]);

					int foundIndex = getArticleIndexById(id);
					
					if (foundIndex == -1) 
					{
						System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					} else {
						articles.remove(foundIndex);
						System.out.printf("%d번 게시글이 삭제되었습니다. \n", id);
					}

				} catch (NumberFormatException e) {
					System.out.println("article delete (숫자)를 입력하세요.");
					continue;
				}
			}
			else if (command.startsWith("article modify")) 
			{
				String[] commandBits = command.split(" ");

				if (commandBits.length < 3) 
				{
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				try {
					int id = Integer.parseInt(commandBits[2]);

					Article foundArticle = getArticleById(id);
					
					if (foundArticle == null) 
					{
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					}
					else {
						System.out.printf("수정할 제목 : ");
						String title = sc.nextLine();
						System.out.printf("수정할 내용 : ");
						String body = sc.nextLine();
						String updateDate = Util.getNowDate();

						foundArticle.title = title;
						foundArticle.body = body;
						foundArticle.updateDate = updateDate;
						System.out.printf("%d번 게시글이 수정되었습.니다.\n", id);
					}

				} catch (NumberFormatException e) {
					System.out.println("article modify (숫자)를 입력하세요.");
					continue;
				}
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
	
	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
		if (article.id == id) {
			return i;
			}
			i++;		
		}return -1;
	}

	public Article getArticleById(int id) {
		int index = getArticleIndexById(id);
		
		if(index != -1) {
			return articles.get(index);
			}
			return null;
		}
	
	public void maketestData() {
		System.out.println("테스트를 위한 테스트 데이터가 생성되었습니다.");
		articles.add(new Article(1, Util.getNowDate(), "", "test1", "test1", 10));
		articles.add(new Article(2, Util.getNowDate(), "", "test2", "test2", 20));
		articles.add(new Article(3, Util.getNowDate(), "", "test3", "test3", 30));
	}

}
