package com.ycs.fe.crud;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.json.JSONException;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.ycs.fe.commandprocessor.BaseCommandProcessor;
import com.ycs.fe.commandprocessor.CommandProcessorResolver;
import com.ycs.fe.dao.FETranslatorDAO;
import com.ycs.fe.dto.InputDTO;
import com.ycs.fe.dto.PrepstmtDTOArray;
import com.ycs.fe.dto.ResultDTO;
import com.ycs.fe.dto.PrepstmtDTO.DataType;
import com.ycs.fe.util.Constants;
import com.ycs.fe.util.ScreenMapRepo;

/**
 * Used for prepopulating data onto value stack for. It can be called from any Action class or Interceptor. 
 * @author Samarjit
 *
 */
public class SelectOnLoad {
	private Logger logger = Logger.getLogger(this.getClass());
	
	public void selectOnLoad(String screenName1, JSONObject jsonsubmitdata ){
		
		try {
			if (Constants.APP_LAYER == Constants.FRONTEND) {
				Element rootXml = ScreenMapRepo.findMapXMLRoot(screenName1);
				Node sessionVar = rootXml
						.selectSingleNode("/root/screen/sessionvars");
				if (sessionVar != null) {
					String strSessionVar = sessionVar.getText();
					Map<String, String> sessionMap = new HashMap<String, String>();
					if (strSessionVar != null || !"".equals(strSessionVar)) {
						String[] arSessionVar = strSessionVar.split(",");
						if (arSessionVar.length > 0) {
							for (String sessVariable : arSessionVar) {
								String[] sessionField = sessVariable.trim()
										.split("\\|");
								String sessionData = "";
								if (sessionField.length > 1) {
									//datatype is defined and it is required
									sessionData = (String) ServletActionContext
											.getContext().getSession()
											.get(sessionField[0]);
									System.out.println("sessionData:"
											+ sessionData);
									if (sessionField[1].equals("INT")) {
										sessionData.matches("0-9");
										//TODO some data validation

									}
								}
								sessionMap.put(sessionField[0], sessionData);
							}
						}
					}
					if(jsonsubmitdata == null)jsonsubmitdata = new JSONObject();
					jsonsubmitdata.put("sessionvars", sessionMap);
				}
			}
		} catch (Exception e) {
			logger.debug("error in setting sessionvars",e);
		}
		if(Constants.CMD_PROCESSOR == Constants.APP_LAYER){
			localSelectOnLoad(  screenName1,   jsonsubmitdata );
		}else{
			remoteSelectOnLoad(  screenName1,   jsonsubmitdata.toString() );
		}
		
	}
	
