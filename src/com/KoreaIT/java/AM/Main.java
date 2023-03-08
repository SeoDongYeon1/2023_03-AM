package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		
		String command = sc.nextLine();
		System.out.printf("입력된 명령어 : %s\n", command);
		
		int command2 = sc.nextInt();
		System.out.printf("입력된 명령어 : %d\n", command2);
		
		System.out.println("==프로그램 끝==");
		
		sc.close();
	}
}