package quiz.model;

import java.sql.SQLException;
import java.util.ArrayList;

import quiz.exception.MessageException;
import quiz.exception.NotExistException;
import quiz.model.dto.QuizDTO;
import quiz.model.dto.QuizUserDTO;
import quiz.model.dto.SolvedQuizDTO;
import quiz.model.dto.SysNameDTO;

public class QuizService {
   
   
   //User CRUD
   //회원 아이디로 user 모든 정보 검색
   public static QuizUserDTO getUser(String userId) throws SQLException {
      return QuizUserDAO.getUser(userId);
   }
      
   //새로운 user 저장
   public static boolean addUser(QuizUserDTO user) throws SQLException, MessageException{
      boolean result = false;
      try{
         result = QuizUserDAO.addUser(user);
      }catch(SQLException s){
         s.printStackTrace();
         throw new MessageException("이미 존재하는 유저입니다 다시 시도 하세요");
      }
      return result;
   }
      
   // user 수정
   public static boolean updateUser(String userId, String oldPw, String newPw) throws SQLException {
      return QuizUserDAO.updateUser(userId, oldPw, newPw);
   }
   //user 삭제
   public static boolean deleteUser(String userId, String userPw) throws SQLException {
      return QuizUserDAO.deleteUser(userId, userPw);
   }
   
   //Quiz CRUD
   //퀴즈 이름 넣어서 퀴즈 정보 받아옴 단, (소수찾기 등 복수일 확률 있어서 ArrayList로 받아옴)
   public static ArrayList<QuizDTO> getQuiz (String quizName) throws SQLException {
      return QuizDAO.getQuiz(quizName);
   }
   
   //퀴즈 레벨받아서 그 퀴즈레벨에 해당하는 퀴즈 네임 ArrayList로 받아옴
   public static ArrayList<String> getLevelQuiz (String quizLevel) throws SQLException {
      return QuizDAO.getLevelQuiz(quizLevel);
   }
   
   //모든 퀴즈 검색
   public static ArrayList<QuizDTO> getAllQuiz() throws SQLException{
      return QuizDAO.getAllQuiz();
   }

   
   //SolvedQuiz CRUD
   
   //Solved Quiz add
   public static boolean addSolvedQuiz(int userNumber, String quizName, String quizLevel, String quizCode) throws SQLException, MessageException{
      boolean result = false;
      try{
         System.out.println("sevice에서 메소드 호출 중");
         result = SolvedQuizDAO.addSolvedQuiz(userNumber, quizName, quizLevel, quizCode);
      }catch(SQLException s){
         s.printStackTrace();
         throw new MessageException("이미 해결한 퀴즈 입니다");
      }
      return result;
   }
   //Solved Quiz update
   public static boolean updateSolvedQuiz(int userNumber, String quizName, String quizLevel , String quizCode) throws SQLException, NotExistException{
      boolean result = SolvedQuizDAO.updateSolvedQuiz(userNumber, quizName, quizLevel, quizCode);
      if(!result){
         throw new NotExistException("코드 갱신 실패했습니다");
      }
      return result;
   }
   //Solved Quiz delete
   public static boolean deleteSolvedQuiz(int userNumber, String quizName, String quizLevel) throws SQLException {
      return SolvedQuizDAO.deleteSolvedQuiz(userNumber, quizName, quizLevel);
   }
   
   //Solved Quiz read 내가가진 푼 퀴즈 전체 읽기 (code 제외)
   
   public static ArrayList<SysNameDTO> getSolvedQuizList(int userNumber) throws SQLException {
      return SolvedQuizDAO.getSolvedQuizList(userNumber);
   }
   
   
   // Solved Quiz read user_number, quiz_level 받아서 해당하는  퀴즈 이름 받아오기
   public static ArrayList<QuizDTO> getSolvedQuizNameList(int userNumber, String quizLevel) throws SQLException {
      return SolvedQuizDAO.getSolvedQuizNameList(userNumber, quizLevel);
   }
   
   // Solved Quiz code 반환
   public static String getSolvedQuizCode(int userNumber, String quizName, String quizLevel) throws SQLException {
      return SolvedQuizDAO.getSolvedQuizCode(userNumber, quizName, quizLevel);
   }
   
   // quiz_level, quiz_name을 받아 해당 퀴즈를 푼 유저들 리스트 받아오기
   public static String getSolvedList(String quizName, String quizLevel) throws SQLException {
      String answer = "[['', '친구 이름' , '친구 등급']";
      ArrayList<QuizUserDTO> list = SolvedQuizDAO.getSolvedList(quizName, quizLevel);
      for(int i = 0; i < list.size(); i++) {
         answer += ",['" + list.get(i).getUserId() + "', '" + list.get(i).getUserName() + "', '" + list.get(i).getUserGrade() + "']";
      }
      answer += "]";
      return answer;
   }
   
