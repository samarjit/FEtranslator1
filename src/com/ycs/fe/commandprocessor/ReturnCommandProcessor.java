package com.ycs.fe.commandprocessor;

import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.ycs.fe.dto.PageReturnType;
import com.ycs.fe.exception.FrontendException;
import com.ycs.fe.util.ScreenMapRepo;

/**
 * The first command of the dataset is processed to resolve result, all the subsequent rows of data-sets are ignored.
 * The above confusion does not occur when bulkcmd is processed as there is only one command per request.
 * @author Samarjit
 * @version 1.0
 */
public class ReturnCommandProcessor {
	private static Logger logger =   Logger.getLogger(ReturnCommandProcessor.class);
	/**
	 * @param screenName
	 * @param submitdataObj from which the command or bulkcmd will match with &lt;cmd .. result="ajax" /> 
	 * to resolve a return struts name, nextScreenName (if *.page) and result pages  ajax,  *.ftl, *.vm, custom *.page everything else is dispatcher (*.jsp)
	 * @return PageReturnType object
	 * @throws FrontendException
	 */
	public PageReturnType getReturnType(String screenName, JSONObject submitdataObj) throws FrontendException{
		PageReturnType pgReturnType = new PageReturnType();
		
		Element rootXml = ScreenMapRepo.findMapXMLRoot(screenName);
		@SuppressWarnings("unchecked")
		Set<String>  itr =  ( (JSONObject) submitdataObj).keySet(); 
		
		if(submitdataObj == null || submitdataObj.isNullObject()){
			pgReturnType.nextScreenName = screenName;
			pgReturnType.resultName = "view";
			pgReturnType.resultPage = screenName;
		}else{
		 if(( (JSONObject) submitdataObj).get("bulkcmd") !=null){
			 String cmd =     submitdataObj.getString("bulkcmd");
	    		Element elmCmd = (Element) rootXml.selectSingleNode("/root/screen/commands/bulkcmd[@name='"+cmd+"' ] ");
	    		System.out.println("/root/screen/commands/bulkcmd[@name='"+cmd+"' ] ");
	    		String strResult  = elmCmd.attributeValue("result");
	    		resolveResult(pgReturnType, strResult);
		 } else {
			for (String dataSetkey : itr) { //form1, form2 ...skip txnrec,sessionvars
		    	//txnrec & sessionvars is just a group of data not a processing command
		    	if( dataSetkey.equals("txnrec")   || dataSetkey.equals("sessionvars")||  dataSetkey.equals("pagination"))continue;
		    	
		    	JSONArray dataSetJobj = ((JSONObject) submitdataObj).getJSONArray(dataSetkey);
		    	for (Object jsonRecord : dataSetJobj) { //rows in dataset a Good place to insert DB Transaction
		    		String cmd = ((JSONObject) jsonRecord).getString("command");
		    		if(cmd !=null && !"".equals(cmd)){
			    		Element elmCmd = (Element) rootXml.selectSingleNode("/root/screen/commands/cmd[@name='"+cmd+"' ] ");
			    		System.out.println("/root/screen/commands/cmd[@name='"+cmd+"' ] ");
			    		String strResult  = elmCmd.attributeValue("result");
			    		
			    		resolveResult(pgReturnType, strResult);
			    		break;
		    		}
		    	}
		    }
		 }
		}
		
		if(!(pgReturnType.resultName != null && !"".equals(pgReturnType.resultName)))
				logger.error("The Result could not be resolved");
		
		return pgReturnType;
	}
	
	/**
	 * @param pgReturnType result is returned by reference
	 * @param strResult is used to resolve result ajax,  *.ftl, *.vm, custom *.page everything else is dispatcher (*.jsp) 
	 */
	public void resolveResult(PageReturnType pgReturnType, String strResult){
		if("".equals(strResult) || "ajax".equals(strResult)){
			pgReturnType.resultName = strResult;
		}else if(strResult.endsWith("page")){
			pgReturnType.resultName = "customXMLRes";
			pgReturnType.resultPage = strResult;
			pgReturnType.nextScreenName = strResult.substring(0,strResult.length() - 5);
		}else if(strResult.endsWith("ftl")){
			pgReturnType.resultName = "freemarker";
			pgReturnType.resultPage = strResult;
			pgReturnType.nextScreenName = strResult.substring(0,strResult.length() - 4);
		}else if(strResult.endsWith("vm")){
			pgReturnType.resultName = "velocity";
			pgReturnType.resultPage = strResult;
			pgReturnType.nextScreenName = strResult.substring(0,strResult.length() - 3);
		}else{
			pgReturnType.resultName = "dispatcher";
			pgReturnType.resultPage = strResult;
			pgReturnType.nextScreenName = strResult.substring(0,strResult.lastIndexOf('.'));
		}
	}
	
}
