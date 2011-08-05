package actionclass;

import java.util.Locale;
import java.util.Map;

import org.apache.struts2.StrutsConstants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class CardOrder extends ActionSupport{
	
	private String screenName;
	private String submitdata = "";
	private String resourceBundle;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
		
	public String getSubmitdata() {
		return submitdata;
	}

	public void setSubmitdata(String submitdata) {
		this.submitdata = submitdata;
	}
	
//	@Inject(StrutsConstants.STRUTS_CUSTOM_I18N_RESOURCES)
//    public void setResourceBundle(String resourceBundle) {
//        this.resourceBundle = resourceBundle;
//    }

	public String execute() {
//		String rb = ActionContext.getContext().getValueStack().findString("resourceBundle");
//		if (rb == null || rb == "") {
//			ActionContext.getContext().getValueStack().set("resourceBundle", resourceBundle);
//		}
//		Locale ll = ActionContext.getContext().getLocale();
		return SUCCESS;
	}
	public String onload(){
		System.out.println("CardOrder LOAD ****************"); 
		if(screenName == null || "".equals(screenName))
		screenName="cardOrder";
		return "load";
	}

	public String submit(){
		System.out.println("*********getSubmitdata**********"+getSubmitdata());
		return "confirm";
	}
}
