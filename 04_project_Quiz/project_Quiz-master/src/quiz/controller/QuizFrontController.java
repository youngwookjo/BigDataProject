package quiz.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quiz.model.QuizService;
import quiz.model.dto.QuizDTO;
import quiz.model.dto.QuizUserDTO;

@WebServlet("/quizFrontController")
public class QuizFrontController extends HttpServlet {

   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      request.setCharacterEncoding("UTF-8");

      // command pattern
      String command = request.getParameter("command");
      System.out.println(0 + " " + command);

      try {
         if (command.equals("getUser")) {// 유저 정보 반환
            getUser(request, response);
         } else if (command.equals("userInsert")) {// 유저추가
            userInsert(request, response);
         } else if (command.equals("userUpdate")) {// 유저 업데이트
            userUpdate(request, response);
         } else if (command.equals("userDelete")) {// 유저 삭제
            userDelete(request, response);
         } else if (command.equals("getQuiz")) {// 퀴즈 정보 읽기
            getQuiz(request, response);
         } else if (command.equals("getLevelQuiz")) {// 해당레벨 퀴즈 검색
            getLevelQuiz(request, response);
         } else if (command.equals("getSolvedList")) {// 퀴즈 레벨, 이름 기반 해당 문제 푼 유저 검색
            getSolvedList(request, response);
         } else if (command.equals("getMySolvedList")) {   // 해당 레벨 문제 중 내가 푼 문제들만 검색
            getMySolvedList(request, response);
         }
         else if (command.equals("getAllQuiz")) {// 모든 퀴즈 반환
            getAllQuiz(request, response);
         } else if (command.equals("insertSolvedQuiz")) {// 해결한 문제 추가
            insertSolvedQuiz(request, response);
         } else if (command.equals("updateSolvedQuiz")) {// 해결한 문제 수정
            updateSolvedQuiz(request, response);
         } else if (command.equals("solveQuizDelete")) {// 해결한 문제 삭제
            solveQuizDelete(request, response);
         } else if (command.equals("getSolvedQuizList")) {// 해결한 문제 리스트
            getSolvedQuizList(request, response);
         } else if (command.equals("getSolvedQuizNameList")) {// 해결한 문제 이름 리스트
            getSolvedQuizNameList(request, response);
         } else if (command.equals("getSolvedQuizCode")) {// 해결한 문제 코드
            getSolvedQuizCode(request, response);
         }
      } catch (Exception s) {
         request.setAttribute("errorMsg", s.getMessage());
         request.getRequestDispatcher("showError.jsp").forward(request, response);
         s.printStackTrace();
      }
   }

   // 메소드 구
   // 회원 아이디로 user 모든 정보 검색 메소드
   public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String url = "showError.jsp";

      String userId = request.getParameter("userId");
      try {
         // 세션 저장 부분
         request.setAttribute("user", QuizService.getUser(userId));
         url = "mystate.jsp";
      } catch (Exception s) {
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // 회원 가입 메소드

   protected void userInsert(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String url = "showError.jsp";

      String userId = request.getParameter("userId");
      String userPw = request.getParameter("userPw");
      String userName = request.getParameter("userName");

      if (userId == null || userId.trim().length() == 0 || userPw == null || userPw.trim().length() == 0
            || userName == null || userName.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }

      QuizUserDTO user = new QuizUserDTO(userId, userPw, userName);

      try {
         boolean result = QuizService.addUser(user);
         if (result) {
            request.setAttribute("user", user);
            request.setAttribute("successMsg", "가입이 성공적으로 완료되었습니다");
            url = "successPage.jsp";// 향후 만들어야 함
         } else {
            request.setAttribute("errorMsg", "다시 시도하세요");
         }
      } catch (Exception s) {
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // 회원 수정 메소드
   public void userUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userId = request.getParameter("userId");
      String oldPw = request.getParameter("oldPw");
      String newPw = request.getParameter("newPw");
      String url = "showError.jsp";
      if (userId == null || userId.trim().length() == 0 || oldPw == null || oldPw.trim().length() == 0
            || newPw == null || newPw.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }

      boolean result = false;
      try {
         result = QuizService.updateUser(userId, oldPw, newPw);
         if (result) {
            request.setAttribute("user", QuizService.getUser(userId));
            url = "about.html";
         } else {
            request.setAttribute("errorMsg", "수정에 실패하였습니다");
         }
      } catch (Exception s) {
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // 삭제메소드
   public void userDelete(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String userId = request.getParameter("userId");
      String userPw = request.getParameter("userPw");
      String url = "showError.jsp";
      if (userId == null || userId.trim().length() == 0 || userPw == null || userPw.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }
      boolean result = false;
      try {
         result = QuizService.deleteUser(userId, userPw);
         if (result) {
            url = "about.jsp";
         }
      } catch (Exception s) {
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // 퀴즈 서비스
   // 퀴즈 검색
   public void getQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String url = "showError.jsp";

      String quizName = request.getParameter("quizName");
      if (quizName == null || quizName.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }
      try {
         request.setAttribute("quizList", QuizService.getQuiz(quizName));
         url = "myPage.jsp";
      } catch (Exception s) {
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // 퀴즈 레벨 이름 검색
   public void getLevelQuiz(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String url = "showError.jsp";
      String level = request.getParameter("levelValue");
      System.out.println(level);

      if (level == null || level.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }
      try {
         request.setAttribute("quizNameList", QuizService.getLevelQuiz(level));
         url = "dataView.jsp"; // 내 페이지 혹은 다른 친구 검색 구분법 만들기
      } catch (Exception s) {
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // getSolverList(String quizName, String quizLevel)
   public void getSolvedList(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, SQLException {
      String url = "showError.jsp";
      String levelValue = request.getParameter("levelValue");
      String nameValue = request.getParameter("nameValue");
      System.out.println(levelValue + " " + nameValue + "ddddddddddddddddddddddddd");
      if (levelValue == null || levelValue.trim().length() == 0 || nameValue == null
            || nameValue.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }
      try {
         request.setAttribute("quizSolvedList", QuizService.getSolvedList(nameValue, levelValue));
         url = "dataVieww.jsp";
      } catch (SQLException s) {
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // 모든 퀴즈 검색
   public void getAllQuiz(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String url = "showError.jsp";

      try {
         request.setAttribute("QuizListAll", QuizService.getAllQuiz());
         url = "activistList.jsp";
      } catch (Exception s) {
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // 해결한 퀴즈 검색
   // Solved 퀴즈 추가
   protected void insertSolvedQuiz(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, SQLException {
      String url = "showError.jsp";

      int userNumber = QuizService.getUser(request.getParameter("userIdValue")).getUserNumber();
      String levelValue = request.getParameter("levelValue");
      String nameValue = request.getParameter("nameValue");
      String quizCode = request.getParameter("quizCode");//

      if (nameValue == null || nameValue.trim().length() == 0 || levelValue == null || levelValue.trim().length() == 0
            || quizCode == null || quizCode.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }

      try {
         boolean result = QuizService.addSolvedQuiz(userNumber, nameValue, levelValue, quizCode);
         if (result) {
            request.setAttribute("successMsg", "코드 추가 완료 했습니다");
            url = "about.jsp";
         } else {
            request.setAttribute("errorMsg", "다시 시도하세요");
         }
      } catch (Exception s) {
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // Solved 퀴즈 업데이트
   protected void updateSolvedQuiz(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, SQLException {
      String url = "showError.jsp";

      int userNumber = QuizService.getUser(request.getParameter("userId")).getUserNumber();
      String quizName = request.getParameter("quizName");
      String quizLevel = request.getParameter("quizLevel");
      String quizCode = request.getParameter("quizCode");

      if (quizName == null || quizName.trim().length() == 0 || quizLevel == null || quizLevel.trim().length() == 0
            || quizCode == null || quizCode.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }

      try {
         boolean result = QuizService.updateSolvedQuiz(userNumber, quizName, quizLevel, quizCode);
         if (result) {
            request.setAttribute("successMsg", "코드 추가 완료 했습니다");
            url = "about.jsp";// 향후 만들어야 함
         } else {
            request.setAttribute("errorMsg", "다시 시도하세요");
         }
      } catch (Exception s) {
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // solved quiz delete
   public void solveQuizDelete(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, SQLException {
      String url = "showError.jsp";

      int userNumber = QuizService.getUser(request.getParameter("userIdValue")).getUserNumber();
      String levelValue = request.getParameter("levelValue");
      String nameValue = request.getParameter("nameValue");

      if (nameValue == null || nameValue.trim().length() == 0 || levelValue == null || levelValue.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }

      boolean result = false;
      try {
         result = QuizService.deleteSolvedQuiz(userNumber, nameValue, levelValue);
         if (result) {
            url = "mycodedelete.jsp";
         }
      } catch (SQLException s) {
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // getSolvedQuizList
   public void getSolvedQuizList(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, SQLException {
      String url = "showError.jsp";
      int userNumber = QuizService.getUser(request.getParameter("userId")).getUserNumber();

      try {
         request.setAttribute("solvedQuizListAll", QuizService.getSolvedQuizList(userNumber));
         url = "activistList.jsp";
      } catch (SQLException s) {
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // solved Quiz Name List
   public void getSolvedQuizNameList(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, SQLException {
      String url = "showError.jsp";
      int userNumber = QuizService.getUser(request.getParameter("userId")).getUserNumber();
      String quizLevel = request.getParameter("quizLevel");
      if (quizLevel == null || quizLevel.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }
      try {
         request.setAttribute("solvedQuizNameList", QuizService.getSolvedQuizNameList(userNumber, quizLevel));
         url = "activistList.jsp";
      } catch (SQLException s) {
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }

   // solvedQuizCode
   public void getSolvedQuizCode(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, SQLException {
      String url = "showError.jsp";
      int userNumber = QuizService.getUser(request.getParameter("userIdValue")).getUserNumber();
      String levelValue = request.getParameter("levelValue");
      String nameValue = request.getParameter("nameValue");
      if (nameValue == null || nameValue.trim().length() == 0 || levelValue == null
            || levelValue.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }
      try {
         request.setAttribute("solvedQuizCode", QuizService.getSolvedQuizCode(userNumber, nameValue, levelValue));
         url = "dataViewww.jsp";
      } catch (SQLException s) {
         System.out.println("여기에러");
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }
   
   public void getMySolvedList(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, SQLException {
      String url = "showError.jsp";
      int userNumber = QuizService.getUser(request.getParameter("userIdValue")).getUserNumber();
      String levelValue = request.getParameter("levelValue");
      if (levelValue == null || levelValue.trim().length() == 0) {
         request.setAttribute("errorMsg", "값이 비어져 있습니다");
         response.sendRedirect(url);
         return;
      }
      try {
         request.setAttribute("quizMySolvedList", QuizService.getMySolvedList(userNumber, levelValue));
         url = "myDataView.jsp";
      } catch (SQLException s) {
         System.out.println("여기에러");
         s.printStackTrace();
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);      
   }
}