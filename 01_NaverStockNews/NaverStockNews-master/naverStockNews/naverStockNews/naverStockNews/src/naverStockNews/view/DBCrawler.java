package naverStockNews.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import naverStockNews.model.NowNewsDAO;
import naverStockNews.model.StockDAO;
import naverStockNews.model.dto.NowNewsDTO;
import naverStockNews.model.dto.StockDTO;

public class DBCrawler {

//1) 실시간속보 크롤링, DB 구축하기	-NowNewsDAO

	
	public static void crawl1() {
		
		File file = new File("NowNews.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintStream ps = new PrintStream(fos);
		System.setOut(System.out);
		System.setErr(ps);
		
		
		Document nowDoc = null;
		try {
			// 금융 실시간 속보 페이지(a page -home) // YYYYMMDD 그제 일자 입력
			nowDoc = Jsoup.connect(
					"https://finance.naver.com/news/news_list.nhn?mode=LSS2D&section_id=101&section_id2=258&date=20190731")
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 오늘 실시간속보
		Elements v = nowDoc.select("#contentarea_left > div.pagenavi_day > a");
		String TodayLink = v.get(0).attr("href");
		Document TodayDoc = null;
		try {
			TodayDoc = Jsoup.connect("https://finance.naver.com" + TodayLink).get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 5pages
		Elements tpg = TodayDoc.select("#contentarea_left > table > tbody > tr > td > table > tbody > tr > td > a");
		for (int t = 0; t < 5; t++) {

			String linkTodaylink = tpg.get(t).attr("href");
			Elements todaySubjects;
			try {
				todaySubjects = Jsoup.connect("https://finance.naver.com" + linkTodaylink).get()
						.getElementsByClass("articleSubject").toggleClass("articleSubject");
//				System.out.println(todaySubjects.size());
				for (Element ts : todaySubjects) {
					System.out.println(ts.text());
//					System.err.println(ts.text());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					try {
						NowNewsDAO.addNowNews(new NowNewsDTO(ts.text()));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

////		// 어제 실시간속보	(용량 문제로 주석 처리 보관)
//	public static void crawl2() {
//		Document nowDoc = null;
//		try {
//			// 금융 실시간 속보 페이지(a page -home) // YYYYMMDD 그제 일자 입력
//			nowDoc = Jsoup.connect(
//					"https://finance.naver.com/news/news_list.nhn?mode=LSS2D&section_id=101&section_id2=258&date=20190728")
//					.get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		// 오늘 실시간속보
//		Elements v = nowDoc.select("#contentarea_left > div.pagenavi_day > a");
//		String YesterdayLink = v.get(1).attr("href");
//		Document YesterdayDoc = null;
//		try {
//			YesterdayDoc = Jsoup.connect("https://finance.naver.com" + YesterdayLink).get();
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
//		Elements ypg = YesterdayDoc.select("#contentarea_left > table > tbody > tr > td > table > tbody > tr > td > a");
//		for (int y = 0; y < 5; y++) {
//			String linkYesterdaylink = ypg.get(y).attr("href");
//			// 5pages
//			Elements yesterdaySubjects;
//			try {
//				yesterdaySubjects = Jsoup.connect("https://finance.naver.com" + linkYesterdaylink).get()
//						.getElementsByClass("articleSubject").toggleClass("articleSubject");
////			System.out.println(yesterdaySubjects.size());
//				for (Element ys : yesterdaySubjects) {
//					System.out.println(ys.text());
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//					}
//					try {
//						NowNewsDAO.addNowNews(new NowNewsDTO(yesterdaySubjects.text()));
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//	}

//2) 종목 정보 크롤링, DB 구축하기	-StockDAO 
	public static void crawl(int sosok) throws IOException {
		
			
		File file = new File("StockInfo.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintStream ps = new PrintStream(fos);
		System.setOut(System.out);
		System.setErr(ps);
		
		HashMap<String, String> map = new HashMap<>();
//	      ArrayList stockDataList = new ArrayList<>();
		ArrayList<StockDTO> stockList = new ArrayList<>();
//	      ArrayList stockData = null;
		StockDTO stock = null;
		for (int i = 1; i <= 10; i++) {
			map.put("page", Integer.toString(i));
			Document doc = getDoc("https://finance.naver.com/sise/sise_market_sum.nhn?sosok=" + sosok, map);
			Elements els = doc.select("#contentarea > div.box_type_l > table.type_2 > tbody > tr");
//	               #contentarea > div.bosx_type_l > table.type_2 > tbody > tr:nth-child(2)
			// N 종목명 현재가 전일비 등락률 시가 총액
			for (Element el : els) {
				if (el.text().length() != 0) {
//	               stockData = new ArrayList<>();
//	               stockData.add(getInt(el, "td.no")); // n
//	               stockData.add(getString(el, "td:nth-child(2)")); // 종목명
//	               stockData.add(getInt(el, "td:nth-child(3)")); // 현재가
//	               stockData.add(getString(el, "td:nth-child(4) > img", "alt")); // 상승,하락
//	               stockData.add(getInt(el, "td:nth-child(4) > span")); // 전일비
//	               stockData.add(getString(el, "td:nth-child(5) > span")); // 등락률
//	               stockData.add(getInt(el, "td:nth-child(7)")); // 시가 총액 
//	               stockDataList.add(stockData);

					try {
						Thread.sleep(300);
						StockDAO.addStock(new StockDTO(getString(el, "td:nth-child(2)"), getInt(el, "td:nth-child(3)"),
								getString(el, "td:nth-child(4) > img", "alt"), getInt(el, "td:nth-child(4) > span"),
								getString(el, "td:nth-child(5) > span"), getInt(el, "td:nth-child(7)")));
						stock = new StockDTO(getString(el, "td:nth-child(2)"), getInt(el, "td:nth-child(3)"),
								getString(el, "td:nth-child(4) > img", "alt"), getInt(el, "td:nth-child(4) > span"),
								getString(el, "td:nth-child(5) > span"), getInt(el, "td:nth-child(7)"));
						
						System.out.println(stock);
//						System.err.println(stock);

						
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	
	public static Document getDoc(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

	public static Document getDoc(String url, Map<String, String> inputData) throws IOException {
		return Jsoup.connect(url).data(inputData).get();
	}

	public static String getString(Element e, String query) {
		return e.select(query).text();
	}

	public static String getString(Element e, String query, String key) {
		return e.select(query).attr(key);
	}

	public static int getInt(Element e, String query) {
		try {
			return Integer.parseInt(e.select(query).text().replace(",", ""));
		} catch (Exception er) {
			return 0;
		}
	}

	public static int getInt(Element e, String query, String key) {
		try {
			return Integer.parseInt(e.select(query).attr(key).replace(",", ""));
		} catch (Exception er) {
			return 0;
		}

	}

}
