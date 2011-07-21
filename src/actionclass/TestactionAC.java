package actionclass;

import com.opensymphony.xwork2.ActionSupport;

public class TestactionAC extends ActionSupport{
 private String name;
 private String retname;
 private String retrievename;
 
 
	public String execute(){
		System.out.println("TestactionAC:Inside Action..name="+name);
		if(name == null)name ="My name";
		System.out.println("TestactionAC:Inside Action class [retrievename]:"+retrievename);
		return SUCCESS;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRetname() {
		return retname;
	}
	public void setRetname(String retname) {
		this.retname = retname;
	}
	public String getRetrievename() {
		return retrievename;
	}
	public void setRetrievename(String retrievename) {
		this.retrievename = retrievename;
	}
	 

}
