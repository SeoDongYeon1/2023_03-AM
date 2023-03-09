package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		List<Article> articles = new ArrayList<>();
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			if (command.equals("article list")) {
				if (articles.size() > 0) 
				{
					Article foundArticle = null;
					System.out.println("  번호  /   제목   /   작성 날짜   ");
					for (int i = articles.size() - 1; i >= 0; i--) 
					{
						foundArticle = articles.get(i);
						System.out.printf("   %d   /   %s    /   %s\n", foundArticle.id, foundArticle.title, foundArticle.regDate);
					}
				} 
				else {
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
				
				if(commandBits.length < 3) {
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				try {
					int id = Integer.parseInt(commandBits[2]);
					
					Article foundArticle = null;
					
					for (int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);
						if (article.id == id) 
						{
							foundArticle = article;
							break;
						}
					}
					if(foundArticle == null) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					}
					else {
						System.out.printf("번호 : %d \n", foundArticle.id);
						System.out.printf("제목 : %s \n", foundArticle.title);
						System.out.printf("내용 : %s \n", foundArticle.body);				
						System.out.printf("작성 날짜 : %s \n", foundArticle.regDate);
						System.out.printf("수정된 날짜 : %s \n", foundArticle.updateDate);
					}
				} catch(NumberFormatException e) {
					System.out.println("article detail (숫자)를 입력하세요.");
					continue;
				}

			} 
			else if (command.startsWith("article delete")) 
			{
				String[] commandBits = command.split(" ");
				
				if(commandBits.length < 3) {
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				try {
					int id = Integer.parseInt(commandBits[2]);
					
					int foundIndex = -1;
					
					for (int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);
						if (article.id == id) 
						{
							foundIndex = i;
							break;
						}
					} 
					if(foundIndex == -1) {
						System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);		
					}
					else {
						articles.remove(foundIndex);
						System.out.printf("%d번 게시글이 삭제되었습니다. \n", id);
					}
					
				} catch(NumberFormatException e) {
					System.out.println("article delete (숫자)를 입력하세요.");
					continue;
				}
			}
			else if (command.startsWith("article modify")) 
			{
				String[] commandBits = command.split(" ");
				
				if(commandBits.length < 3) {
					System.out.println("명령어를 확인해주세요.");
					continue;
				}
				try {
					int id = Integer.parseInt(commandBits[2]);
					
					Article foundArticle = null;
					
					for (int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);
						if (article.id == id) 
						{
							foundArticle = article;
							break;
						}
					}
					if(foundArticle == null) {
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
						System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
					}
					
				} catch(NumberFormatException e) {
					System.out.println("article modify (숫자)를 입력하세요.");
					continue;
				}
			} 
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();
	} 
}

class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;

	Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}
}