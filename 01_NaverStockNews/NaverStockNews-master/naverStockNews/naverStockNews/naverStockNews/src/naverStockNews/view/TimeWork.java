package naverStockNews.view;

import java.util.TimerTask;

import naverStockNews.controller.NaverStockNewsController;

public class TimeWork extends TimerTask {
	
	@Override
	public void run() {
		System.out.println("시간 맞춤: 장 시작 직후 [출발] 전 회원 대상 문자 서비스");
		NaverStockNewsController.getStartStock();	
		System.out.println();
		System.out.println("시간 맞춤: 장중 [특징주], [실적속보], [공시] 전 회원 대상 문자 서비스");
		NaverStockNewsController.getFeaturedStock();	
	}
	
}