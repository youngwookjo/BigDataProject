package quiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
	private int quizNumber;
	private String quizName;
	private String quizLevel;		// table에서 number -> string으로 전환

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("퀴즈번호");
		builder.append(quizNumber);
		builder.append(", 퀴즈이름 =");
		builder.append(quizName);
		builder.append(", 퀴즈레벨 =");
		builder.append(quizLevel);
		builder.append("\n");
		return builder.toString();
	}


	public QuizDTO(String quizName) {
		this.quizName = quizName;
	}
	public QuizDTO(String quizName, String quizLevel) {
		this.quizName = quizName;
		this.quizLevel = quizLevel;
	}
	
	
}
