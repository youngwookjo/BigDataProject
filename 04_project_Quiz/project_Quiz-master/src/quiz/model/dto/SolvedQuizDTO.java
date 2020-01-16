package quiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolvedQuizDTO {
	private int uNumber;
	private int qNumber;
	private String quizCode;
	private String quizSysdate;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SolvedQuizDTO [uNumber=");
		builder.append(uNumber);
		builder.append(", qNumber=");
		builder.append(qNumber);
		builder.append(", quizCode=");
		builder.append(quizCode);
		builder.append(", quizSysdate=");
		builder.append(quizSysdate);
		builder.append("]");
		return builder.toString();
	}

	public SolvedQuizDTO(String quizCode, String quizSysdate) {
		super();
		this.quizCode = quizCode;
		this.quizSysdate = quizSysdate;
	}
}

/* 
 * CREATE TABLE SOLVED_QUIZ
(
	USER_NUMBER number(2,0) NOT NULL,
	QUIZ_NUMBER number(4,0) NOT NULL,
	QUIZ_CODE long,
	QUIZ_SYSDATE varchar2(40) NOT NULL,
	CONSTRAINT COMPOSITE_KEY UNIQUE (USER_NUMBER, QUIZ_NUMBER)
);
 * */
