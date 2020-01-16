package quiz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quiz.model.dto.QuizDTO;
import quiz.model.util.DBUtil;

public class QuizDAO {

	// 퀴즈 크롤링 추가
	public static boolean addAllQuizzes(String level, ArrayList<String> quizzes) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			for (int i = 0; i < quizzes.size(); i++) {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(
						"insert into QUIZ (quiz_number, quiz_name, quiz_level)" + "values(quiz_number.nextval, ?, ?)");
				pstmt.setString(1, quizzes.get(i));
				pstmt.setString(2, level);

				try {
					Thread.sleep(50); // 오류 방지
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int result = pstmt.executeUpdate();

				if (result == 1) {
					System.out.print((i + 1) + "  ");
					DBUtil.close(con, pstmt); //
//					return true;
				} else {
					System.out.println("입력 실패: " + (i + 1));
				}
			}
			System.out.println();
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 모든 퀴즈 검색
	public static ArrayList<QuizDTO> getAllQuiz() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QuizDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from quiz");
			rset = pstmt.executeQuery();

			list = new ArrayList<QuizDTO>();
			while (rset.next()) {
				list.add(new QuizDTO(rset.getInt(1), rset.getString(2), rset.getString(3)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}

	// 퀴즈 검색
	public static ArrayList<QuizDTO> getQuiz(String quizName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QuizDTO> quiz = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from quiz where quiz_name=?");
			pstmt.setString(1, quizName);
			rset = pstmt.executeQuery();
			quiz = new ArrayList<QuizDTO>();
			while (rset.next()) {
				quiz.add(new QuizDTO(rset.getInt(1), rset.getString(2), rset.getString(3)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return quiz;
	}

	// 레벨에 따른 퀴즈 검색
	public static ArrayList<String> getLevelQuiz(String quizLevel) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> quiz = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select quiz_Name from quiz where quiz_Level=?");
			pstmt.setString(1, quizLevel);
			rset = pstmt.executeQuery();
			quiz = new ArrayList<String>();

			while (rset.next()) {
				quiz.add("'" + rset.getString(1)+ "'");
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return quiz;
	}
	
	public static ArrayList<String> getLevel() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> quiz = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select distinct quiz_level from quiz order by quiz_level asc");
			rset = pstmt.executeQuery();
			quiz = new ArrayList<String>();

			while (rset.next()) {
				quiz.add(rset.getString(1));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return quiz;
	}
	

}
