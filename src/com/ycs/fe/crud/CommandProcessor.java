package com.ycs.fe.crud;

import java.util.Set;

import map.ScreenMapRepo;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Element;

import com.ycs.fe.dto.ResultDTO;

public class CommandProcessor {

	/**
	 * command="jrpcCmd1" should be present in each record see in submitdata data structure
	 * 
	 * submitdata={"form1":[{"row":0,"programname":"LOYCARD","txtnewprogname":"LOYCARD","txtprogramdesc":"Loyalty Card Program",
	 * "issuername":"HSBC Bank","countryofissue":"SINGAPORE","txtstatus":"Modify",command:"jrpcCmd1"},{"row":1,"programname":"TRACARD",
	 * "txtnewprogname":"TRACARD","txtprogramdesc":"Travel Card Program","issuername":"HSBC Bank","countryofissue":"SINGAPORE",
	 * "txtstatus":"Modify",command:"jrpcCmd1"}],“txnrec”:{single:””,multiple:[{aaa:’’},{aaa:’’}]}}
	 * 
	 * @param command
	 * @param submitdataObj
	 * @param screenName 
	 * @return
	 */
	public ResultDTO commandProcessor( JSON submitdataObj, String screenName){
		JsrpcPojo rpc = new JsrpcPojo();
		Element rootXml = ScreenMapRepo.findMapXMLRoot(screenName);
		
		ResultDTO resDTO = null;
		
			
		    @SuppressWarnings("unchecked")
			Set<String>  itr =  ( (JSONObject) submitdataObj).keySet();
		    for (String dataSetkey : itr) {
		    	JSONArray dataSetJobj = ((JSONObject) submitdataObj).getJSONArray(dataSetkey);
		    	for (Object jsonPart : dataSetJobj) {
		    		String cmd = ((JSONObject) jsonPart).getString("command");
		    		Element elmCmd = (Element) rootXml.selectSingleNode("//commands/cmd[@name='"+cmd+"' ] ");
		    		System.out.println("//commands/cmd[@name='"+cmd+"' ] ");
		    		String instack = elmCmd.attributeValue("instack");
		    		String operation = elmCmd.attributeValue("opt");
		    		String[] opts = operation.split("\\|");
		    		for (String opt : opts) {
		    			String[] sqlcmd = opt.split("\\:");
		    			String querynode =  sqlcmd[0]+"[@id='"+sqlcmd[1]+"']";
		    		resDTO = rpc.selectData(  screenName,   null, querynode ,   (JSONObject)jsonPart);
		    		}
		    	}
			}
		 
		return resDTO;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
