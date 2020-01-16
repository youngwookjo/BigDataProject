package quiz.view;

import java.io.IOException;
import java.sql.SQLException;

import quiz.crawler.QuizCrawler;
import quiz.model.QuizDAO;

public class test {

	public static void main(String[] args) throws SQLException, IOException {
		for(int i = 1; i<6; i++) {
			QuizDAO.addAllQuizzes("Lv."+String.valueOf(i),QuizCrawler.crawlQuiz(i));
		}
	}
}
