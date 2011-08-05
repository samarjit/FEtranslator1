package com.ycs.fe.commandprocessor;

import java.util.Set;

import org.dom4j.Element;

import com.ycs.fe.dto.PageReturnType;
import com.ycs.fe.util.ScreenMapRepo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReturnCommandProcessor {
	public PageReturnType getReturnType(String screenName, JSONObject submitdataObj){
		PageReturnType pgReturnType = new PageReturnType();
		
		Element rootXml = ScreenMapRepo.findMapXMLRoot(screenName);
		@SuppressWarnings("unchecked")
		Set<String>  itr =  ( (JSONObject) submitdataObj).keySet(); 
	    for (String dataSetkey : itr) { //form1, form2 ...
	    	JSONArray dataSetJobj = ((JSONObject) submitdataObj).getJSONArray(dataSetkey);
	    	for (Object jsonRecord : dataSetJobj) { //rows in dataset a Good place to insert DB Transaction
	    		String cmd = ((JSONObject) jsonRecord).getString("command");
	    		Element elmCmd = (Element) rootXml.selectSingleNode("/root/screen/commands/cmd[@name='"+cmd+"' ] ");
	    		System.out.println("/root/screen/commands/cmd[@name='"+cmd+"' ] ");
	    		String strResult  = elmCmd.attributeValue("result");
	    		if("".equals(strResult) || "ajax".equals(strResult)){
	    			pgReturnType.resultName = strResult;
	    		}else if(strResult.endsWith("page")){
	    			pgReturnType.resultName = "customXMLRes";
	    			pgReturnType.resultPage = strResult;
	    			pgReturnType.nextScreenName = strResult.substring(0,strResult.length() - 6);
	    		}else if(strResult.endsWith("ftl")){
	    			pgReturnType.resultName = "freemarker";
	    			pgReturnType.resultPage = strResult;
	    			pgReturnType.nextScreenName = strResult.substring(0,strResult.length() - 5);
	    		}else if(strResult.endsWith("vm")){
	    			pgReturnType.resultName = "velocity";
	    			pgReturnType.resultPage = strResult;
	    			pgReturnType.nextScreenName = strResult.substring(0,strResult.length() - 4);
	    		}else{
	    			pgReturnType.resultName = "dispatcher";
	    			pgReturnType.resultPage = strResult;
	    			pgReturnType.nextScreenName = strResult.substring(0,strResult.lastIndexOf('.') - 1);
	    		}
	    		
	    		
	    	}
	    }
	
		return pgReturnType;
	}
}
