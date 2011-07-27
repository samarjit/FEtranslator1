package com.ycs.fe.actions;

import java.util.Locale;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LocaleAction extends ActionSupport {

	private static final long serialVersionUID = -1069207141208713092L;
	private String language;
	private String resourceBundle;
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

//	@Inject(StrutsConstants.STRUTS_CUSTOM_I18N_RESOURCES)
//	public void setResourceBundle(String resourceBundle) {
//		this.resourceBundle = resourceBundle;
//	}
//	
	
	//@Action(value="locale",results={@Result(name="success",location = "/login.jsp")},interceptorRefs= @InterceptorRef("myCustomStack"))
	public String execute(){
		ActionContext context = ActionContext.getContext();
		if(language != null && language.trim() != ""){
			context.setLocale(new Locale(language));
		}
//		context.getSession().put("resourceBundle", resourceBundle);
	
//		Map parameters = new HashedMap();
//		parameters.put("resourceBundle", resourceBundle);
//		context.setApplication(parameters);
//		Map pp = context.getApplication();
//		String resource = (String) pp.get("resourceBundle");
//		context.getValueStack().set("resourceBundle", resourceBundle);
		
		return SUCCESS;
	}
}
