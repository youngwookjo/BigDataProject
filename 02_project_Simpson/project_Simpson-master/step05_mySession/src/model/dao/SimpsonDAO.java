package model.dao;

import java.util.ArrayList;

import model.dto.SimpsonDTO;

/*private String name;
private String born;
private String like;
private String sort;*/

public class SimpsonDAO {
	public static ArrayList<SimpsonDTO> simpson = new ArrayList<>();

	static {
		simpson.add(new SimpsonDTO("바트 심슨", "1980년 4월 1일", "광대 크러스티,장난,스케이트 보드", "심슨 가족"));
		simpson.add(new SimpsonDTO("리사 심슨", "1983년 5월 9일", "평화, 공부, 조랑말, 색소폰, 음악", "심슨 가족"));
		simpson.add(new SimpsonDTO("호머 심슨", "1956년 5월 12일", "도넛을 비롯한 먹을 것, 맥주, TV, 돈, 낮잠, 엄마", "심슨 가족"));
		simpson.add(new SimpsonDTO("마지 심슨", "1956년 10월 1일", "아이들, 언니들", "심슨 가족"));
		simpson.add(new SimpsonDTO("매기 심슨", "1989년 1월 12일", "젖병", "심슨 가족"));
		simpson.add(new SimpsonDTO("몽고메리 번즈", "", "돈", "기타 인물"));
		simpson.add(new SimpsonDTO("밀하우스", "", "리사", "기타 인물"));
		simpson.add(new SimpsonDTO("네드 플랜더스", "", "개신교", "기타 인물"));
	}

	public static ArrayList<SimpsonDTO> getDB() {
		return simpson;
	}

	public static ArrayList<SimpsonDTO> getResearch(String search) {
		ArrayList<SimpsonDTO> all = new ArrayList<>();
		int size = simpson.size();
		SimpsonDTO dto = null;
		for (int i = 0; i < size; i++) {
			dto = simpson.get(i);
			if (search.equals(dto.getName())) {
				all.add(new SimpsonDTO(dto.getName(), dto.getBorn(), dto.getLike(), dto.getSort()));
			}
		}
		return all;
	}

	public static ArrayList<SimpsonDTO> getFamily() {
		ArrayList<SimpsonDTO> all = new ArrayList<>();
		int size = simpson.size();
		SimpsonDTO dto = null;
		for (int i = 0; i < size; i++) {
			dto = simpson.get(i);
			if ("심슨 가족".equals(dto.getSort())) {
				all.add(new SimpsonDTO(dto.getName(), dto.getBorn(), dto.getLike(), dto.getSort()));
			}
		}
		return all;
	}

}
