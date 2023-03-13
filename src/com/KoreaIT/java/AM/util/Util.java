package com.KoreaIT.java.AM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	/** 포맷팅 현재 날짜/시간 반환 Str*/
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
        Date now = new Date();
 
        String nowDate = sdf.format(now);
        
		return nowDate;
	}
}
