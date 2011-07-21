package actionclass;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class BinGroup extends ActionSupport{
	private Logger logger = Logger.getLogger(getClass());

	private String screenName;
	
	public String view() throws Exception {
		System.out.println("Bin Group ****************"); 
		if(screenName == null || "".equals(screenName))
		screenName = "BinGroup";
		return "view";
	}
	
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
}
