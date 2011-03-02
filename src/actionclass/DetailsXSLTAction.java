package actionclass;

import com.opensymphony.xwork2.ActionSupport;

public class DetailsXSLTAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -552896450198022478L;

	private String firstname;
	private String LastName;
	private String gender;
	private String check;
	private String orderdate;
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String details() throws Exception {
		System.out.println("FirstName: " + firstname + " LastName : "
				+ LastName + " Gender : " + gender + " OrderDate : "
				+ orderdate + "  Check" + check + "  Country"+ country);
		return "result";
	}
}
