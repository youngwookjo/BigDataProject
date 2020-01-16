package naverStockNews.view;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

import naverStockNews.controller.NaverStockNewsController;
import naverStockNews.model.dto.NowNewsDTO;
import naverStockNews.model.dto.StockDTO;
import naverStockNews.model.dto.UserDTO;

public class StartView {

	public static void main(String[] args) {

//// 		--		DB 구축		--
//		//1) 실시간속보 크롤링, DB 구축하기
//		DBCrawler.crawl1();
//		//2) 종목 정보 크롤링, DB 구축하기	
//		try {
//			for (int i = 0; i < 2; i++) {
//				DBCrawler.crawl(i);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		System.out.println("--		User CRUD		--");
//		--		User CRUD		--	
//		1. 가입		
		NaverStockNewsController.addUser(new UserDTO("suk1206", 991023, "신라젠", "석유진", 1044493691));
		NaverStockNewsController.getOneUser("suk1206");
		System.out.println();

//		2. 검색 -all
		NaverStockNewsController.getAllUsers();
		System.out.println();

//		3. 검색 -one, user_id로
		NaverStockNewsController.getOneUser("yyy2410");
		System.out.println();

//		4. 수정	- user_id와 pw 입력할 시 새 pw로 변경
		NaverStockNewsController.updateUserPW(991023, "yyy2410", 241023);
		NaverStockNewsController.getOneUser("yyy2410");
		System.out.println();

//		5. 수정	- user_id와 pw 입력할 시 user_name 변경
		NaverStockNewsController.updateUserName("주영욱", "yyy2410", 991023);
		NaverStockNewsController.getOneUser("yyy2410");
		System.out.println();

//		6. 수정	- user_id와 pw 입력할 시 phone_no 변경
		NaverStockNewsController.updateUserPhoneNo(1074333693, "yyy2410", 991023);
		NaverStockNewsController.getOneUser("yyy2410");
		System.out.println();

//		7. 수정	- user_id와 pw 입력할 시 관심 stock_name 바꾸기
		NaverStockNewsController.updateUserOtherStockName("LG생활건강", "yyy2410", 991023);
		NaverStockNewsController.getOneUser("yyy2410");
		System.out.println();

//		8. 수정	- user_id와 pw 입력할 시 관심 stock_name 없애기
		NaverStockNewsController.deleteUserStockName("abc2864", 335423);
		NaverStockNewsController.getOneUser("abc2864");
		System.out.println();

//		9. 탈퇴	- user_id와 pw 입력할 시
		NaverStockNewsController.deleteUser("abc2864", 335423);
		System.out.println();

		System.out.println("--		Stock CRUD		--");
//			--		Stock CRUD		--	
//	 	1. 추가
		NaverStockNewsController.addStock(new StockDTO("우리차", 1100, "상승", 100, "+10%", 1000));
		NaverStockNewsController.getOneStock("우리차");
		System.out.println();

//	 	2. 검색 -all
		NaverStockNewsController.getAllstocks();
		System.out.println();

//	 	3. 종목 한개 검색
		NaverStockNewsController.getOneStock("삼성전자");
		System.out.println();

//		4. 종목 이름으로 조회해서 삭제
		NaverStockNewsController.deleteStock("현대차");
		System.out.println();

		System.out.println("--		NowNews CRUD		--");
//			--		NowNews CRUD		--	
//		1. 뉴스 추가
		NaverStockNewsController.addNowNews(new NowNewsDTO("SK하이닉스가 세계 D램 시장에서 선두를 달리고 싶다."));
		System.out.println();

//		2. 모든 뉴스 검색
		NaverStockNewsController.getAllNowNews();
		System.out.println();

//		3. 검색 문자열 포함 뉴스 검색
		NaverStockNewsController.getNowNews("코스피");
		System.out.println();

//		4. 검색 문자열 관련 뉴스 삭제
		NaverStockNewsController.deleteNowNews("반등");
		System.out.println();

		System.out.println("--		맞춤서비스		--");
//		--		맞춤서비스		--		
		System.out.println("사용자가 지정한 관심 종목 관련 기사 서비스");
		NaverStockNewsController.giveInterestedStock("suk1206");
		System.out.println();

//		Timer t = new Timer();
//		GregorianCalendar cal = new GregorianCalendar();
//		
//		cal.set(2019, Calendar.AUGUST, 02, 9, 02);
//		long EveryDay = 24*60*60*1000;
//		t.scheduleAtFixedRate(new TimeWork(), cal.getTime(), EveryDay);

	}

}