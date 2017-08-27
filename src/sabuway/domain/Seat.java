package sabuway.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 좌석 정보가 있는 클래스
 * @author
 *
 */

public class Seat {
	private Map<String, String> seats = new HashMap<String, String>();
	
	public boolean putSeat(String key){
		if(!seats.containsKey(key)){
			seats.put(key, null);
			return true;
		}
		return false;
	}
	
	public boolean putId(String key, String val){
		if(seats.containsKey(key)){
			seats.put(key,val);
			return true;
		}
		return false;
	}
	
	public boolean findNullSeat(String key){
		if(seats.containsKey(key)){
			if(seats.get(key) == null){
				return true;
			}
		}
		return false;
	}
	
	public String findNullSeats(){
		String result = "";
		for(Object key : seats.keySet()){
			if(seats.get(key) != null){
				result = seats.get(key);
				return result;
			}
		}
		return "";
	}

	public Map<String, String> getSeats() {
		return seats;
	}
	
}
