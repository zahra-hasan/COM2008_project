package team060.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/** helper class .
 * @author love2code.com
 * @author Zahra Hasan A Alhilal 
*/
public abstract class DAOUtils {
	

	public static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	public static void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk:3306/team060?"+
				"useTimezone=true&serverTimezone=UTC"+"&user=team060&"+"password=81092702"+"&useSSL=false");
		
	}
	
}
