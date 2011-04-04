package com.ycs.login.actions;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAC extends ActionSupport{
	private static Logger logger = Logger.getLogger(LoginAC.class);
	private static final long serialVersionUID = 1L;
	
	@Action(value="login",
			results={
			@Result(name="success",location="login.jsp")
		}
	)
	public String execute(){
		logger.debug("login action started");
		return SUCCESS;
	}
	
}
