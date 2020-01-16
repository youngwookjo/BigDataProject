package naverStockNews.model.dto;

//

//user_id varchar2(20) NOT NULL,
//pw number(10) NOT NULL,
//stock_name varchar2(30),
//user_name varchar2(20) NOT NULL,
//phone_no number(10) NOT NULL,
//PRIMARY KEY (user_id)

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
	private String id;
	private int pw;
	private String stockName;
	private String name;
	private int phoneNO;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 아이디 = ");
		builder.append(id + "\t");
		builder.append("2. 비밀번호 : ");
		builder.append(pw + "\t");
		builder.append("3. 관심 종목 : ");
		builder.append(stockName + "\t");
		builder.append("4. 회원명 : ");
		builder.append(name + "\t");
		builder.append("5. 전화번호 : ");
		builder.append(phoneNO + "\t");
		return builder.toString();
	}

}
