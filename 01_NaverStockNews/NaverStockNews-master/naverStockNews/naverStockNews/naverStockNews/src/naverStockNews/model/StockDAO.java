package naverStockNews.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import naverStockNews.model.dto.StockDTO;
import naverStockNews.model.util.DBUtil;

//private String stockname;
//private int price;
//private int net;
//private double updown;
//private int marketcap;
//('삼성전자',25000,2000,10.23,2432342)


public class StockDAO {
	
	//추가
	public static boolean addStock(StockDTO stock) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into stock values(?,?,?,?,?,?)");
			pstmt.setString(1, stock.getStockName());
			pstmt.setInt(2, stock.getPrice());
			pstmt.setString(3, stock.getNetUpDown());
			pstmt.setInt(4, stock.getNet());
			pstmt.setString(5, stock.getUpDown());
			pstmt.setInt(6, stock.getMarketCap());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	//검색 -all
	public static ArrayList<StockDTO> getAllStocks() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<StockDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from stock");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<StockDTO>();
			while(rset.next()){
				list.add(new StockDTO(rset.getString(1), 
						rset.getInt(2), 
						rset.getString(3),
						rset.getInt(4),
						rset.getString(5),
						rset.getInt(6)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	//검색 -one
	public static StockDTO getOneStock(String stockName) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		StockDTO stock = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from stock where stock_name=?");
			pstmt.setString(1, stockName);
			rset = pstmt.executeQuery();
			if(rset.next()){
				stock = new StockDTO(rset.getString(1), 
						rset.getInt(2), 
						rset.getString(3),
						rset.getInt(4),
						rset.getString(5),
						rset.getInt(6));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return stock;
	}
	
	//삭제
	public static boolean deleteStock(String stockName) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from stock where stock_name=?");
			pstmt.setString(1, stockName);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
}
