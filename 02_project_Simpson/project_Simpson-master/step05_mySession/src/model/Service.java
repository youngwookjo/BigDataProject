package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.SimpsonDAO;

@WebServlet("/service")
public class Service extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		String search = request.getParameter("search");

		if (SimpsonDAO.getResearch(search).size() != 0) {
			session.setAttribute("searchResult", SimpsonDAO.getResearch(search));
			response.sendRedirect("sessionFinal");
		} else {
			response.sendRedirect("failResearch");
		}

	}

}
