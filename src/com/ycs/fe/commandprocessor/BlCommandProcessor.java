package com.ycs.fe.commandprocessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.google.gson.JsonArray;
import com.sun.mirror.util.Types;
import com.ycs.fe.dto.InputDTO;
import com.ycs.fe.dto.ResultDTO;
import com.ycs.fe.util.ParseSentenceOgnl;
import com.ycs.fe.util.ScreenMapRepo;
import com.ycs.fe.util.SentenceParseException;

public class BlCommandProcessor implements BaseCommandProcessor {
	private static Logger logger = Logger.getLogger(BlCommandProcessor.class);

	@Override
	public ResultDTO processCommand(String screenName, String querynodeXpath, JSONObject jsonRecord, InputDTO inputDTO, ResultDTO resultDTO) {
		logger.debug("Currently processing record:" + jsonRecord.toString());
		HashMap<String, Object> data = new HashMap<String, Object>();
		resultDTO = new ResultDTO();
//		try {
//
//			String resultJsonConf = "{'class':'com.ycs.fe.businesslogic.CmsBL','method':'cardOrder','param'=[{'Hello'}]}";
//
//			String xmlPath = ScreenMapRepo.findMapXMLPath(screenName);
//			org.dom4j.Document xmldoc = new SAXReader().read(xmlPath);
//			org.dom4j.Element rootele = xmldoc.getRootElement();
//			Node crudnode = rootele.selectSingleNode("/root/screen/bl");
//			Node queryNode = crudnode.selectSingleNode(querynodeXpath);
//
//			Element rootXml = ScreenMapRepo.findMapXMLRoot(screenName);
//			Node selectSingleNode = rootXml.selectSingleNode(querynodeXpath);
//
//			String xmlText = queryNode.getText();
//			String cmdDetails = ParseSentenceOgnl.parse(xmlText, jsonRecord);
//			JSONObject jsonObj = JSONObject.fromObject(cmdDetails);
//
//			String className = null;
//			String method = null;
//			JSONArray param = null;
//			if (jsonObj.containsKey("className"))
//				className = jsonObj.getString("className");
//
//			if (jsonObj.containsKey("className"))
//				method = jsonObj.getString("method");
//
//			if (jsonObj.containsKey("param"))
//				param = jsonObj.getJSONArray("param");
//
//		
//			if (className != null && method != null) {
//				Class<?> c = Class.forName(className);
//				Object t = c.newInstance();
//				
//				Class<?>[] args1 = new Class[2];
//	            args1[0] = String.class;
//	            args1[1] = InputDTO.class;
//				
//			Method mm = c.getMethod(method, args1);
//			mm.invoke(t, "Helloo",null);
//			
////				Method[] allMethods = c.getDeclaredMethods();
////				for (Method m : allMethods) {
////					String mname = m.getName();
////					if (mname.equals(method)) {
////						System.out.println("m.getGenericReturnType()" + m.getGenericReturnType());
////						Type[] pType = m.getGenericParameterTypes();
////						for (Type arg : pType) {
////							System.out.println("Parameter :" + arg);
////						}
////						m.setAccessible(true);
////						Object o = m.invoke(t, param);
////						System.out.println("output : " + o.toString());
////					}
////				}
//			}
//
//			System.out.println(jsonObj);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		} catch (SentenceParseException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}
	
	public static void main(String[] args){
		BlCommandProcessor bl =  new BlCommandProcessor();
		bl.processCommand("cardOrder", "buslogic[@id='plasticDetails']", null , null , null);
	}

}
