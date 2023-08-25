package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBHelper {

		
		protected Connection conn = null;
		protected PreparedStatement psmt = null;
		protected Statement stmt = null;
		protected ResultSet rs = null;
		
		public Connection getConnection() {
		Logger logger = LoggerFactory.getLogger(getClass());
		
			try {
				Context ctx = (Context)new InitialContext().lookup("java:comp/env");	
				DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
				conn = ds.getConnection();
				
				
			} catch (Exception e) {
				logger.error("DAO Error");
			}
			
			return null;
		}
}
