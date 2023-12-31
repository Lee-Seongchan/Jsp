package kr.farmstory1.db;

public class SQL {
	
	public final static String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
														+ "`parent` = ?, "
														+ "`content` = ?, "	
														+ "`writer`= ?, "
														+ "`regip` = ?, "
														+ "`rdate`= NOW()";
	
	public final static String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=? ";
	
	
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
	
	
	public final static String SELECT_PRODUCTS_ALL = "SELECT * FROM `Product` WHERE `stock` > 0 LIMIT ?, 10";	
	public final static String SELECT_PRODUCTS_TYPE = "SELECT * FROM `Product` WHERE `stock` > 0 AND `type`=? LIMIT ?, 10";

	public final static String SELECT_PRODUCT = "SELECT * FROM `Product` WHERE `pNo`= ?";
	
	
	
	
	
	public final static String SELECT_COUNT_PRODUCTS_ALL = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0" ;
	public final static String SELECT_COUNT_PRODUCTS_TYPE = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0 AND `type`= ?" ;
	
	
	//order
	public final static String INSERT_ORDER = "INSERT INTO `Order` SET " 
											+ "`orderProduct` = ?, "
											+ "`orderCount` = ?, "
											+ "`orderDelivery` = ?, "
											+ "`orderPrice` = ?, "
											+ "`orderTotal` = ?, "
											+ "`receiver` = ?, "
											+ "`hp` = ?, "
											+ "`zip` = ?, "
											+ "`addr1` = ?, "
											+ "`addr2` = ?, "
											+ "`orderEtc` = ?, "
											+ "`orderUser` = ?, "
											+ "`orderDate` = NOW()" ;
	
	public final static String SELECT_ORDERS = "SELECT a.*,"
												+ "b.`pName`,"
												+ "b.`thumb1` "
												+ "FROM `Order` AS a "
												+ "JOIN `Product` AS b "
												+ "ON a.orderProduct = b.pNo "
												+ "LIMIT ?, 10";
		
	
	public final static String SELECT_ORDERS_COUNT = "SELECT COUNT(*) FROM `Order`";
	public final static String DELETE_ORDER = "DELETE FROM `Order` WHERE `orderNo` = ? ";
		
}