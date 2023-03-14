package com.KoreaIT.java.AM.Controller;

import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {

	public abstract void doAction(String actionMethodName, String command);
	public abstract void maketestData();
	
	protected static Member loginedMember;
	
	public static boolean isLogined() {
		return loginedMember != null;
	}
	
}
