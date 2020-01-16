package naverStockNews.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NowNewsDTO {
	private String newsTitle;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(newsTitle + "\t");
		return builder.toString();
	}
}
