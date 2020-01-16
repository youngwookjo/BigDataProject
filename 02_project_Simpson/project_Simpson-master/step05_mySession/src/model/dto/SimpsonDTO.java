package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SimpsonDTO {
	private String name;
	private String born;
	private String like;
	private String sort;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("이름 : ");
		builder.append(name);
		builder.append(" / 태어난 날 : ");
		builder.append(born);
		builder.append(" / 좋아하는 것  :");
		builder.append(like);
		builder.append(" / 분류 : ");
		builder.append(sort);
		return builder.toString();
	}
}