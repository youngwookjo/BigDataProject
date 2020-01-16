package naverStockNews.model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import naverStockNews.exception.NotExistException;
import naverStockNews.model.dto.NowNewsDTO;
import naverStockNews.model.dto.StockDTO;
import naverStockNews.model.dto.UserDTO;

public class NaverStockNewsService {

	private static NaverStockNewsService instance = new NaverStockNewsService();

	private NaverStockNewsService() {
	}

	public static NaverStockNewsService getInstance() {
		return instance;
	}

//	--		User CRUD		--	
//	0. 존재하지 않는 예외 상황
	public static void notExistUser(String userId) throws NotExistException, SQLException {
		UserDTO oneUser = UserDAO.getOneUser(userId);
		if (oneUser == null) {
			throw new NotExistException("검색하신 사용자가 존재하지 않습니다.");
		}
	}

//	1. 가입
	public boolean addUser(UserDTO user) throws SQLException {
		return UserDAO.addUser(user);
	}

//	2. 검색 -all
	public ArrayList<UserDTO> getAllUsers() throws SQLException {
		return UserDAO.getAllUsers();
	}

//	3. 검색 -one, user_id로
	public UserDTO getOneUser(String userId) throws SQLException, NotExistException {
		notExistUser(userId);
		return UserDAO.getOneUser(userId);
	}

//	4. 수정	- user_id와 pw 입력할 시 새 pw로 변경
	public boolean updateUser(int pwNew, String userId, int pwOriginal) throws SQLException, NotExistException {
		notExistUser(userId);
		return UserDAO.updateUserPW(pwNew, userId, pwOriginal);
	}

//	5. 수정	- user_id와 pw 입력할 시 user_name 변경
	public boolean updateUserName(String name, String userId, int pw) throws SQLException, NotExistException {
		notExistUser(userId);
		return UserDAO.updateUserName(name, userId, pw);
	}

//	6. 수정	- user_id와 pw 입력할 시 phone_no 변경
	public boolean updateUserPhonNo(int phoneNO, String userId, int pw) throws SQLException, NotExistException {
		notExistUser(userId);
		return UserDAO.updateUserPhonNo(phoneNO, userId, pw);
	}

//	7. 수정	- user_id와 pw 입력할 시 관심 stock_name 바꾸기
	public boolean updateUserOtherStockName(String stockName, String userId, int pw)
			throws SQLException, NotExistException {
		notExistUser(userId);
		return UserDAO.updateUserOtherStockName(stockName, userId, pw);
	}

//	8. 수정	- user_id와 pw 입력할 시 관심 stock_name 없애기
	public boolean deleteUserStockName(String userId, int pw) throws SQLException, NotExistException {
		notExistUser(userId);
		return UserDAO.deleteUserStockName(userId, pw);
	}

//	9. 탈퇴	- user_id와 pw 입력할 시
	public boolean deleteUser(String userId, int pw) throws SQLException, NotExistException {
		notExistUser(userId);
		return UserDAO.deleteUser(userId, pw);
	}

//	--		Stock CRUD		--
//	0. 존재하지 않는 예외 상황
	public void notExistStock(String stockName) throws NotExistException, SQLException {
		StockDTO oneStock = StockDAO.getOneStock(stockName);
		if (oneStock == null) {
			throw new NotExistException("검색하신 종목이 존재하지 않습니다.");
		}
	}

//	 1. 추가
	public boolean addStock(StockDTO stock) throws SQLException {
		return StockDAO.addStock(stock);
	}

// 	2. 검색 -all
	public ArrayList<StockDTO> getAllStocks() throws SQLException {
		return StockDAO.getAllStocks();
	}

// 	3. 종목 한개 검색
	public StockDTO getOneStock(String stockName) throws SQLException, NotExistException {
		notExistStock(stockName);
		return StockDAO.getOneStock(stockName);
	}

//	4. 종목 이름으로 조회해서 삭제
	public boolean deleteStock(String stockName) throws SQLException, NotExistException {
		notExistStock(stockName);
		return StockDAO.deleteStock(stockName);
	}

//	--		NowNews CRUD		--
//	0. 존재하지 않는 예외 상황
	public static void notExistNowNews(String newsTarget) throws NotExistException, SQLException {
		ArrayList<NowNewsDTO> nowNews = NowNewsDAO.getNowNews(newsTarget);
		if (nowNews == null) {
			throw new NotExistException("검색하신 기사가 존재하지 않습니다.");
		}
	}

//	1. 뉴스 추가
	public boolean addNowNews(NowNewsDTO nowNews) throws SQLException {
		return NowNewsDAO.addNowNews(nowNews);
	}

//	2. 모든 뉴스 검색
	public ArrayList<NowNewsDTO> getAllNowNews() throws SQLException {
		return NowNewsDAO.getAllNews();
	}

//	3. 검색 문자열 포함 뉴스 검색
	public ArrayList<NowNewsDTO> getNowNews(String newsTarget) throws SQLException, NotExistException {
		notExistNowNews(newsTarget);
		return NowNewsDAO.getNowNews(newsTarget);
	}

//	4. 검색 문자열 관련 뉴스 삭제
	public boolean deleteNownews(String newsTarget) throws SQLException, NotExistException {
		notExistNowNews(newsTarget);
		return NowNewsDAO.deleteNowNews(newsTarget);
	}

//	--		맞춤서비스		--
//	0. 서비스 완료 시간
	public String timeCheck() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format.format(time);
		return time1;
	}

//	1. 사용자가 지정한 관심 종목 관련 기사 서비스
//	UserDTO id로 getStockName 해서 null일 경우 예외 처리, null 아닐 경우 그 문자열 포함한 기사만 검색해 출력 
	public ArrayList<NowNewsDTO> giveInterestedStock(String userId) throws SQLException, NotExistException {
		String v = UserDAO.getOneUser(userId).getStockName();
		if (v == "없음") {
			notExistNowNews(v);
		}
		return getNowNews(v);
	}

//	2. getfeaturedStock(String v): 오전장 자동 회원 대상 단체 문자 서비스
//	UserDTO 모든 phoneNo에게 StockDTO contentNowNews "[특징주]", "[실적속보]", "[공시]" 문자열 포함 검색 결과 출력 %
	// or UserDTO에서 InterestedStock 추가 시 StockDTO의stockOfInterest 추가
	public ArrayList<NowNewsDTO> getFeaturedStock() throws SQLException {
		return NowNewsDAO.getNowNews("[시황]");
	}
	public ArrayList<NowNewsDTO> getBizResultStock() throws SQLException {
		return NowNewsDAO.getNowNews("[실적속보]");
	}
	public ArrayList<NowNewsDTO> getNoticeStock() throws SQLException {
		return NowNewsDAO.getNowNews("[공시]");
	}

//	3. getStartInfo(String v)	: 9시 장 시작 직후 자동 회원 대상 단체 문자 서비스
//	DBUtil의 nowNewsTitle에서 이데일리 內 문자열 "[출발]" 포함한 검색 내역 회원에게 발송(가정)하는 메시지 출력 
	public ArrayList<NowNewsDTO> getStartStock() throws SQLException {
		return NowNewsDAO.getNowNews("[출발]");
	}


	
//	4. readyService(String v)	: 매일 오전 8:50 장 시작 전 자동 전 회원 대상 단체 문자 서비스
//	UserDTO 모든 phoneNo에게 contentNowNews 이데일리 문자열 "[재송]" 포함 검색 결과 출력

	



}
