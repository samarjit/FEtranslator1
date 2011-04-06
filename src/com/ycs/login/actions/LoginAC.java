package com.ycs.login.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.ycs.user.Role;
import com.ycs.user.accesscontroller.PasswordValidator;
import com.ycs.user.accesscontroller.UserRoleHelper;

public class LoginAC extends ActionSupport implements SessionAware{
	private static Logger logger = Logger.getLogger(LoginAC.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	
	
	private String userid;
	private String passwd;
	
	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Action(value="login", results={
			@Result(name="success",location="/jsp/dashboard.jsp"),
			@Result(name="error",location="/login.jsp")
		})
	public String execute(){
		if(session.containsKey("userid")){
			logger.debug("User already logged in");
			 
		}else{//Logging in
		
			session.put("userid",userid);
			session.put("passwd",passwd);
			
			logger.debug("login action started for user:"+userid);
			PasswordValidator passv = new PasswordValidator();
			if(!passv.isValidUser(userid, passwd)){
				List<String> errorMessages = new ArrayList<String>();
				errorMessages.add("UserId or Password Invalid");
				setActionErrors(errorMessages );
				return com.opensymphony.xwork2.Action.ERROR;
			}
		
			UserRoleHelper urh =  new UserRoleHelper();
			List<Role> listRole = urh.getRolesForUser(userid);
			logger.debug("Role:"+listRole);
			
			session.put("roles",listRole);
		}
		
		
		
		return SUCCESS;
	}

	@Action(value="logout", results={
			@Result(name="success",location="/login.jsp"),
			@Result(name="error",location="/login.jsp")
		})
	public String executeLogout(){
		if(isLoggedIn())
		ServletActionContext.getRequest().getSession().invalidate();
		logger.debug("logged out!");
		return SUCCESS;
	}
	
	public boolean isLoggedIn(){
		if(session.containsKey("userid")){
			 return true; 
		}
		return false;
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		 this.session = arg0;
	}

 
	public Map<String, Object> getSession() {
		return this.session ;
	}

	
}
