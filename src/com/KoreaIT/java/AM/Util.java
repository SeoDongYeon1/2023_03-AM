package com.KoreaIT.java.AM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
        Date now = new Date();
 
        String nowDate = sdf.format(now);
		return nowDate;
	}
}
