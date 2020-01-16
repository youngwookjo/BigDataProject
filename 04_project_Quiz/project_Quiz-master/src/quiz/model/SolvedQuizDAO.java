package quiz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import quiz.model.dto.QuizDTO;
import quiz.model.dto.QuizUserDTO;
import quiz.model.dto.SolvedQuizDTO;
import quiz.model.dto.SysNameDTO;
import quiz.model.util.DBUtil;

public class SolvedQuizDAO {

   // CREAT - 검증 완료
   public static boolean addSolvedQuiz(int userNumber, String quizName, String quizLevel, String quizCode)
         throws SQLException {
      System.out.println("solvedDAT.addSolvedQuiz 호출 성공");
      Connection con = null;
      PreparedStatement pstmt = null;
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(
               "insert into SOLVED_QUIZ values(?, (select quiz_number from QUIZ where QUIZ_NAME = ? and QUIZ_LEVEL = ?), ?, Sysdate)");
         pstmt.setInt(1, userNumber);
         pstmt.setString(2, quizName);
         pstmt.setString(3, quizLevel);
         pstmt.setString(4, quizCode);
         int result = pstmt.executeUpdate();
         if (result == 1) {
            System.out.println("저장 성공");
            return true;
         }
      } finally {
         DBUtil.close(con, pstmt);
      }
      System.out.println("저장 실패");
      return false;
   }

   // UPDATE -> quizCode만 수정
   public static boolean updateSolvedQuiz(int userNumber, String quizName, String quizLevel, String quizCode)
         throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      try {
         con = DBUtil.getConnection();

         pstmt = con.prepareStatement(
               "update SOLVED_QUIZ set QUIZ_CODE = ? where USER_NUMBER = ? and QUIZ_NUMBER = (select QUIZ_NUMBER from QUIZ where QUIZ_NAME = ? and QUIZ_LEVEL = ?)");
         pstmt.setString(1, quizCode);
         pstmt.setInt(2, userNumber);
         pstmt.setString(3, quizName);
         pstmt.setString(4, quizLevel);

         int result = pstmt.executeUpdate();
         if (result == 1) {
            return true;
         }
      } finally {
         DBUtil.close(con, pstmt);
      }
      return false;
   }

   // DELETE
   public static boolean deleteSolvedQuiz(int userNumber, String quizName, String quizLevel) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(
               "delete from SOLVED_QUIZ where USER_NUMBER = ? and QUIZ_NUMBER = (select QUIZ_NUMBER from QUIZ where QUIZ_NAME = ? and QUIZ_LEVEL = ?)");
         pstmt.setInt(1, userNumber);
         pstmt.setString(2, quizName);
         pstmt.setString(3, quizLevel);
         int result = pstmt.executeUpdate();
         if (result == 1) {
            return true;
         }
      } finally {
         DBUtil.close(con, pstmt);
      }
      return false;
   }

   // READ
   // 전체 읽기 기능은 -> code 제외
   // 코드는 반드시 특정 문제 검색 시에만. (SolvedQuizDetail.jsp)

   // 전체 읽기 (code 제외) // 서로 다른 테이블의 값들 출력이라 어떤 객체에 넣어야할지 몰라서 ArrayList <> 에
   // String[] 넣었는데 문법 오류는 없어보이네요. 될지 안될지는 해봐야 알 듯 합니다 ㅎㅎ
   public static ArrayList<SysNameDTO> getSolvedQuizList(int userNumber) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<SysNameDTO> list = null;
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(
               "select q.QUIZ_NAME, s.QUIZ_SYSDATE from QUIZ q, SOLVED_QUIZ s where q.QUIZ_NUMBER in (select QUIZ_NUMBER from SOLVED_QUIZ where USER_NUMBER = ?) and q.QUIZ_NUMBER = s.QUIZ_NUMBER");
         pstmt.setInt(1, userNumber);
         rset = pstmt.executeQuery();

         list = new ArrayList<SysNameDTO>();
         while (rset.next()) {
            list.add(new SysNameDTO(rset.getString(1), rset.getString(2)));
         }
      } finally {
         DBUtil.close(con, pstmt, rset);
      }
      return list;
   }

   // user_number, quiz_level 받아서 해당하는 퀴즈 이름 받아오기
   public static ArrayList<QuizDTO> getSolvedQuizNameList(int userNumber, String quizLevel) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<QuizDTO> list = null;
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(
               "select quiz_name from quiz where quiz_level = ? and quiz_number in (select quiz_number from solved_quiz where user_number = ?)");
         pstmt.setInt(2, userNumber);
         pstmt.setString(1, quizLevel);
         rset = pstmt.executeQuery();

         list = new ArrayList<QuizDTO>();
         while (rset.next()) {
            list.add(new QuizDTO(rset.getString(1)));
         }
      } finally {
         DBUtil.close(con, pstmt, rset);
      }
      return list;
   }

   // 위에서 얻은 문제 이름을 추가로 받아와서 해당 문제의 코드를 return해 준다
   public static String getSolvedQuizCode(int userNumber, String quizName, String quizLevel) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String quiz = null;
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(
               "select QUIZ_CODE from SOLVED_QUIZ where USER_NUMBER = ? and QUIZ_NUMBER = (select QUIZ_NUMBER from QUIZ where QUIZ_NAME = ? and QUIZ_LEVEL = ?)");
         pstmt.setInt(1, userNumber);
         pstmt.setString(2, quizName);
         pstmt.setString(3, quizLevel);
         rset = pstmt.executeQuery();
         if (rset.next()) {

            quiz = "'" + rset.getString(1) + "'";
            System.out.println(quiz);
         }
      } finally {
         DBUtil.close(con, pstmt, rset);
      }
      return quiz;
   }

   // 유저 넘버, 퀴즈 레벨을 받아 해당 유저가 푼 문제 중 특정 레벨 문제 이름들만 리턴
   public static ArrayList<String> getMySolvedList(int userNumber, String quizLevel) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<String> list = null;
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(
               "select quiz_name from quiz where quiz_level = ? and quiz_number in (select quiz_number from solved_quiz where user_number = ?)");
         pstmt.setString(1, quizLevel);
         pstmt.setInt(2, userNumber);
         rset = pstmt.executeQuery();
         list = new ArrayList<String>();
         while (rset.next()) {
            list.add("'" + rset.getString(1) + "'");
         }
      } finally {
         DBUtil.close(con, pstmt, rset);
      }
      return list;
   }

   public static ArrayList<QuizUserDTO> getSolvedList(String quizName, String quizLevel) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      ArrayList<QuizUserDTO> list = null;
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(
               "SELECT user_id, user_name, user_grade from quiz_user where user_number in (SELECT user_number FROM solved_quiz WHERE quiz_number = (SELECT quiz_number FROM quiz WHERE quiz_level = ? and quiz_name = ?))");
         pstmt.setString(1, quizLevel);
         pstmt.setString(2, quizName);
         rset = pstmt.executeQuery();
         list = new ArrayList<QuizUserDTO>();
         while (rset.next()) {
            list.add(new QuizUserDTO(rset.getString(1), rset.getString(2), rset.getString(3)));
         }
      } finally {
         DBUtil.close(con, pstmt, rset);
      }
      return list;
   }
}