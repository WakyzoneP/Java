package eu.ase.test;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

// Mark 8: create public class UtilsDAO with only one static field c from class Connection (SQL/JDBC)
	//	This class contains the following 3 static methods:
	//	a.	public static void setConnection() - set the connection by using org.sqlite.JDBC driver and connection string: jdbc:sqlite:test.db
	//	b.	public static void closeConnection() - close the SQL/JDBC connection
	//	c.	public static String selectData() throws SQLException - for SQL selecting all the phones from the already created SQLite DB file
	//		- select * from PHONES
	//		- PHONES table is already created with the following columns id - INT, PRODUCER - TEXT, DIAGONAL - REAL and WEIGHT - REAL
	//		- the String containing the view after selecting the entire table is having each table line separated with "\r\n" and each column value will be separated by ":"

public class UtilsDAO {
//	private static Connection c;
//
//	public static void setConnection(String dbFile) throws ClassNotFoundException {
//		Class.forName("org.sqlite.JDBC");
//		try {
//			c=DriverManager.getConnection("jdbc:sqlite"+dbFile);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void closeConnection() {
//		try {
//			c.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static String selectData() {
//		String sql="Select * from PHONES";
//		String s=null;
//		try {
//			Statement st=(Statement) c.createStatement();
//			ResultSet rs=((java.sql.Statement)st).executeQuery(sql);
//			while(rs.next()) {
//				s=rs.getInt("id")+" "+rs.getString("PRODUCER")+" "+rs.getDouble("DIAGONAL")+ " "+rs.getFloat("WEIGHT");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return s;
//	}
}
