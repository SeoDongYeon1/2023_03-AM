package com.KoreaIT.java.AM.dao;

public abstract class Dao {
	protected int lastId;
	
	public Dao() {
		lastId = 0;
	}
	
	public abstract int getLastId();
}
