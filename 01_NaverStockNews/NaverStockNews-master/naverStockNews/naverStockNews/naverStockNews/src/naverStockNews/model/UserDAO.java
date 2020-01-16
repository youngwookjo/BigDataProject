package naverStockNews.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import naverStockNews.model.dto.UserDTO;
import naverStockNews.model.util.DBUtil;

public class UserDAO {

	// 가입
	public static boolean addUser(UserDTO user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into news_user values(?,?,?,?,?)");
			pstmt.setString(1, user.getId());
			pstmt.setInt(2, user.getPw());
			pstmt.setString(3, user.getStockName());
			pstmt.setString(4, user.getName());
			pstmt.setInt(5, user.getPhoneNO());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 검색 -all
	public static ArrayList<UserDTO> getAllUsers() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from news_user");
			rset = pstmt.executeQuery();

			list = new ArrayList<UserDTO>();
			while (rset.next()) {
				list.add(new UserDTO(rset.getString(1), rset.getInt(2), rset.getString(3), rset.getString(4),
						rset.getInt(5)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}

	// 검색 -one, user_id로
	public static UserDTO getOneUser(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserDTO oneUser = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from news_user where user_id=?");
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				oneUser = new UserDTO(rset.getString(1), rset.getInt(2), rset.getString(3), rset.getString(4),
						rset.getInt(5));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return oneUser;
	}

	// 수정	- user_id와 pw 입력할 시 새 pw로 변경
	public static boolean updateUserPW(int pwNew, String userId, int pwOriginal) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update news_user set pw=? where user_id=? and pw=?");
			pstmt.setInt(1, pwNew);
			pstmt.setString(2, userId);
			pstmt.setInt(3, pwOriginal);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 수정	- user_id와 pw 입력할 시 user_name 변경
	public static boolean updateUserName(String name, String userId, int pw) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update news_user set user_name=? where user_id=? and pw=?");
			pstmt.setString(1, name);
			pstmt.setString(2, userId);
			pstmt.setInt(3, pw);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 수정	- user_id와 pw 입력할 시 phone_no 변경
	public static boolean updateUserPhonNo(int phoneNO, String userId, int pw) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update news_user set phone_no=? where user_id=? and pw=?");
			pstmt.setInt(1, phoneNO);
			pstmt.setString(2, userId);
			pstmt.setInt(3, pw);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 수정	- user_id와 pw 입력할 시 관심 stock_name 바꾸기
	public static boolean updateUserOtherStockName(String stockName, String userId, int pw) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update news_user set stock_name=? where user_id=? and pw=?");
			pstmt.setString(1, stockName);
			pstmt.setString(2, userId);
			pstmt.setInt(3, pw);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 수정	- user_id와 pw 입력할 시 관심 stock_name 없애기
	public static boolean deleteUserStockName(String userId, int pw) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update news_user set stock_name='없음' where user_id=? and pw=?");
			pstmt.setString(1, userId);
			pstmt.setInt(2, pw);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	//탈퇴	- user_id와 pw 입력할 시
		public static boolean deleteUser(String userId, int pw) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("delete from news_user where user_id=? and pw=?");
				pstmt.setString(1, userId);
				pstmt.setInt(2, pw);
				
				int result = pstmt.executeUpdate();
				if(result == 1){
					return true;
				}
			}finally{
				DBUtil.close(con, pstmt);
			}
			return false;
		}
}
