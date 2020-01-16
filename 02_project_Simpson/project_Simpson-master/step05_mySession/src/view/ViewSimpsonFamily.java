package view;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.SimpsonDAO;
import model.dto.SimpsonDTO;

@WebServlet("/family")
public class ViewSimpsonFamily extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for(SimpsonDTO s : SimpsonDAO.getFamily()) {
			out.println(s.toString() + "<br>");
		}
		out.println("<button onclick='location.href=\"search.html\"'>검색 화면</button>");
		out.println("<button onclick='location.href=\"end\"'>로그아웃</button>");
	}

}
