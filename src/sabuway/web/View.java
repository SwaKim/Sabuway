package sabuway.web;

import java.text.ParseException;
import java.util.List;

import sabuway.Command;
import sabuway.ServiceImpl;
import sabuway.domain.Admin;
import sabuway.domain.Event;
import sabuway.domain.Member;
import sabuway.domain.Menu;


public class View {
	
	private Command c = new Command();
	private static ServiceImpl service = null;		// 싱글톤을 위한 null
	private static View view = null;				// 싱글톤을 위한 null
	private static boolean isAdmin = false;			// 관리자인지 판단
	
	private List<Member> memberList = null;
	private int memberIndex = -1;					// 로그인 후 멤버 인덱스 기억
	private Member member = null;					// 로그인 후 멤버 기억
	
	private int adminIndex = -1;					// 로그인 후 멤버 인덱스 기억
	private Admin admin = null;						// 로그인 후 멤버 기억
	
	private List<Event> eventList = null;
//	private Table t = new Table();
	//private static Vector<String> callStack = new Vector; // 페이지 기억 // 뒤로가기 구현
	
	
	/**
	 * View 생성 직후 Service 생성
	 */
	private View() {
		service = ServiceImpl.makeInstance();
	}
	
	
	/**
	 * View Instance 생성 후 반환
	 * @return View
	 */
	public static View makeInstance() {
		if (view == null) {
			view = new View();
		}
		return view;
	}

	
	
	
	
	//-------------------입력 메서드 시작--------------------//
	
	//-----------------------입력 메서드 끝-------------------------//
	
	
	
	//-----------------------메뉴 메서드 시작------------------------//
	
	/**
	 * 초기화면
	 */
	public void mainMenu(){
		System.out.println("선택하거나 명령을 입력하세요");
		System.out.println("1 : 로그인하기 (login)");
		System.out.println("2 : 회원가입하기 (regi)");
		System.out.print("new command");

		memberList = service.getListMember();
		eventList = service.getListEvent();
		
		c.scan();
		int hotKey = c.getNumber();
		String command = c.getCommand();
		
				
		if(command.equals("login") || hotKey == 1){
			clear();
			if(!c.hasOptions()){
				if(c.checkOptions("-admin")){
					viewLogin(0);
					return;
				}
			}
			viewLogin(1);
			return;
		}
		else if(command.equals("regi") || hotKey == 2){
			clear();
			viewMemberInsert();
			return;
		}
		else {
			System.out.println("명령어가 없습니다.");
			mainMenu();
			return;
		}
	}
	
	
	
	/**
	 * Login 후 화면
	 */
	private void menuAfterLogin(){
		service.checkTicketValid();
		System.out.println("선택하거나 명령을 입력하세요");
		System.out.println("1 : 내 이용권 보기 (vms)");
		System.out.println("2 : 내 회원정보 수정 (vmi)");
		System.out.println("3 : 포인트 구매하기 (pt)");
		System.out.println("4 : 메뉴 주문하기 (mn)");
		System.out.println("5 : 이용하기 (use)");
		System.out.println("6 : 퇴실하기 (out)");
		System.out.println("7 : 로그아웃 (logout)");
		System.out.print("new command");
		
		c.scan();
		int hotKey = c.getNumber();
		String command = c.getCommand();
		
		if(command.equals("vms") || hotKey == 1){
			clear();
			menuMemberTicketInfoEdit();
			return;
		}
		else if(command.equals("vmi") || hotKey == 2){
			clear();
			menuMemberInfoEdit();
			return;
		}
		else if(command.equals("pt") || hotKey == 3){
			clear();
			viewPurchasePoint();
			return;
		}
		else if(command.equals("mn") || hotKey == 4){
			clear();
			viewPurchaseTicket();
			return;
		}
		else if(command.equals("use") || hotKey == 5){
			clear();
			viewAttendence();
			return;
		}
		else if(command.equals("out") || hotKey == 6){
			clear();
			viewOut();
			return;
		}
		else if(command.equals("logout") || hotKey == 7){
			clear();
			viewMemberLogout();
			return;
		}
		else {
			System.out.println("명령어가 없습니다.");
			menuAfterLogin();
			return;
		}
	}
	
	
	private void menuMemberInfoEdit(){
		viewMyInfomation();
		System.out.println("선택하세요");
		System.out.println("1 : 이름변경");
		System.out.println("2 : 나이변경");
		System.out.println("3 : 비번변경");
		System.out.println("4 : 회원탈퇴");
		System.out.println("5 : 나가기");
		System.out.print("new command");
		
		c.scan();
		int hotKey = c.getNumber();
		
		if(hotKey == 1){
			viewMyInfoEdit("name");
			menuMemberInfoEdit();
			return;
		}
		else if(hotKey == 2){
			viewMyInfoEdit("age");
			menuMemberInfoEdit();
			return;
		}
		else if(hotKey == 3){
			viewMyInfoEdit("pw");
			menuMemberInfoEdit();
			return;
		}
		else if(hotKey == 4){
			clear();
			viewMemberRegistOut();
			return;
		}
		else if(hotKey == 5){
			clear();
			menuAfterLogin();
			return;
		}
		else {
			System.out.println("명령어가 없습니다.");
			menuMemberInfoEdit();
			return;
		}
	}
	
