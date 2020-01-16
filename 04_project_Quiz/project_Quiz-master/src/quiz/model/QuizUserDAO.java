package quiz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quiz.model.dto.QuizUserDTO;
import quiz.model.util.DBUtil;

public class QuizUserDAO {
	
	// CREATE - 가입
	public static boolean addUser(QuizUserDTO user) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into QUIZ_USER(USER_NUMBER,USER_ID,USER_PW,USER_NAME) values(user_number.nextval, ?, ?, ?)");
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserGrade());		
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// UPDATE //  패스워드 받아서 패스워드  수정
	public static boolean updateUser(String userId, String oldPw, String newPw) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update quiz_user set user_pw=? where user_id=? and user_pw=?");
			pstmt.setString(1, newPw);
			pstmt.setString(2, userId);
			pstmt.setString(3, oldPw);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	
	// DELETE // 아이디 패스워드 받아서 탈퇴	
	public static boolean deleteUser(String userId, String userPw) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from quiz_user where user_id=? and user_pw=?");
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// READ
	//회원 아이디로 해당 회원 모든 정보 검색
	public static QuizUserDTO getUser(String userId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QuizUserDTO user = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from QUIZ_USER where user_id=?");
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()){
				user = new QuizUserDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return user;
	} 
	
	
	//모든 회원 정보 검색
	public static ArrayList<QuizUserDTO> getAllusers() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QuizUserDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from QUIZ_USER");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<QuizUserDTO>();
			while(rset.next()){
				list.add(new QuizUserDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	
	public static int getUserNumber(String userName) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int a = 0;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select quiz_number from QUIZ_USER where user_name=?");
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();
			if(rset.next()){
				a = rset.getInt(1);
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return a;
	}
}


/*
 * 	private int userNumber;
	private String userId;
	private String userPw;
	private String userName;
	private String userGrade;
	private String userQuizCount;
	private String userTotalPoint;
	insert into QUIZ_USER(USER_NUMBER,USER_ID,USER_PW,USER_NAME)  values (USER_NUMBER.nextval,'yyy2410', '241023', '조영욱');
 *  */
