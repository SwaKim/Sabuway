package sabuway;

import java.util.List;

import sabuway.db.DataBase;
import sabuway.domain.Admin;
import sabuway.domain.Event;
import sabuway.domain.Member;
import sabuway.domain.Menu;
import sabuway.domain.Seat;

/**
 * DB 접근을 위한 Dao(Data access object) 클래스
 * Singleton
 * @author 김수환
 *
 */
public class Repository {
	
	private static Repository repository = null;
	private DataBase db = null;
	
	
	
	/**
	 * Dao 생성 직후 바로 DB 생성
	 */
	private Repository(){
		db = DataBase.makeInstance();
	}
	
	
	
	/**
	 * Dao Instance 생성 후 반환
	 * @return Dao
	 */
	public static Repository makeInstance(){
		if(repository == null){
			repository = new Repository();
		}
		return repository;
	}
	
	
	
	//---------------------getter 시작---------------------------//
	/**
	 * DB에서 memberlist 얻어오기
	 * @return MemberList List
	 */
	public List<Member> getMemberList(){
		return db.getMemberList();
	}
	
	
	/**
	 * DB에서 adminlist 얻어오기
	 * @return MemberList List
	 */
	public List<Admin> getAdminList(){
		return db.getAdminList();
	}
	
	
	public List<Menu> getMenuList(){
		return db.getMenuList();
	}
	
	
	public List<Event> getEventList(){
		return db.getEventList();
	}
	
	public Seat getSeatList(){
		return db.getSeatList();
	}
	//--------------------getter 끝-------------------------------//
	
}
