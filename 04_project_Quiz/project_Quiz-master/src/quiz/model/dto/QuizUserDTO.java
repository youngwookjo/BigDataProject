package quiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizUserDTO {
	private int userNumber;
	private String userId;
	private String userPw;
	private String userName;
	private String userGrade;
	private String userQuizCount;
	private String userTotalPoint;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuizUserDTO [userNumber=");
		builder.append(userNumber);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userPw=");
		builder.append(userPw);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userGrade=");
		builder.append(userGrade);
		builder.append(", userQuizCount=");
		builder.append(userQuizCount);
		builder.append(", userTotalPoint=");
		builder.append(userTotalPoint);
		builder.append("]");
		return builder.toString();
	}

	
	
//	public QuizUserDTO(String userId, String userPw, String userName) {
//		super();
//		this.userId = userId;
//		this.userPw = userPw;
//		this.userName = userName;
//	}

	public QuizUserDTO(String userId, String userPw, String userName, String userGrade, String userQuizCount,
			String userTotalPoint) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userGrade = userGrade;
		this.userQuizCount = userQuizCount;
		this.userTotalPoint = userTotalPoint;
	}


	public QuizUserDTO(String userId, String userName, String userGrade) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userGrade = userGrade;
	}


}