package kr.co.jboard1.db;

public class SQL {
	
	//user
	public static final String INSERT_USER = "INSERT INTO `User` SET "
											+ " `uid` = ?,"
											+ " `pass`= SHA2(?, 256)," 
											+ " `name` = ?,"
										    + " `nick` = ?,"
											+ " `email` = ?," 
										    + " `hp` = ?," 
											+ " `zip` = ?," 
										    + " `addr1` = ?, "
										    + " `addr2`=?," 
										    + " `regip`=?," 
										    + " `regDate`= NOW()";
	

										    
	public static final String SELECT_USER = "SELECT * FROM `user` WHERE `uid`=? AND `pass`=SHA2(?, 256)";					    
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `User` WHERE `uid`=? ";					    
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(*) FROM `User` WHERE `nick`=? ";					    
	public static final String SELECT_COUNT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=? ";					    
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `User` WHERE `hp`=? ";	
	
	
	//Article
	public final static String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`title`= ?, "
												+ "`content`= ?, "
												+ "`writer`= ?, "
												+ "`regip`= ?, "
												+ "`rdate`= NOW() ";
	
	public final static String SELECT_ARTICLES = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10"; //시작위치(0부터 시작), 반환갯수
	
	

	public final static String SELECT_COUNTTOTAL = "SELECT Count(*) FROM `Article`";
	
	
}

