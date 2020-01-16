package naverStockNews.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import naverStockNews.model.dto.NowNewsDTO;
import naverStockNews.model.util.DBUtil;

public class NowNewsDAO {


	// 뉴스 추가
	public static boolean addNowNews(NowNewsDTO nownews) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into now_news (news_title) values(?)");
			pstmt.setString(1, nownews.getNewsTitle());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);

		}
		return false;
	}
	
	// 모든 뉴스 검색
	   public static ArrayList<NowNewsDTO> getAllNews() throws SQLException {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      ArrayList<NowNewsDTO> list = null;
	      try {
	         con = DBUtil.getConnection();
	         pstmt = con.prepareStatement("select * from now_news");
	         rset = pstmt.executeQuery();

	         list = new ArrayList<NowNewsDTO>();
	         while (rset.next()) {
	            list.add(new NowNewsDTO(rset.getString("news_title")));
	         }
	      } finally {
	         DBUtil.close(con, pstmt, rset);
	      }
	      return list;
	   }

	// 문자열 포함 뉴스 검색
	public static ArrayList<NowNewsDTO> getNowNews(String newsTitle) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NowNewsDTO> list = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from now_news where news_title like '%'||?||'%'");
			pstmt.setString(1, newsTitle);
			rset = pstmt.executeQuery();

			list = new ArrayList<NowNewsDTO>();
			while (rset.next()) {
				list.add(new NowNewsDTO(rset.getString("news_title")));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}

	// 관련 뉴스 삭제
	public static boolean deleteNowNews(String newsTitle) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from now_news where news_title like '%'||?||'%'");
			pstmt.setString(1, newsTitle);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	

}
