package sabuway.db;

import java.util.ArrayList;

import sabuway.domain.Admin;
import sabuway.domain.Event;
import sabuway.domain.Member;
import sabuway.domain.Menu;
import sabuway.domain.Seat;

/**
 * ArrayList VO 기반 DB 구축
 * Singleton 구현을 위해 getter setter 사용
 * @author pc18
 *
 */

public class DataBase {
	
	private static ArrayList<Member> memberList = new ArrayList<Member>(); // 이 자체가 하나의 DB table이다.
	private static ArrayList<Admin> adminList = new ArrayList<Admin>();
	private static ArrayList<Menu> ticketList = new ArrayList<Menu>();
	private static ArrayList<Event> eventList = new ArrayList<Event>();
	private static Seat seatList = new Seat();
	
	private static DataBase db = null;
	
	
	
	/**
	 * initialize를 위한 생성자, 초기에 들어갈 Data를 입력
	 */
	
	private DataBase(){
		Admin avo1 = new Admin("admin1", "1234", "admin1", "10");
		adminList.add(avo1);
		Member mem1 = new Member("test", "1234", "test", "1234");
		memberList.add(mem1);
		Menu tvo1 = new Menu("1day", 1,"5000");
		ticketList.add(tvo1);
		Menu tvo2 = new Menu("7day", 7,"30000");
		ticketList.add(tvo2);
		Menu tvo3 = new Menu("30day", 30,"140000");
		ticketList.add(tvo3);
		
		seatList.putSeat("1-01");
		seatList.putSeat("1-02");
		seatList.putSeat("1-03");
		seatList.putSeat("1-04");
		seatList.putSeat("1-05");
	}
	

	
	
	/**
	 * DB Instance 생성 후 반환
	 * @return DB
	 */
	public static DataBase makeInstance(){
		if(db==null){
			db=new DataBase();
		}
		return db;
	}
	
	
	//---------------------getter 시작--------------------------------//
	/**
	 * Member 리스트 반환
	 * @return MemberList ArrayList
	 */
	public static ArrayList<Member> getMemberList() {
		return memberList;
	}
	
	
	/**
	 * Admin 리스트 반환
	 * @return MemberList ArrayList
	 */
	public static ArrayList<Admin> getAdminList() {
		return adminList;
	}
	
	/**
	 * Ticket 리스트 반환
	 * @return MemberList ArrayList
	 */
	public static ArrayList<Menu> getMenuList(){
		return ticketList;
	}
	
	

	public static ArrayList<Event> getEventList(){
		return eventList;
	}
	
	public static Seat getSeatList(){
		return seatList;
	}
	//--------------------getter 끝-----------------------------------//
	
}