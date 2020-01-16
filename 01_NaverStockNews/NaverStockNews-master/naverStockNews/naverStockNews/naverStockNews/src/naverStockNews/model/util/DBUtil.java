package naverStockNews.model.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "TIGER");
	}
	
	//자원반환 
	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if(rset != null) {
				rset.close();
				rset = null;
			}
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
			if(con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//자원반환 - dml[insert /update/delete용  /]
	//preparedStatement 와 statement의 관계는 상속관계 즉 다형성으로 인한 자원 반환 기능
public static void close(Connection con, Statement stmt) {
	try {
		if(stmt != null) {
			stmt.close();
			stmt = null;
		}
		if(con != null) {
			con.close();
			con = null;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}

