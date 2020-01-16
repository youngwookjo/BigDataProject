package quiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SysNameDTO {
   private String quizName;
   private String quizSysdate;
   
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("SysNameDTO [quizName=");
      builder.append(quizName);
      builder.append(", quizSysdate=");
      builder.append(quizSysdate);
      builder.append("]");
      return builder.toString();
   }
   
}