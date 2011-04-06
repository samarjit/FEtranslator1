package com.ycs.workflow.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.ycs.login.actions.LoginAC;

public class DashboardAC extends ActionSupport{
	private static Logger logger = Logger.getLogger(LoginAC.class);
	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
	
	@Action(value="dashboard",
			results={
			@Result(name="success",location="dashboard.jsp"),
			@Result(name="ajax",type="stream",params={"contentType","text/html","inputName","inputStream","resultxml","ProductSetup.xml"})
		}
	)
	public String execute(){
		logger.debug("login action started");
		
		String ajaxStr = "";
		
		
		inputStream = new ByteArrayInputStream(ajaxStr.getBytes());
		return SUCCESS;
	}

	 

	public InputStream getInputStream() {
		return inputStream;
	}
	
}

