package naverStockNews.view;

import java.util.ArrayList;

import naverStockNews.model.dto.NowNewsDTO;
import naverStockNews.model.dto.StockDTO;
import naverStockNews.model.dto.UserDTO;

public class EndView {

	// 요청 수행 성공 메시지 출력
	public static void successMessage(String message) {
		System.out.println(message);
	}

	// 모두 검색
	public static void allUserView(ArrayList<UserDTO> user){
		int length = user.size();
		if( length != 0 ){
			for(int index = 0; index < length; index++){	
				System.out.println("회원 목록 " + (index+1) + " - " + user.get(index));
			}
		}
	}
	public static void allStockView(ArrayList<StockDTO> stock){
		int length = stock.size();
		if( length != 0 ){
			for(int index = 0; index < length; index++){	
				System.out.println("종목 목록 " + (index+1) + " - " + stock.get(index));
			}
		}
	}
	public static void allNewsView(ArrayList<NowNewsDTO> news){
		int length = news.size();
		if( length != 0 ){
			for(int index = 0; index < length; index++){
				System.out.println("기사 목록 " + (index+1) + " - " + news.get(index));
			}
		}
	}	
	
	//개별 검색
	public static void oneUserView(UserDTO user) {
		System.out.println(user);	
	}
	public static void oneStockView(StockDTO stock) {
		System.out.println(stock);
	}
	public static void oneNewsView(NowNewsDTO news) {
		System.out.println(news);
	}
	
	// 서비스 완료 시각
	public static void timeCheck(String time) {
		System.out.println(time + " 모든 회원에게 문자 발송 서비스를 완료했습니다");
	}
	
}
