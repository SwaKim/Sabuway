package sabuway.domain;

import java.util.Map;

/**
 * VO (Value Object)
 * singleton
 * @author pc18
 *
 */
public class Admin {
	private String name;
	private String age;
	private String id;
	private String pw;
	
	public Admin(Map<String,String> params){
		this.name=params.get("member_name").toString();
		this.age=params.get("member_age").toString();
		this.id=params.get("member_id").toString();
		this.pw=params.get("member_pw").toString();
	}
	
	public Admin(String ID, String PW, String name, String age){
		this.name = name;
		this.age = age;
		this.id = ID;
		this.pw = PW;
	}

	
	//------------ getter setter start----------------//
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
	//---------------getter setter end--------------//
	
	@Override
	public String toString() {
		return id + "\t" + pw + "\t" + name + "\t" + age;
	}
}
