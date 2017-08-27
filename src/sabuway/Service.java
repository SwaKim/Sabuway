package sabuway;

import java.util.Date;
import java.util.List;

import sabuway.domain.Admin;
import sabuway.domain.Event;
import sabuway.domain.Member;
import sabuway.domain.Seat;


interface Service {
	//-------------------------Dao 접근 시작---------------------------------------//
	List<Member> getListMember(); // 해당 VO list 얻어오기
	List<Admin> getListAdmin(); // 해당 VO list 얻어오기
	List<Event> getListEvent(); // 해당 VO list 얻어오기

	Seat getListSeat(); // 해당 VO 얻어오기

	//-------------------------Dao 접근 끝---------------------------------------//
	
	//-------------------------search 시작---------------------------------------//
	//Dao 접근 메서드 이용
	int searchIndexById_ListOfMember(String searchId); // VO List에서 id로 검색해서 index 리턴
	int searchIndexById_ListOfAdmin(String searchId); // VO List에서 id로 검색해서 index 리턴
	int searchIndexById_ListOfTicket(String searchName); // VO List에서 id로 검색해서 index 리턴
	String searchIndexById_ListOfEvent(String searchId); // VO List에서 id로 검색해서 index 리턴
	String searchIndexById_ListOfEvent(Date searchDate); // VO List에서 Date로 검색해서 index 리턴

	//-------------------------search 끝---------------------------------------//
	
	//-------------------------match 시작--------------------------------------//
	// match 는 search를 이용해 VO를 얻어와서 사용
	//-------------------------match 끝--------------------------------------//
	
	
	//-------------------------기타 많이 쓰는거 메서드화 시작----------------------------//
	String md5Digest(String input); // 암호적용
	
	//------------------- 시간메서드 시작-------------------------------------//
	long conMillOfDay(); // 오늘날짜
	long conMillOfDay(String date); // 오버라이드
	long conMillOfDay(String date, int term); // 특정 날짜에서 기간 후 날짜 반환
	long conMillOfDay(String date1, String date2);
	String conDayOfMill(long date);
	//------------------- 시간메서드 끝-------------------------------------//
	
	
	
	//--------------------------여기에 추가 시작------------------------------------//
	
	
	
	
	
	
	
	
	//--------------------------여기에 추가 끝------------------------------------//
	
	//-------------------------기타 많이 쓰는거 메서드화 끝----------------------------//
}