	private void menuMemberTicketInfoEdit(){
		if(member.getMenu() == null){
			System.out.println("이용권이 없습니다.");
			menuAfterLogin();
			return;
		}
		viewMyTicketInfomation();
		System.out.println("선택하세요");
		System.out.println("1 : 이용권환불");
//		System.out.println("2 : 자리변경");
		System.out.println("2 : 나가기");
		System.out.print("new command");
		
		c.scan();
		int hotKey = c.getNumber();
		
		if(hotKey == 1){
			viewMyTicketRefond();
			menuAfterLogin();
			return;
		}
		else if(hotKey == 2){
			menuAfterLogin();
			return;
		}
		else {
			System.out.println("명령어가 없습니다.");
			menuMemberTicketInfoEdit();
			return;
		}
	}
	
	
	
	/**
	 * Admin Login 후 화면
	 */
	private void menuAfterAdminLogin(){
		System.out.println("선택하거나 명령을 입력하세요");
		System.out.println("1 : 회원목록보기 (vlst)");
		System.out.println("2 : 회원삭제하기 (rvmbr)");
		System.out.println("3 : 회원기록검색 (smbl)");
		System.out.println("4 : 로그아웃하기 (logout)");
		System.out.print("new command");
		
		memberList = service.getListMember();
		
		c.scan();
		int hotKey = c.getNumber();
		String command = c.getCommand();
		
		if(command.equals("vlst") || hotKey == 1){
			clear();
			viewMembersViaTable();
			menuAfterAdminLogin();
			return;
		}
		else if(command.equals("rvmbr") || hotKey == 2){
			clear();
			viewRegistOutByAdmin();
			return;
		}
		else if(command.equals("smbl") || hotKey == 3){
			clear();
			viewEventFilteredById();
			return;
		}
		else if(command.equals("logout") || hotKey == 4){
			clear();
			viewAdminLogout();
			return;
		}
		else {
			System.out.println("명령어가 없습니다.");
			menuAfterAdminLogin();
			return;
		}
	}
	
	
	
	/**
	 * Console 화면을 깨끗하게 처리
	 */
	private void clear(){
	    for (int i = 0; i < 10; i++){
	        System.out.println("");
	    }
	}
	
	//-----------------------메뉴 메서드 끝------------------------//
	
	
	
	
	
	
	
	//------------------------뷰 메서드 시작----------------------------//
	
	
	
	
//============================ startMenu Views start ===============================//	
	/**
	 * Login 화면, 회원 관리자에서 같이 씀
	 */
	private void viewLogin(int flag){
		System.out.println("로그인 페이지");
		System.out.print("ID");
		String ID = c.scan().getText(); // command input
		System.out.print("PW");
		String PW = c.scan().getText(); // command input
		
		int i = flag == 0 ? service.searchIndexById_ListOfAdmin(ID)
				: flag == 1 ? service.searchIndexById_ListOfMember(ID) : -1;
		
		if(i == -1){
			System.out.println("ID가 없습니다.");
			mainMenu();
			return;
		}
		
		String str = flag == 0 ? service.getListAdmin().get(i).getPw()
				:flag == 1 ? memberList.get(i).getPw() : "";
		
		if(!str.equals(PW)){
			System.out.println("PW가 틀립니다.");
			mainMenu();
			return;
		}
		
		if(flag == 0){
			admin = service.getListAdmin().get(i);
			isAdmin = true;
			adminIndex = i;
			System.out.println("관리자 로그인 완료");
			menuAfterAdminLogin();
		}else {
			member = memberList.get(i);
			isAdmin = false;
			memberIndex = i;
			System.out.println("로그인 완료");
			menuAfterLogin();
		}
	}
	
	

	
	/**
	 * 회원가입 화면
	 */
	private void viewMemberInsert() {
		System.out.println("회원가입 페이지");
		String ID = c.getID();
		int i = service.searchIndexById_ListOfMember(ID);
		while(i != -1){
			System.out.println("이미 있습니다.");
			ID = c.getID();
			i = service.searchIndexById_ListOfMember(ID);
		}
		
		Member newMem = new Member(ID, c.getPW(), c.getName(), c.getAge());
		memberList.add(newMem);
		
		System.out.println("회원가입에 성공하였습니다.");
		service.getListEvent().add(new Event(newMem.getId(), "회원가입", "정상적인 회원가입"));
		mainMenu();
	}
//============================ startMenu Views end ===============================//	
	
	
	
//============================ menuAfterLogin Views start ===============================//
	/**
	 * 회원내역 보기
	 */
	private void viewMembersViaTable() {
		if(isAdmin){
			System.out.println("회원목록");
			System.out.println(service.viewMembersViaTable());
		} else {
			System.out.println("관리자가 아닙니다.");
		}
	}
	
