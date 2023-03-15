package com.KoreaIT.java.AM.Controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.service.ArticleService;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController extends Controller{
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private ArticleService articleService;
	
	public ArticleController(Scanner sc) {
		articleService = Container.articleService;
		this.sc = sc;
	}
	
	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch(actionMethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "write":
			doWrite();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
			default :
				System.out.println("존재하지 않는 명령어입니다.");
		}
	}

	private void showList() {
			String searchKeyword = command.substring("article list".length()).trim();

			List<Article> forPrintArticles = articleService.getForPrintArticles(searchKeyword);
			
			if (forPrintArticles.size() == 0) {
				System.out.println("게시글이 없습니다");
				return;
			}
				
			System.out.println("  번호  |      제목      |         작성 날짜         |  조회수  |  작성자");
			for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
				String writerName = null;
				
				List<Member> members = Container.memberDao.members;
				Article article = forPrintArticles.get(i);
				
				for(Member member : members) {
					if(article.memberId == member.id) {
						writerName = member.name;
						break;
					}
				}
				System.out.printf("   %d   |     %s     |   %s   |   %d    |  %s  \n", article.id, article.title, article.regDate, article.hit, writerName);				
			}
		}

	private void doWrite() {
		int id = articleService.setNewId();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String regDate = Util.getNowDate();
		String updateDate = "";

		Article article = new Article(id, regDate, updateDate, loginedMember.id, title, body);
		articleService.add(article);

		System.out.printf("%d번글이 생성되었습니다.\n", id);
	}

	private void showDetail() {
		String[] commandBits = command.split(" ");

		if (commandBits.length < 3 || commandBits.length > 3) {
			System.out.println("article detail (숫자)를 입력해주세요.");
			return;
		}
		try {
			int id = Integer.parseInt(commandBits[2]);

			Article foundArticle = articleService.getArticleById(id);

			if (foundArticle == null) { 
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			} else {
				String writerName = null;

				List<Member> members = Container.memberDao.members;

				for (Member member : members) {
					if (foundArticle.memberId == member.id) {
						writerName = member.name;
						break;
					}
				}
				
				foundArticle.IncreaseHit();
				System.out.printf("번호 : %d \n", foundArticle.id);
				System.out.printf("작성 날짜 : %s \n", foundArticle.regDate);
				System.out.printf("수정 날짜 : %s \n", foundArticle.updateDate);
				System.out.printf("작성자 : %s \n", writerName); 
				System.out.printf("제목 : %s \n", foundArticle.title);
				System.out.printf("내용 : %s \n", foundArticle.body);
				System.out.printf("조회수 : %d \n", foundArticle.hit);
			}
		} catch (NumberFormatException e) {
			System.out.println("article detail (숫자)를 입력하세요.");
		}

	}

	private void doDelete() {
		String[] commandBits = command.split(" ");

		if (commandBits.length < 3 || commandBits.length > 3) {
			System.out.println("article delete (숫자)를 입력해주세요.");
			return;
		}
		try {
			int id = Integer.parseInt(commandBits[2]);

			Article foundArticle = articleService.getArticleById(id);

			if (foundArticle == null) {
				System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
				return;
			}
			
			if(loginedMember.id != foundArticle.memberId) {
				System.out.println("이 게시물에 권한이 없습니다.");
			}
			else {
				articleService.remove(foundArticle);
				System.out.printf("%d번 게시글이 삭제되었습니다. \n", foundArticle.id);
			}

		} catch (NumberFormatException e) {
			System.out.println("article delete (숫자)를 입력하세요.");
		}

	}

	private void doModify() {
		String[] commandBits = command.split(" ");

		if (commandBits.length < 3 || commandBits.length > 3) {
			System.out.println("article modify (숫자)를 입력해주세요.");
			return;
		}
		try {
			int id = Integer.parseInt(commandBits[2]);

			Article foundArticle = articleService.getArticleById(id);
			
			if (foundArticle == null) {
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
				return;
			} 
			
			if(loginedMember.id != foundArticle.memberId) {
				System.out.println("이 게시물에 권한이 없습니다.");
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

		} catch (NumberFormatException e) {
			System.out.println("article modify (숫자)를 입력하세요.");
		}

	}
	
	public void maketestData() {
		System.out.println("테스트를 위한 게시글 데이터가 생성되었습니다.");
		articleService.add(new Article(1, Util.getNowDate(), "", 1, "test1", "test1", 10));
		articleService.add(new Article(2, Util.getNowDate(), "", 2, "test2", "test2", 20));
		articleService.add(new Article(3, Util.getNowDate(), "", 2, "test3", "test3", 30));
	}

}
