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
					System.out.println("  번호  /   제목   ");
					for (int i = articles.size() - 1; i >= 0; i--) 
					{
						foundArticle = articles.get(i);
						System.out.printf("   %d   /   %s   \n", foundArticle.id, foundArticle.title);
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

				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastArticleId++;
			} 
			else if (command.startsWith("article detail ")) 
			{
				String[] commandBits = command.split(" ");
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
					System.out.printf("작성날짜 : %s \n", foundArticle.regDate);
					System.out.printf("제목 : %s \n", foundArticle.title);
					System.out.printf("내용 : %s \n", foundArticle.body);				
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
	String title;
	String body;

	Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
}