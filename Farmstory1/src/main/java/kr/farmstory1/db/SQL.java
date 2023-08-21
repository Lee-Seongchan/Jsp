package kr.farmstory1.db;

public class SQL {
	
	public final static String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
														+ "`parent` = ?, "
														+ "`content` = ?, "	
														+ "`writer`= ?, "
														+ "`regip` = ?, "
														+ "`rdate`= NOW()";
	
	public final static String SELECT_USER = "SELECT * FROM `User`WHERE uid = ? AND pass = ?";
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
	
	public final static String INSERT_ARTICLE = "INSERT INTO `Article` SET " 
															+ "`cate` = ?, "
															+ "`title`=?, " 
															+ "`content`=?, "
															+ "`writer`=?, "
															+ "`regip`=?, "
															+ "`rdate`=NOW()";

	public final static String SELECT_LATESTS = "SELECT `no`,`title`,`rdate` FROM `Article` WHERE `parent` = 0 AND `cate` = ? "
															+ " Order BY `no` DESC LIMIT ? ";
	
	public final static String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no` = ? ";
												

	public static final String SELECT_ARTICLES = "SELECT a.*, "
															+ "b.nick "
															+ "FROM `Article` AS a "
															+ "JOIN `User` AS b on a.`writer` = b.`uid` "
															+ "WHERE `parent` = 0 AND "
															+ "`cate` = ? "
															+ "ORDER BY a.`no` DESC "
															+ "LIMIT ?, 10";
	
	
	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET "
												+ "`title` = ?, "
												+ "`content` = ? "
												+ "WHERE `no` = ? ";
	
	
	public static final String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no` = ? OR `parent` = ? ";
	
	
	public static final String SELECT_COMMENTS = "SELECT a.* , b.`nick` "
													+ "FROM `Article` AS a "
													+ "JOIN `User` AS b  ON a.writer = b.uid "
													+ "WHERE `parent` = ? ";
	
	public final static String SELECT_COUNT_TOTAL = "SELECT COUNT(*) "
															+ "FROM `Article` "
															+ "WHERE `parent` = 0 AND "
															+ "`cate` = ?";	

	
	//Product 
	
	public final static String INSERT_PRODUCT = "INSERT INTO `Product` SET"
										+ "`type` = ?, "
										+ "`pName` = ?, "
										+ "`price` = ?, "
										+ "`delivery` = ?, "
										+ "`stock` = ?, "
										+ "`thumb1` = ?, "
										+ "`thumb2` = ?, "
										+ "`thumb3` = ?, "
										+ "`seller` = ?, "
										+ "`etc` = ?, "
										+ "`rdate` = NOW()";
	
	
}
