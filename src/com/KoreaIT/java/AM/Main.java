package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine();
			
			if(command.length()==0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if (command.equals("exit")) 
			{
				break;
			}
			
			if (command.equals("article list")) {
				if(lastArticleId > 0) {
					System.out.printf("%d개의 게시글이 존재합니다.\n", lastArticleId);
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

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastArticleId++;
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
			
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}