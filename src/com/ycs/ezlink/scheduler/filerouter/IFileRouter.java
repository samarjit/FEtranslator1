package com.ycs.ezlink.scheduler.filerouter;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IFileRouter {
	@WebMethod
	public abstract String process(String filetype);

}