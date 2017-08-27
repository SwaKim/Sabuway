package sabuway.domain;

/**
 * 이용권VO 
 * @author 김수환
 *
 */




public class Menu {
	private String menuName; // 메뉴이름
	private int menuKind; // 메뉴 종류(기간)
	private String menuPrice; // 이용권 가격
	
	public Menu(String menuName, int menuKind,String menuPrice){
		this.menuName = menuName;
		this.menuKind = menuKind;
		this.menuPrice = menuPrice;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuKind() {
		return menuKind;
	}

	public void setMenuKind(int menuKind) {
		this.menuKind = menuKind;
	}

	public String getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}

	@Override
	public String toString() {
		return menuName+"\t\t"+menuKind+"\t\t"+menuPrice;
	}
		
}