	/**
	 * 로그아웃 보기
	 */
	private void viewMemberLogout(){
		System.out.println("로그아웃 하시겠습니까? y/n");
		if(c.scan().getText().equals("y")){
			memberIndex = -1;
			member = null;
			System.out.println("로그아웃 되었습니다.");
			mainMenu();
		}else if(c.getText().equals("n")){
			System.out.println("로그아웃 취소하였습니다.");
			service.getListEvent().add(new Event(member.getId(), "회원가입", "정상적인 회원가입"));
			menuAfterLogin();
		}else {
			System.out.println("잘못 입력하였습니다.");
			menuAfterLogin();
		}
	}
	
	
	
	/**
	 * 내 이용권 보기
	 */
	private void viewMyTicketInfomation() {
		System.out.println("내 이용권 정보");
		System.out.println("이용권 : "+member.getMenu());
		System.out.println("좌석 : "+member.getSeatNumber());
	}
	
	
	/**
	 * 내 회원정보 보기
	 */
	private void viewMyInfomation() {
		System.out.println("내 회원정보 보기 페이지");
		System.out.println("아이디 : "+member.getId());
		System.out.println("이름 : "+member.getName());
		System.out.println("나이 : "+member.getAge());
	}
	
	
	/**
	 * 내 회원정보 수정하기
	 */
	private void viewMyInfoEdit(String str){
		if(str.equals("name")){
			member.setName(c.getName());
			menuMemberInfoEdit();
			return;
		}else if(str.equals("age")){
			member.setAge(c.getAge());
			menuMemberInfoEdit();
			return;
		}else if(str.equals("pw")){
			System.out.print("현재PW");
			if(c.scan().getText().equals(member.getPw())){
				System.out.print("바꿀");
				String newPW = c.getPW();
				System.out.print("한번더입력");
				String newPW2 = c.scan().getText();
				if(newPW.equals(newPW2)){
					member.setPw(newPW);
					System.out.println("변경 완료");
					service.getListEvent().add(new Event(member.getId(), "회원정보변경", "회원정보변경"));
					menuMemberInfoEdit();
				}else {
					System.out.println("잘못 입력");
					menuMemberInfoEdit();
				}
			}else{
				System.out.println("PW 틀림");
				menuMemberInfoEdit();
			}
			return;
		}
	}
	
	/**
	 * 회원자신이 로그인 후 회원탈퇴하기
	 */
	private void viewMemberRegistOut() {
		System.out.println("회원탈퇴 페이지");
		System.out.print("PW");
		String PW = c.scan().getText();
		if(!member.getPw().equals(PW)){
			System.out.println("PW가 틀립니다.");
			menuAfterLogin();
			return;
		}
		service.getListEvent().add(new Event(member.getId(), "회원탈퇴", "회원정보변경"));
		memberList.remove(memberIndex);
		memberIndex = -1;
		
		clear();
		System.out.println("회원탈퇴 하셨습니다.");
		mainMenu();
	}
	
	
	/**
	 * 이용권 환불
	 * 이용권 사용량 만큼만 환불
	 */
	private void viewMyTicketRefond(){
		System.out.println("이용권환불페이지");
		if(member.getMenu() == null){
			System.out.println("이용권이 없습니다");
			menuAfterLogin();
			return;
		}
		System.out.println(member.getMenu()+", "+member.getMenuPrice());
		System.out.println("환불하시겠습니까?	y/n");
			
		if(c.scan().checkYesNo()){
			int tmp = member.getPoint();
			tmp += member.getMenuPrice();
			member.setPoint(tmp);
			member.setMenu(null);
			member.setTicketStart(null);
			member.setTicketEnd(null);
			service.getListSeat().putId(member.getSeatNumber(), null);
			member.setSeatNumber(null);
			System.out.println("환불되었습니다");
			service.getListEvent().add(new Event(member.getId(), "환불", "이용권환불"));
			System.out.println(member.getPoint());
			menuAfterLogin();
			return;
		} else if(c.scan().checkYesNo()){
			System.out.println("취소되었습니다");
			menuAfterLogin();
			return;
		} else {
			System.out.println("잘못입력하셨습니다");
			menuAfterLogin();
			return;
		}
	}
	
