package com.ycs.ezlink.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ycs.fe.exception.FrontendException;

public class FileService extends ActionSupport {

	private static Logger logger = Logger.getLogger(FileService.class);
	private static final long serialVersionUID = 1L;
	public String dirAlias;
	public InputStream inputStream;
	private String relativePath;
	
	@Action(value="fileservice", results={@Result(type="stream",name="ajax",params={"contentType","application/json"}  )})
	public String execute(){
		String result="";
		JSONObject jores = new JSONObject();
		JSONArray jafile = new JSONArray();
		JSONArray jadir = new JSONArray();
		try{
			
		
			if(dirAlias == null || "".equals(dirAlias)){
				addActionError("dirAlias cannot be null");
				throw new FrontendException("dirAlias cannot be null");
			}
			
			String path = findRealDirPath(dirAlias);
			if(relativePath != null && !"".equals(relativePath)){
				path += relativePath;
				jores.put("relativePath", relativePath);
			}
//			System.out.println("relativePath:"+relativePath+"\n fullpath:"+path);
			File fi = new File(path);
			if(fi.exists()){
				if(fi.isFile()){
//					jores.put("file", fi.getAbsolutePath()); 
					System.out.println(ServletActionContext.getServletContext().getRealPath("/"));
					jores.put("file", ServletActionContext.getRequest().getContextPath()+relativePath); 
				}else{
					for (File ftemp: fi.listFiles()) {
						if(ftemp.isFile()){
							jafile.add(ftemp.getName());
						}else{
							jadir.add(ftemp.getName());
						}
					}
					jores.put("files", jafile);
					jores.put("dirs", jadir);
				}
			}else{
				addActionError("File not found");
				throw new FrontendException("File not found");
			}
			
			result = jores.toString();
		}catch(FrontendException e){
			if(getActionErrors().size() !=0){
				logger.error("Error:"+e);
				JSONArray jar = new JSONArray();
				jar.addAll( getActionErrors());
				result = "{\"error\":"+jar.toString()+"}";
			} else{
				result = "{\"error\":\""+e.getLocalizedMessage()+"\"}";
			}
		}
		
		
		try {
			inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "ajax";
	}
 
	private String findRealDirPath(String dirAlias){
	   String path = "";
		if("EMAIL_TEMPLATES".equals(dirAlias)){
			path = "C:/Eclipse/workspace1/FEtranslator1/WebContent";
		}
		return path;
	}
	
	
	public String getDirAlias() {
		return dirAlias;
	}

	public void setDirAlias(String dirAlias) {
		this.dirAlias = dirAlias;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	
	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
