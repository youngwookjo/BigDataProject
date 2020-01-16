package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/check")
public class IdCheck extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("psw");
		if (id.equals("admin") && pw.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", "관리자");
			response.sendRedirect("search.html");
		} else {
			response.sendRedirect("fail");
		}

	}
}
