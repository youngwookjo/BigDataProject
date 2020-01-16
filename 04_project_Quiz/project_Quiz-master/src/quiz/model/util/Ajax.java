package quiz.model.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import quiz.model.SolvedQuizDAO;
import quiz.model.dto.QuizDTO;

@WebServlet("/ajax")
public class Ajax extends HttpServlet {
       
    public Ajax() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        SolvedQuizDAO dao = new SolvedQuizDAO();
        int userNumber = Integer.parseInt(request.getParameter("userNumber"));
        String quizLevel = request.getParameter("quizLevel");
     
        try {
     
        	ArrayList<QuizDTO> listCatagory = dao.getSolvedQuizNameList(userNumber, quizLevel);
            String json = new Gson().toJson(listCatagory);
     
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
     
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
