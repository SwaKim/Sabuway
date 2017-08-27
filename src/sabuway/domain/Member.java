package sabuway.domain;

/**
 * 회원정보 VO
 * @author pc18
 *
 */

public class Member {
	private String name;
	private String age;
	private String id;
	private String pw;
	private int point = 0;
	private String menu;
	private int menuPrice;
	private String ticketStart;
	private String ticketEnd;
	private String seatNumber;
	private String attendence;
	
	
	


	public Member(String Id, String Pw, String Name, String Age){
		this.name = Name;
		this.age = Age;
		this.id = Id;
		this.pw = Pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public String getMenu() {
		return menu;
	}


	public void setMenu(String menu) {
		this.menu = menu;
	}


	public String getTicketStart() {
		return ticketStart;
	}


	public void setTicketStart(String ticketStart) {
		this.ticketStart = ticketStart;
	}


	public String getTicketEnd() {
		return ticketEnd;
	}


	public void setTicketEnd(String ticketEnd) {
		this.ticketEnd = ticketEnd;
	}
	

	public String getSeatNumber() {
		return seatNumber;
	}


	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}


	public int getMenuPrice() {
		return menuPrice;
	}


	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	
	public String getAttendence() {
		return attendence;
	}


	public void setAttendence(String attendence) {
		this.attendence = attendence;
	}


	public String toString() {
		return id + "\t" + pw + "\t" + name + "\t" + age + "\t" + point + "\t" + menu + "\t" + ticketStart + "\t" + ticketEnd + "\t";
	}
	
	

}