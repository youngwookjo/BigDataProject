package naverStockNews.model.dto;

//stock_name varchar2(30) NOT NULL,
//price number(7,0) NOT NULL,
//net number(6) NOT NULL,
//updown number(4,2) NOT NULL,
//marketcap number(7,0) NOT NULL,
//PRIMARY KEY (stock_name)

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StockDTO {
	private String stockName;
	private int price;
	private String netUpDown;
	private int net;
	private String upDown;
	private int marketCap;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 종목명 = ");
		builder.append(stockName + "\t");
		builder.append("2. 현재가 : ");
		builder.append(price + "\t");
		builder.append("3. 상승/하락 : ");
		builder.append(netUpDown + "\t");
		builder.append("4. 전일비 : ");
		builder.append(net + "\t");
		builder.append("5. 등락률 : ");
		builder.append(upDown + "\t");
		builder.append("6. 시가총액 : ");
		builder.append(marketCap + "\t");
		return builder.toString();
	}

}