	public void localSelectOnLoad(String screenName1, JSONObject jsonsubmitdata ){
		String xmlconfigfile =  ScreenMapRepo.findMapXMLPath(screenName1);
		if(screenName1 != null && screenName1.length() >0)	{
			try {
				org.dom4j.Document document1 = new SAXReader().read(xmlconfigfile);
				org.dom4j.Element root = document1.getRootElement();
				HashMap<String, Object> adhocstackids = new HashMap<String, Object>();
				//preload select queries
				List nodeList = root.selectNodes("//query");
				logger.debug("query list size:"+nodeList.size());
				for (Iterator queryList = nodeList.iterator(); queryList.hasNext();) {
					org.dom4j.Node node = (org.dom4j.Node) queryList.next();
					logger.debug("Query Node:"+node.getText());
					String stackid = ((org.dom4j.Element) node).attributeValue("stackid");
					String type = ((org.dom4j.Element) node).attributeValue("type");
					String sqlquery = node.getText();
					FETranslatorDAO feDAO = new FETranslatorDAO();
					feDAO.executequery(sqlquery,stackid,type); //outputs in different stack ids
					org.dom4j.Element e = (org.dom4j.Element) node;
					adhocstackids.put("adhocstackids",stackid);
				}
				//preload selectonload queries
			/*	List selonloadnl = root.selectNodes("//selectonload");
				Element elm = (Element) root.selectSingleNode("/root/screen");
				String screenName = elm.attributeValue("name");
				logger.debug("query selectonload list size:"+selonloadnl.size());*/
				
				/////command onload ////
				Element onloadElm = (Element) root.selectSingleNode("/root/screen/commands/onload");
				String commandChain = onloadElm.attributeValue("opt");
				String[] opts = commandChain.split("\\|");
				ResultDTO resultDTO = new ResultDTO();
				InputDTO inputDTO = new InputDTO();
				inputDTO.setData(jsonsubmitdata);
				
				resultDTO.setData(adhocstackids);
				
				for (String opt : opts) {
	    			String[] sqlcmd = opt.split("\\:"); //get Id of query 
	    			String querynodeXpath =  sqlcmd[0]+"[@id='"+sqlcmd[1]+"']"; //Query node xpath
	    			Element processorElm = (Element) root.selectSingleNode("/root/screen/*/"+querynodeXpath+" ");
	    			String strProcessor = processorElm.getParent().getName();
	    		    BaseCommandProcessor cmdProcessor =  CommandProcessorResolver.getCommandProcessor(strProcessor);
					resultDTO = cmdProcessor.processCommand(screenName1, querynodeXpath, null, inputDTO, resultDTO);				
	    		    //resDTO = rpc.selectData(  screenName,   null, querynodeXpath ,   (JSONObject)jsonRecord);
	    		}
				ActionContext.getContext().getValueStack().set("resDTO",new Gson().toJson(resultDTO).toString());
				System.out.println("SelectOnLoad::"+ new Gson().toJson(resultDTO).toString());
				/////end command onload ////
				
				/*
				for (Iterator queryList = selonloadnl.iterator(); queryList.hasNext();) {
					org.dom4j.Node queryNode = (org.dom4j.Node) queryList.next();
					logger.debug("Query Node:"+queryNode.getText());
					String stackid = ((org.dom4j.Element) queryNode).attributeValue("outstack");
					String type = ((org.dom4j.Element) queryNode).attributeValue("type");
					String sqlquery = queryNode.getText();
					
					Element errorNode = (Element) queryNode.selectSingleNode("error");
					String errorTemplate = "";
					if(errorNode !=null)errorTemplate=errorNode.attributeValue("message");
					
					Element messageNode = (Element) queryNode.selectSingleNode("message");
					String messageTemplate = "";
					if(messageNode !=null)messageTemplate=messageNode.attributeValue("message");
					
					List<Element> nl = root.selectNodes("//fields/field/*");
					HashMap<String, DataType> hmfielddbtype= new HashMap<String, DataType>();
					QueryParser.populateFieldDBType(nl, hmfielddbtype);
					
					PrepstmtDTOArray arparam = new PrepstmtDTOArray();
					String parsedquery = QueryParser.parseQuery(sqlquery, null, jsonsubmitdata, arparam, hmfielddbtype);
					logger.debug("selonload Query query:"+parsedquery+"\n Expanded prep:"+arparam.toString(parsedquery));
					FETranslatorDAO feDAO = new FETranslatorDAO();
					ResultDTO resDTO = feDAO.executecrud(screenName,parsedquery,stackid,jsonsubmitdata, arparam, errorTemplate, messageTemplate );
					resDTO.merge(resultDTO);
					logger.debug("resDTO (gson converter)= "+new Gson().toJson(resDTO).toString());
					logger.debug("resDTO (JSONSerializer converter)= "+JSONSerializer.toJSON(resDTO).toString());
					ActionContext.getContext().getValueStack().set("resDTO",new Gson().toJson(resDTO).toString());
					ActionContext.getContext().getValueStack().getContext().put("ZHello", "World");
					ActionContext.getContext().getValueStack().set("ZHello2", "World2");
					org.dom4j.Element e = (org.dom4j.Element) queryNode;
					System.out.println("HTMLProcessor **************** populating value stack");
				}
				*/
			} catch (DocumentException e) {
				logger.debug("result xml file not readable --",e);
				e.printStackTrace();
//			} catch (JSONException e) {
//				logger.debug("result xml file not readable --",e);
//				e.printStackTrace();
			} catch (Exception e) {
				logger.debug("result xml file not readable --",e);
				e.printStackTrace();
			}
			
			 
			 
			
		}
	}
	
	public void remoteSelectOnLoad(String screenName1, String jsonsubmitdata ){
		throw new NotImplementedException();
	}
}
