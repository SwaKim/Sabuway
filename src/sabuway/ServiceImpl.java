package sabuway;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sabuway.domain.Admin;
import sabuway.domain.Event;
import sabuway.domain.Member;
import sabuway.domain.Menu;
import sabuway.domain.Seat;

/**
 * Dao 접근 및 기타 service를 위한 클래스
 * @author 김수환
 *
 */
public class ServiceImpl implements Service{
	
	private Repository dao = null;
	private static ServiceImpl service = null;
//	private static Table t = new Table();
	
	/**
	 * service 생성 직후 dao 생성
	 */
	private ServiceImpl() {
		dao = Repository.makeInstance();
	} // 서비스 생성하면 dao도 생성
	
	
	/**
	 * Service Instance 생성 후 반환
	 * @return Service
	 */
	public static ServiceImpl makeInstance() {
		if (service == null) {
			service = new ServiceImpl();
		}
		return service;
	}

//------------------Service Impls 시작----------------------------------------------------//

	
//------------------------Dao 접근 시작--------------------------------------//
	public List<Member> getListMember(){
		return dao.getMemberList();
	}
	public List<Admin> getListAdmin(){
		return dao.getAdminList();
	}
	public List<Menu> getListMenu(){
		return dao.getMenuList();
	}
	public List<Event> getListEvent() {
		return dao.getEventList();
	}
	public Seat getListSeat(){
		return dao.getSeatList();
	}
//------------------------Dao 접근 끝--------------------------------------//
	
//------------------------search 시작-------------------------------------//
	public int searchIndexById_ListOfMember(String searchId){
		int index = -1;
		List<Member> tmp = getListMember();
		for (int i = 0; i < tmp.size(); i++) {
			if(tmp.get(i).getId().equals(searchId)){
				index = i;
				return index;            
			}      
		}
		return index;
	}
	public int searchIndexById_ListOfAdmin(String searchId){
		int index = -1;
		List<Admin> tmp = getListAdmin();
		for (int i = 0; i < tmp.size(); i++) {
			if(tmp.get(i).getId().equals(searchId)){
				index = i;
				return index;            
			}      
		}
		return index;
	}
	public int searchIndexById_ListOfTicket(String searchName){
		int index = -1;
		List<Menu> tmp = getListMenu();
		for (int i = 0; i < tmp.size(); i++) {
			if(tmp.get(i).getMenuName().equals(searchName)){
				index = i;
				return index;            
			}      
		}
		return index;
	}
	public String searchIndexById_ListOfEvent(String searchId) {
		String result = "날짜\t\t이벤트\t\t비고\n";
		List<Event> tmp = getListEvent();
		for (int i = 0; i < tmp.size(); i++) {
			if(tmp.get(i).getEventOwn().equals(searchId)){
				result += tmp.get(i).toString();
			}
		}
		return result;
	}
	public String searchIndexById_ListOfEvent(Date searchDate) {
		String result = "날짜\t\t이벤트\t\t비고\n";
		List<Event> tmp = getListEvent();
		for (int i = 0; i < tmp.size(); i++) {
			if(tmp.get(i).getEventDate().equals(searchDate)){
				result += tmp.get(i).toString();
			}      
		}
		return result;
	}
	public String searchIndexById_ListOfEvent() {
		String result = "날짜\t\t이벤트\t\t비고\n";
		List<Event> tmp = getListEvent();
		for (int i = 0; i < tmp.size(); i++) {
			result += tmp.get(i).toString();
		}
		return result;
	}
//------------------------search 끝-------------------------------------//
	
	
	
	
	
	
	
	
	
	//-----------------------기타 시작------------------------------//
	
	//-------------암호화 시작 안봐도됨-------------------------------------------//
/*	public String md5Digest(String input){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		md.update(input.getBytes());
		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(md.digest());
	}*/

	@Override
	public String md5Digest(String input) {
		// TODO Auto-generated method stub
		return null;
	}
	//-------------암호화 끝 안봐도됨-------------------------------------------//
	
	
	//------------------- 시간메서드 시작-------------------------------------//
	public long conMillOfDay(){
		return System.currentTimeMillis();
	} // 오늘날짜
	public long conMillOfDay(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}// 오버라이드
	public long conMillOfDay(String date, int term){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long cdate;
		try {
			cdate = formatter.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		long cterm = term*24*60*60*1000;
		return cdate+cterm;
	} // 특정 날짜에서 기간 후 날짜 반환
	public long conMillOfDay(String date1, String date2){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long cdate1 = 0;
		long cdate2 = 0;
		try {
			cdate1 = formatter.parse(date1).getTime();
			cdate2 = formatter.parse(date2).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cdate1 < cdate2){
			return -1;
		}
		return cdate1 - cdate2;
	} // 
	public String conDayOfMill(long date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeInDate = new Date(date);  
        String timeInFormat = formatter.format(timeInDate);
        return timeInFormat;
	}
	//------------------- 시간메서드 끝-------------------------------------//
	
	
	//----------------테이블 메서드 시작====================================//
	public String viewMembersViaTable(){
		String result = "아이디\t비번\t이름\t나이\t포인트\t이용권\t이용권시작\t이용권끝\n";
		List<Member> tmp = getListMember();
		for(int i = 0; i < tmp.size(); i++){
			result += tmp.get(i).toString()+'\n';
		}
//		t.scan(result).parseExcel().toExcelFile("Cafe_members");
		return result;
	}
	
	
	public String viewSeatsViaTable(){
		String result = "";
		Map<String,String> tmp = getListSeat().getSeats();
		for(Object key : tmp.keySet()){
			result += "좌석이름 : "+key+", 좌석예약 : "+tmp.get(key)+'\n';
		}
		return result;
	}
	
	public String viewTicketsViaTable(){
		String result = "이용권이름\t이용권기간\t이용권가격\n";
		List<Menu> tmp = getListMenu();
		for(int i = 0; i < tmp.size(); i++){
			result += tmp.get(i).toString()+'\n';
		}
		return result;
	}
	
	
	

	public String viewAdminsViaTable(){
		String result = "";
		for(int i = 0; i < dao.getAdminList().size(); i++){
			result += dao.getAdminList().get(i).toString()+'\n';
		}
		return result;
	}
	//----------------테이블 메서드 끝====================================//
	
	
	
	
	public boolean checkTicketValid(){
		return true;
	}
	//-----------------------기타 끝------------------------------//




	//------------------Service Impls 끝----------------------------------------------------//
}