	/**
	 * 포인트 구매
	 * 결재 API 없으므로 일단 테스트로 입력만큼 구매가능
	 */
	private void viewPurchasePoint(){
		System.out.println("포인트 구매");
		int point = Integer.parseInt(c.scan().getText());
		member.setPoint(point);
		service.getListEvent().add(new Event(member.getId(), "포인트구매", "포인트구매"));
		System.out.println(point + "만큼 포인트 구매하였습니다.");
		menuAfterLogin();
	}
	
	/**
	 * 이용권 구매
	 * 현재 이용권이 있는데 다른걸 산다면 그 이용권은 환불되고 다시 결재됨
	 */
	private void viewPurchaseTicket(){
		System.out.println("이용권구매페이지");
		if(member.getMenu() != null){
			System.out.println("이용권이 이미 있습니다. 환불하고 계속하시겠습니까?");
			if(c.scan().checkYesNo()){
				viewMyTicketRefond();
			}else if(c.checkYesNo()){
				System.out.println("취소하였습니다.");
				return;
			}else{
				System.out.println("잘못입력");
			}
		}
		System.out.println("원하시는 이용권이름을 입력해주세요");
		System.out.println(service.viewTicketsViaTable());
		String TicketName = c.scan().getText();
		int i = service.searchIndexById_ListOfTicket(TicketName);
		if(i == -1){
			System.out.println("입력하신티켓이없습니다.");
			menuAfterLogin();
			return;
		}
		Menu menu = service.getListMenu().get(i);
		int ticketPrice = Integer.parseInt(menu.getMenuPrice());
		System.out.println("원하시는 이용권의 가격은 : " + ticketPrice);
		System.out.println("구매하시겠습니까?	y/n");
		String yesNo = c.scan().getText();
		if(yesNo.equals("y")){
			if(member.getPoint() < ticketPrice){
				System.out.println("포인트부족");
				menuAfterLogin();
				return;
			}
			member.setMenu(TicketName);
			member.setMenuPrice(ticketPrice);
			int tmp = member.getPoint()-member.getMenuPrice();
			member.setPoint(tmp);
			
			String mTime = service.conDayOfMill(service.conMillOfDay());
			String mTime2 = service.conDayOfMill(service.conMillOfDay(mTime,menu.getMenuKind()));
			
			member.setTicketStart(mTime);
			member.setTicketEnd(mTime2);
			
			System.out.println("자리번호 입력해주세요");
			System.out.println(service.viewSeatsViaTable());
			String st = c.scan().getText();
			if(service.getListSeat().findNullSeat(st)){
				if(service.getListSeat().getSeats().containsValue(null)){
				System.out.println("사용가능한좌석입니다");
				System.out.println("사용하시겠습니까?	y/n");
				String yN = c.scan().getText();
				if(yN.equals("y")){
					member.setSeatNumber(st);
					service.getListSeat().putId(st, member.getId());
					service.getListEvent().add(new Event(member.getId(), "구매", ticketPrice+", 포인트사용"));
					System.out.println("구매완료");
					System.out.println(member.getMenu()+","+member.getTicketStart()+","+member.getTicketEnd());
					menuAfterLogin();
					return;
				}else{
					System.out.println("취소했습니다");
					menuAfterLogin();
					return;
				}
				}
			}else{
				
				System.out.println("이미 사용중입니다");
				menuAfterLogin();
				return;
			}
			
		} else if(yesNo.equals("n")){
			System.out.println("구매취소");
		} else{
			System.out.println("잘못된입력");
		}
		menuAfterLogin();
	}
	
