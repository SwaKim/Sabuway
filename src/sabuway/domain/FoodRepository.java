package sabuway.domain;

import java.util.List;

import sabuway.db.DataBase;

/**
 * DB 접근을 위한 Dao(Data access object) 클래스
 * Singleton
 * @author 김수환
 *
 */
public class FoodRepository {
	
	private static FoodRepository foodRepository = null;
	private DataBase db = null;
	
	
	
	/**
	 * Dao 생성 직후 바로 DB 생성
	 */
	private FoodRepository(){
		db = DataBase.makeInstance();
	}
	
	
	
	/**
	 * Dao Instance 생성 후 반환
	 * @return Dao
	 */
	public static FoodRepository makeInstance(){
		if(foodRepository == null){
			foodRepository = new FoodRepository();
		}
		return foodRepository;
	}
	
	
	
	//---------------------getter 시작---------------------------//
	/**
	 * DB에서 menuList 얻어오기
	 * @return MenuList List
	 */
	
	public List<Menu> getMenuList(){
		return db.getMenuList();
	}
	
	public Seat getSeatList(){
		return db.getSeatList();
	}
	//--------------------getter 끝-------------------------------//
	
}
