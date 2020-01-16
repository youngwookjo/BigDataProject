package naverStockNews.controller;

import java.sql.SQLException;

import naverStockNews.exception.NotExistException;
import naverStockNews.model.NaverStockNewsService;
import naverStockNews.model.dto.NowNewsDTO;
import naverStockNews.model.dto.StockDTO;
import naverStockNews.model.dto.UserDTO;
import naverStockNews.view.EndView;
import naverStockNews.view.FailView;

public class NaverStockNewsController {
	private static NaverStockNewsController instance = new NaverStockNewsController();

	private NaverStockNewsController() {}

	public static NaverStockNewsController getInstance() {
		return instance;
	}

	private static NaverStockNewsService service = NaverStockNewsService.getInstance();

//		--		User CRUD		--	
//	1. 가입
	public static void addUser(UserDTO user) {
		try {
			service.addUser(user);
			EndView.successMessage("회원 가입에 성공했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	2. 검색 -all
	public static void getAllUsers() {
		try {
			EndView.successMessage("모든 회원 검색에 성공했습니다.");
			EndView.allUserView(service.getAllUsers());
		} catch (SQLException s) {
			s.printStackTrace();
			FailView.failMessageView("요청하신 모든 회원 검색");
		}
	}

//	3. 검색 -one, user_id로
	public static UserDTO getOneUser(String userId) {
		try {
			EndView.successMessage("요청하신 회원 검색에 성공했습니다.");
			EndView.oneUserView(service.getOneUser(userId));
		} catch (NotExistException e) {
			e.printStackTrace();
			FailView.failMessageView("요청하신 회원 검색 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	4. 수정	- user_id와 pw 입력할 시 새 pw로 변경
	public static void updateUserPW(int pwNew, String userId, int pwOriginal) {
		try {
			service.updateUser(pwNew, userId, pwOriginal);
			EndView.successMessage("회원정보 수정에 성공했습니다.");
		} catch (Exception s) {
			s.printStackTrace();
			FailView.failMessageView("회원정보 수정");
		}
	}

//	5. 수정	- user_id와 pw 입력할 시 user_name 변경
	public static void updateUserName(String userName, String userId, int pw) {
		try {
			service.updateUserName(userName, userId, pw);
			EndView.successMessage("회원정보 수정에 성공했습니다.");
		} catch (Exception s) {
			s.printStackTrace();
			FailView.failMessageView("회원정보 수정");
		}
	}

//	6. 수정	- user_id와 pw 입력할 시 phone_no 변경
	public static void updateUserPhoneNo(int phoneNO, String userId, int pw) {
		try {
			service.updateUserPhonNo(phoneNO, userId, pw);
			EndView.successMessage("회원정보 수정에 성공했습니다.");
		} catch (Exception s) {
			s.printStackTrace();
			FailView.failMessageView("회원정보 수정");
		}
	}

//	7. 수정	- user_id와 pw 입력할 시 관심 stock_name 바꾸기
	public static void updateUserOtherStockName(String stockName, String userId, int pw) {
		try {
			service.updateUserOtherStockName(stockName, userId, pw);
			EndView.successMessage("회원정보 수정에 성공했습니다.");
		} catch (Exception s) {
			s.printStackTrace();
			FailView.failMessageView("회원정보 수정");
		}
	}

//	8. 수정	- user_id와 pw 입력할 시 관심 stock_name 없애기
	public static void deleteUserStockName(String userId, int pw) {
		try {
			service.deleteUserStockName(userId, pw);
			EndView.successMessage("회원정보 수정에 성공했습니다.");
		} catch (Exception s) {
			s.printStackTrace();
			FailView.failMessageView("회원정보 수정");
		}
	}

//	9. 탈퇴	- user_id와 pw 입력할 시
	public static void deleteUser(String userId, int pw) {
		try {
			service.deleteUser(userId, pw);
			EndView.successMessage("회원 탈퇴에 성공했습니다.");
		} catch (Exception s) {
			s.printStackTrace();
			FailView.failMessageView("회원 탈퇴");
		}
	}

//		--		Stock CRUD		--	
// 	1. 추가
	public static void addStock(StockDTO stock) {
		try {
			EndView.successMessage("종목 추가에 성공했습니다.");
			service.addStock(stock);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 	2. 검색 -all
	public static void getAllstocks() {
		try {
			EndView.successMessage("모든 종목 검색에 성공했습니다.");
			EndView.allStockView(service.getAllStocks());
			;
		} catch (SQLException s) {
			s.printStackTrace();
			FailView.failMessageView("요청하신 모든 회원 검색");
		}
	}

// 	3. 종목 한개 검색
	public static StockDTO getOneStock(String stockName) {
		try {
			EndView.successMessage("요청하신 종목 검색에 성공했습니다.");
			EndView.oneStockView(service.getOneStock(stockName));
		} catch (NotExistException e) {
			e.printStackTrace();
			FailView.failMessageView("요청하신 종목 검색 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	4. 종목 이름으로 조회해서 삭제
	public static void deleteStock(String stockName) {
		try {
			service.deleteStock(stockName);
			EndView.successMessage("종목 삭제에 성공했습니다.");
		} catch (Exception s) {
			s.printStackTrace();
			FailView.failMessageView("종목 삭제");
		}
	}

//		--		NowNews CRUD		--	
//	1. 뉴스 추가
	public static void addNowNews(NowNewsDTO nownews) {
		try {
			service.addNowNews(nownews);
			EndView.successMessage("뉴스 추가에 성공했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	2. 모든 뉴스 검색
	public static void getAllNowNews() {
		try {
			EndView.successMessage("모든 뉴스 검색에 성공했습니다.");
			EndView.allNewsView(service.getAllNowNews());
		} catch (SQLException s) {
			s.printStackTrace();
			FailView.failMessageView("요청하신 모든 회원 검색");
		}
	}

//	3. 검색 문자열 포함 뉴스 검색
	public static NowNewsDTO getNowNews(String newsTarget) {
		try {
			EndView.successMessage("모든 뉴스 검색에 성공했습니다.");
			EndView.allNewsView(service.getNowNews(newsTarget));
		} catch (NotExistException e) {
			e.printStackTrace();
			FailView.failMessageView("요청하신 종목에 대한 뉴스 검색 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	4. 검색 문자열 관련 뉴스 삭제
	public static void deleteNowNews(String newsTarget) {
		try {
			service.deleteNownews(newsTarget);
			EndView.successMessage("뉴스 삭제에 성공했습니다.");
		} catch (Exception s) {
			s.printStackTrace();
			FailView.failMessageView("검색하신 종목과 관련한 뉴스 삭제");
		}
	}

//		--		맞춤서비스		--
	// 사용자가 지정한 관심 종목 관련 기사 서비스
	public static void giveInterestedStock(String userId) {
		try {
			EndView.successMessage("회원님이 검색하신 종목에 대한 기사입니다 >");
			EndView.allNewsView(service.giveInterestedStock(userId));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NotExistException e) {
			e.printStackTrace();
		}
	}

	// 오전장 [특징주], [실적속보], [공시] 전 회원 대상 문자 서비스
	public static void getFeaturedStock() {
		try {
			EndView.timeCheck(service.timeCheck());
			EndView.allUserView(service.getAllUsers());
			EndView.allNewsView(service.getFeaturedStock());
			EndView.allNewsView(service.getBizResultStock());
			EndView.allNewsView(service.getNoticeStock());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 장 [출발] 전 회원 대상 문자 서비스
	public static void getStartStock() {
		try {
			EndView.timeCheck(service.timeCheck());
			EndView.allUserView(service.getAllUsers());
			EndView.allNewsView(service.getStartStock());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