	/**
	 * 이용 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	private void viewAttendence(){
		if(member.getMenu()==null){
			System.out.println("이용권이 없습니다.");
			menuAfterLogin();
			return;
		}
		
		System.out.println("이용 하시겠습니까?	y/n");
		if(c.scan().checkYesNo()){
			service.checkTicketValid();
			member.setAttendence("Attendence");
			String mTime = service.conDayOfMill(service.conMillOfDay());
			System.out.println(mTime+","+member.getSeatNumber()+","+member.getAttendence()+","+member.getTicketEnd());
			System.out.println("사용시작합니다");
			service.getListEvent().add(new Event(member.getId(), "이용", "정상적인 입실"));
			menuAfterLogin();
			return;
			
		} else if(c.scan().checkYesNo()){
			System.out.println("취소하셨습니다");
			menuAfterLogin();
			return;
		} else{
			System.out.println("잘못입력하셨습니다");
			menuAfterLogin();
			return;
		}
	}
	
	/**
	 * 이용퇴근
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	private void viewOut(){
		if(member.getAttendence()==null){
			System.out.println("이용기록이 없습니다.");
			menuAfterLogin();
			return;
		}
		System.out.println("나가시겠습니까? y/n");
		if(c.scan().checkYesNo()){
			String mTime = service.conDayOfMill(service.conMillOfDay());
			System.out.println("정상적으로처리되었습니다.");
			service.getListEvent().add(new Event(member.getId(), "퇴실", "정상적으로 퇴실"));
			menuAfterLogin();
			return;
		} else if(c.checkYesNo()){
			System.out.println("취소하셨습니다");
			menuAfterLogin();
			return;
		} else{
			System.out.println("잘못입력하셨습니다");
			menuAfterLogin();
			return;
		}
	}
	//============================ menuAfterLogin Views end ===============================//

	
	
	
	
	
	//============================ menuAfterAdminLogin Views start ===============================//
	
	private void viewRegistOutByAdmin() {
		System.out.println("회원삭제 페이지");
		viewMembersViaTable();
		System.out.print("ID");
		String ID = c.scan().getText();
		int i = service.searchIndexById_ListOfMember(ID);
		if(i == -1){
			System.out.println("ID가 없습니다.");
			menuAfterAdminLogin();
			return;
		}
		if(memberList.get(i).getMenu() != null){
			System.out.println("유효한 이용권이 있습니다. 이용권을 환불합니다."); // 구현
		}
		if(memberList.get(i).getPoint() != 0 ){
			System.out.println("포인트가 남았습니다. 모두 회원의 계좌로 환불합니다."); // 구현
		}
		memberList.remove(i);
		clear();
		System.out.println("회원이 삭제되었습니다.");
		menuAfterAdminLogin();
	}
	
	private void viewAdminLogout(){
		System.out.println("로그아웃 하시겠습니까? y/n");
		if(c.scan().getText().equals("y")){
			adminIndex = -1;
			admin = null;
			isAdmin = false;
			System.out.println("로그아웃 되었습니다.");
			mainMenu();
		}else if(c.getText().equals("n")){
			System.out.println("로그아웃 취소하였습니다.");
			menuAfterAdminLogin();
		}else {
			System.out.println("잘못 입력하였습니다.");
			menuAfterAdminLogin();
		}
	}
	
	private void viewEventFilteredById() {
		System.out.println("검색하려는 회원의 아이디를 입력해주세요");
		String searchId = c.scan().getText();
		String result = "";
		if(searchId == null || searchId.equals("")){
			result+=service.searchIndexById_ListOfEvent();
			System.out.println(result);
		}else{
			result+=service.searchIndexById_ListOfEvent(searchId);
			System.out.println(result);
		}
		System.out.println("출력?");
		if(c.scan().checkYesNo()){
//			t.scan(result).parseExcel().toExcelFile("Cafe_event");
			System.out.println("이메일?");
			if(c.scan().checkYesNo()){
				System.out.print("주소");
//				t.scan(result).sendMail("sdh1591@naver.com", "eb3cf555f170e5d4", c.scan().getText(), "Cafe_event");
				menuAfterAdminLogin();
			}else{
				System.out.print("취소");
				menuAfterAdminLogin();
			}
		}else{
			System.out.print("취소");
			menuAfterAdminLogin();
		}
	}
	
	private void viewEventFilteredByDate() {
		System.out.println("");
		menuAfterAdminLogin();
	}
	
	//============================ menuAfterAdminLogin Views end ===============================//
	
	//-----------------------뷰 메서드 끝-----------------------------------//
	
	
	
}
