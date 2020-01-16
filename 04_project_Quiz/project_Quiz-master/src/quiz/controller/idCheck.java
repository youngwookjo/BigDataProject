package quiz.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.model.QuizService;
import quiz.model.dto.QuizUserDTO;

@WebServlet("/check")
public class idCheck extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		try {
					
			if (userId.equals(QuizService.getUser(userId).getUserId()) && userPw.equals(QuizService.getUser(userId).getUserPw())) {
				request.setAttribute("userId", userId);
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				url = "about.jsp";
			
			} else {
				request.setAttribute("errorMsg", "비밀번호가 틀립니다");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
