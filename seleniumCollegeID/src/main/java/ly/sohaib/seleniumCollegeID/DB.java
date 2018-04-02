package ly.sohaib.seleniumCollegeID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcConnectionPool;

public class DB {
	private static Connection con = null;
	private static String userName = "sa";
	private static String password = "";
	private static String url = "jdbc:h2:C:/Users/Sohaib/Desktop/Debug Images/Students";
	
	@SuppressWarnings("unused")
	private static Connection establishConnection() {
		try(Connection con = DriverManager.getConnection(url, userName, password);){
			DB.con = con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * this makes sure only one connection is available at a time.
	 * @return Connection if it was successful, null if there was a problem which is improbable. 
	 * @throws NullPointerException
	 */
	public static Connection getConnection() throws NullPointerException{
		 JdbcConnectionPool pool = null;
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pool = JdbcConnectionPool.create(url, userName, password);
		try {
			con = pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
			//Objects.nonNull(con)? con: establishConnection();
	}
	
	
}
