package kr.farmstory1.db;

public class SQL {
	
	public final static String SELECT_TERMS = "SELECT * FROM `Terms`";
	public final static String SELECT_USER = "SELECT * FROM `User`WHERE uid = ? AND pass = ?";
}

