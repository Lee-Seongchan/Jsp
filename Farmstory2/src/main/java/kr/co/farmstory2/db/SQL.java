package kr.co.farmstory2.db;

public class SQL {

	
	//약관
	public final static String SELECT_TERMS = "SELECT * FROM `Terms`"; 
	
	
	//User
	public static final String INSERT_USER = "INSERT INTO `User` SET "
															+ "`uid`=?,"
															+ "`pass`= ?,"
															+ "`name`=?,"											
															+ "`nick`=?,"
															+ "`email`=?,"
															+ "`hp`=?,"
															+ "`zip`=?,"
															+ "`addr1`=?,"
															+ "`addr2`=?,"
															+ "`regip`=?,"
															+ "`regDate`=NOW()";
	
	
	public static final String SELECT_CHECK_UID = "SELECT COUNT(*) FROM `User` WHERE uid = ?";
	public static final String SELECT_CHECK_NICK = "SELECT COUNT(*) FROM `User` WHERE nick = ?";
	
	public static final String SELECT_CHECK_EMAIL = "SELECT COUNT(*) FROM `User` WHERE email = ?";
	public static final String SELECT_COUNT_HP    = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	
	
}
