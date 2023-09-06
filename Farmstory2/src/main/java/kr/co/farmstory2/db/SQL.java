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

	
	public final static String SELECT_USER = "SELECT * FROM `User`WHERE uid = ? AND pass = ?";
	
	public static final String SELECT_CHECK_UID = "SELECT COUNT(*) FROM `User` WHERE uid = ?";
	public static final String SELECT_CHECK_NICK = "SELECT COUNT(*) FROM `User` WHERE nick = ?";
	
	public static final String SELECT_CHECK_EMAIL = "SELECT COUNT(*) FROM `User` WHERE email = ?";
	public static final String SELECT_COUNT_HP    = "SELECT COUNT(*) FROM `User` WHERE `hp`= ? ";
	
	
	
	
	
	//Article 
	public final static String INSERT_ARTICLE = "INSERT INTO `Article` SET " 
													+ "`cate` = ?, "
													+ "`title`=?, " 
													+ "`content`=?, "
													+ "`file` = ?, "
													+ "`writer`=?, "
													+ "`regip`=?, "
													+ "`rdate`=NOW()";
	
	public final static String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`"; //방금 입력된 글.
	
	
	
	public final static String SELECT_ARTICLE = "SELECT * FROM `Article` AS a "
												+ "LEFT JOIN `File` AS b "
												+ "ON a.`no` = b.`ano` "
												+ "WHERE `no`=?" ;
	
	
	public static final String SELECT_ARTICLES = "SELECT a.*, "
													+ "b.nick "
													+ "FROM `Article` AS a "
													+ "JOIN `User` AS b on a.`writer` = b.`uid` "
													+ "WHERE `parent` = 0 AND "
													+ "`cate` = ? "
													+ "ORDER BY a.`no` DESC "
													+ "LIMIT ?, 10";
	
	public final static String SELECT_COUNT_TOTAL = "SELECT Count(*) FROM `Article` WHERE `parent`=0 and `cate` = ?"; //전체 게시글 조회
	
	
	
	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET "
														+ "`title` = ?, "
														+ "`content` = ? "
														+ "WHERE `no` = ? ";
	
	public final static String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent` = ?";
	
	
	//comment
	//댓글 입력
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
																+ "`parent`=?, "
																+ "`content`=?,"
																+ "`writer`=?,"
																+ "`regip`=?,"
																+ "`rdate`=NOW()";
	
	//제일 최근에 쓴 댓글 
	public final static String SELECT_COMMENT_LATEST = "SELECT "
															+ "a.*, "
															+ "b.`nick` "
															+ "FROM `Article` AS a "
															+ "JOIN `User` AS b ON a.writer = b.uid "
															+ "WHERE `parent`!=0 "
															+ "ORDER BY `no` DESC LIMIT 1"; 
	
	//댓글 출력
	public final static String SELECT_COMMENTS = "SELECT "
														+ "a.*, "
														+ "b.`nick` "
														+ "FROM `Article` AS a "
														+ "JOIN `User` AS b ON a.writer = b.uid "
														+ "WHERE `parent`=?";
	//댓글수정
	public final static String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=? ";
	
	//댓글 삭제
	public final static String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=? ";
	
	//File
	public final static String INSERT_FILE = "INSERT INTO `File` SET "
												+ "`ano` = ?, "
												+ "`oriName` = ?, "
												+ "`newName` = ?, "
												+ "`rdate` = NOW() ";
	
	public final static String SELECT_FILE = "SELECT * FROM `File` WHERE `fno` = ? ";
	public final static String DELETE_FILE = "DELETE FROM `File` WHERE `ano` = ? ";   //하나의 글에 여러 파일이 있을 수 있기 때문에								
	
	
	
	
	
	
	
	//product
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
	
	public final static String SELECT_PRODUCTS = "SELECT * FROM `Product` ";
	
}
