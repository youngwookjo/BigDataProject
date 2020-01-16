package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionFinal")
public class SessionView extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		out.println(session.getAttribute("id") + "<br>");
		out.println(session.getAttribute("name") + "<br>");
		out.println(session.getAttribute("searchResult") + "<br>");
		out.println("<button onclick='location.href=\"search.html\"'>검색화면</button>");
		out.println("<button onclick='location.href=\"end\"'>로그아웃</button>");
	}

}
