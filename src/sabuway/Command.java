package sabuway;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
	private Scanner sc = new Scanner(System.in);
	private String text = "";
	private String command = "";
	private Map<String, Boolean> options = new HashMap<String, Boolean>();
	


	public void setText(String text) {
		this.text = text;
	}


	public Command(){
	}
	
	
	
	//================= 입력 시작 =======================================//
	public Command scan(){
		System.out.print(">>");
		this.text = sc.nextLine();
		return checkCommand();
	}
	
	public Command scan(String str){
		this.text = str;
		return checkCommand();
	}
	//================== 입력 끝 ========================================//
	
	
	//===================== 커맨드 스캔 시작 ==================================//
	private Command checkCommand(){
		options.clear(); // 옵션은 새 커맨드 올때마다 초기화
		Pattern p = Pattern.compile("login|-admin|regi|vlst|vms|vmi|rt|rvmbr|pt|tk|logout|[0-9]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(this.text);
		StringBuffer sb = new StringBuffer();
		boolean f = false;
		
		while(m.find()){
			String str = m.group();
			if(f){
				options.put(str, true);
			}
			else{
				sb.append(m.group());
				f = true;
			}
		}
		this.command = sb.toString().trim();
		return this;
	}
	
	public int getNumber(){
		String str = this.text;
		char check;
		if(str.equals("")){
			return -1;
		} else {
			for(int i = 0; i < str.length(); i++){
				check = str.charAt(i);
				if( check < 48 || check > 58){
					return -1;
				}
			}
		}
		return Integer.parseInt(str);
	}
	

	public boolean checkOptions(String str) {
		return options.containsKey(str);
	}
	
	public boolean hasOptions(){
		return this.options.isEmpty();
	}
	
	public boolean checkYesNo() {
		if(text.equals("y") || text.equals("Y")){
			return true;
		}else {
			return false;
		}
	}
	//===================== 커맨드 스캔 끝 ==================================//	
	
	
	
	
	//============================= 정규식 시작 ===================================//
	public boolean isPW(){
		return Pattern.matches("[0-9]+", text);
	}
	
	public boolean isID(){
		return Pattern.matches("[a-zA-Z]+", text);
	}
	
	public boolean isName(){
		return Pattern.matches("[a-zA-Z]+", text);
	}
	
	public boolean isAge(){
		return Pattern.matches("[0-9]+", text);
	}
	//============================= 정규식 끝 ===================================//
	
	
	
	//==================================== 해당 커맨드에 정규화된 String 넣기 시작 ========================//
	public String getID(){
		System.out.print("ID");
		scan();
		while(!isID()){
			System.out.print("ID다시");
			scan();
		}
		return getText();
	}
	public String getPW(){
		System.out.print("PW");
		scan();
		while(!isPW()){
			System.out.print("PW다시");
			scan();
		}
		return getText();
	}
	public String getName(){
		System.out.print("Name");
		scan();
		while(!isName()){
			System.out.print("Name다시");
			scan();
		}
		return getText();
	}
	public String getAge(){
		System.out.print("Age");
		scan();
		while(!isAge()){
			System.out.print("Age다시");
			scan();
		}
		return getText();
	}
	
	//==================================== 해당 커맨드에 정규화된 String 넣기 끝 ========================//
	
	
	
	
	
	
	//====================== getter setter 시작 ===============================//
	
	public String getCommand() {
		return command;
	}

	public String getText() {
		return text;
	}
	

	//====================== getter setter 끝 ===============================//
	
	
	
	
}