   public static ArrayList<String> getMySolvedList(int userNumber, String quizLevel) throws SQLException {
      return SolvedQuizDAO.getMySolvedList(userNumber, quizLevel);
   }
   
   
   
   
//   public static void main(String[] args) throws MessageException, NotExistException {
//      
//            try {
////                     addSolvedQuiz(1, "사칙연산", "Lv.4", "aas");
//    addSolvedQuiz(1, "도둑질", "Lv.4", "aasb");
//    addSolvedQuiz(1, "최댓값과 최솟값", "Lv.2", "aadwq");
//    addSolvedQuiz(1, "[3차] n진수 게임", "Lv.5", "aadwq");
//    addSolvedQuiz(1, "피보나치 수", "Lv.2", "awedxw");
//    addSolvedQuiz(1, "[3차] 자동완성" , "Lv.5", "qwesxd");
//    addSolvedQuiz(1, "숫자 블록", "Lv.4", "qwexdas");
//    addSolvedQuiz(1, "올바른 괄호의 갯수", "Lv.4", "qwexdcvf");
//    addSolvedQuiz(1, "폰켓몬", "Lv.2", "asqwexda");
//    addSolvedQuiz(1, "소수 찾기", "Lv.1", "qwedsgr");
//    addSolvedQuiz(1, "H-Index", "Lv.2", "dqwesxzd");
//    addSolvedQuiz(1, "GPS", "Lv.4", "dqwesxzd");
//    addSolvedQuiz(1, "튜브의 소개팅", "Lv.4", "dqwesxzd");
//    addSolvedQuiz(1, "스마트한 프로도", "Lv.4", "dqwesxzd");
//addSolvedQuiz(1, "x만큼 간격이 있는 n개의 숫자", "Lv.1", "dqwesxzd22");
//addSolvedQuiz(1, "직사각형 별찍기", "Lv.1", "dqwesxzd222");
//    addSolvedQuiz(2, "사칙연산", "Lv.4", "aas");
//    addSolvedQuiz(2, "도둑질", "Lv.4", "aasb");
//    addSolvedQuiz(2, "최댓값과 최솟값", "Lv.2", "aadwq");
//    addSolvedQuiz(2, "[3차] n진수 게임", "Lv.5", "aadwq");
//    addSolvedQuiz(2, "탑", "Lv.2", "qwesadxczs");
//    addSolvedQuiz(2, "JadenCase 문자열 만들기", "Lv.2", "qwexasd");
//    addSolvedQuiz(2, "행렬의 곱셈" ,"Lv.2", "qwesaxzc");
//    addSolvedQuiz(2, "수박수박수박수박수박수?", "Lv.1", "qwexcdf");
//    addSolvedQuiz(2, "시저 암호", "Lv.1", "qwexcdf");
//    addSolvedQuiz(2, "약수의 합", "Lv.1", "qwexcdf");
//    addSolvedQuiz(2, "이상한 문자 만들기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(2, "자릿수 더하기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(3, "[3차] 자동완성" , "Lv.5", "qwesxd");
//    addSolvedQuiz(3, "숫자 블록", "Lv.4", "qwexdas");
//    addSolvedQuiz(3, "수박수박수박수박수박수?", "Lv.1", "qwexcdf");
//    addSolvedQuiz(3, "시저 암호", "Lv.1", "qwexcdf");
//    addSolvedQuiz(3, "약수의 합", "Lv.1", "qwexcdf");
//    addSolvedQuiz(3, "이상한 문자 만들기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(3, "자릿수 더하기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "수박수박수박수박수박수?", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "시저 암호", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "약수의 합", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "이상한 문자 만들기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "자릿수 더하기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "문자열 다루기 기본", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "문자열 내 p와 y의 개수", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "문자열 내 마음대로 정렬하기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "두 정수 사이의 합", "Lv.1", "qwexcdf");
//    addSolvedQuiz(4, "행렬의 곱셈" ,"Lv.2", "qwesaxzc");
//    addSolvedQuiz(5 , "수박수박수박수박수박수?", "Lv.1", "qwexcdf");
//    addSolvedQuiz(5, "시저 암호", "Lv.1", "qwexcdf");
//    addSolvedQuiz(5, "약수의 합", "Lv.1", "qwexcdf");
//    addSolvedQuiz(5, "이상한 문자 만들기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(5, "자릿수 더하기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(5, "문자열 다루기 기본", "Lv.1", "qwexcdf");
//    addSolvedQuiz(5, "문자열 내 p와 y의 개수", "Lv.1", "qwexcdf");
//    addSolvedQuiz(5, "문자열 내 마음대로 정렬하기", "Lv.1", "qwexcdf");
//    addSolvedQuiz(5, "두 정수 사이의 합", "Lv.1", "qwexcdf");
//addSolvedQuiz(5, "행렬의 곱셈" ,"Lv.2", "qwesaxzc");
//addSolvedQuiz(5, "최댓값과 최솟값", "Lv.2", "aadwq");
//addSolvedQuiz(5, "무지의 먹방 라이브", "Lv.5", "dqwesxzd3");
//addSolvedQuiz(5, "[3차] n진수 게임", "Lv.5", "aadwq");
//addSolvedQuiz(5, "[3차] 자동완성" , "Lv.5", "qwesxd");
//addSolvedQuiz(5, "블록 게임", "Lv.5", "dqwesxzd3");
//addSolvedQuiz(5, "[3차] 방금그곡", "Lv.5", "dqwesxzd3");
//addSolvedQuiz(5, "매칭 점수", "Lv.5", "dqwesxzd3");
//addSolvedQuiz(5, "오픈채팅방", "Lv.5", "dqwesxzd3");;
//            } catch (SQLException e) {
//               e.printStackTrace();
//            }
//   }
   
}