package com.ycs.fe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Element;
import org.dom4j.Node;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;
import com.opensymphony.xwork2.ValidationAwareSupport;
import com.ycs.fe.DataTypeException;
import com.ycs.fe.dto.PrepstmtDTO;
import com.ycs.fe.dto.PrepstmtDTO.DataType;
import com.ycs.fe.dto.ResultDTO;

public class FEValidator  implements LocaleProvider{

	private static Logger logger = Logger.getLogger(FEValidator.class);
	private TextProvider textProvider;

	public ResultDTO validate(String screenName, JSONObject submitdataObj) throws DataTypeException {
		Element rootElm = ScreenMapRepo.findMapXMLRoot(screenName);
		Map<String,Object> s = (Map<String,Object>)submitdataObj;
		for (Entry<String, Object> itr : s.entrySet()) { //form1, form2 ...skip txnrec, sessionvars, bulkcmd
			if(itr.getKey().equals("sessionvars")){
				//These will be most likely have not yet been set, validate directly from sessoin variable
				JSONObject sessvars = (JSONObject) itr.getValue();
				System.out.println("Need to validate these:"+ sessvars);
			}else if(itr.getKey().equals("bulkcmd")){
				//do nothing
			}else if(itr.getKey().equals("txnrec")){
				JSONObject txnrec = (JSONObject) itr.getValue();
				JSONObject singlerec = (JSONObject) txnrec.getJSONObject("single");
				for (Iterator keyitr = singlerec.keys(); keyitr.hasNext();) {
					String keystr = (String) keyitr.next();
					validateNode(rootElm, singlerec, keystr, null);
				} 
				JSONArray armultirec = txnrec.getJSONArray("multiple");
				for (Iterator iterator = armultirec.iterator(); iterator.hasNext();) { //rows
					JSONObject joMulti = (JSONObject) iterator.next(); //each row
					for (Iterator keyitr = joMulti.keys(); keyitr.hasNext();) {
						String keystr = (String) keyitr.next();
						validateNode(rootElm, joMulti, keystr, null);
					} 
				}  
			}else{ //form1,form2 ... data vaalidation
				JSONArray rows =  (JSONArray) itr.getValue();
				for (Iterator iterator = rows.iterator(); iterator.hasNext();) { //rows
					JSONObject joMulti = (JSONObject) iterator.next(); //each row
					for (Iterator keyitr = joMulti.keys(); keyitr.hasNext();) {
						String keystr = (String) keyitr.next();
						validateNode(rootElm, joMulti, keystr, null);
					} 
				}
			}
		}
		
		validateSessionVariables(screenName);
		return null;
	}

	private void validateSessionVariables(String screenName) throws DataTypeException {
		Element rootElm = ScreenMapRepo.findMapXMLRoot(screenName);
		Node sessionVar = rootElm.selectSingleNode("/root/screen/sessionvars");
		   if(sessionVar != null){
			String strSessionVar = sessionVar.getText();
			Map<String, String> sessionMap = new HashMap<String,String>();
			if(strSessionVar != null || !"".equals(strSessionVar)){
				String[] arSessionVar = strSessionVar.split(",");
				if(arSessionVar.length >0){
					for (String sessVariable : arSessionVar) {
							String[] sessionField = sessVariable.trim().split("\\|");
						String sessionData = "";
						if(sessionField.length >1){
							//datatype is defined and it is required
								sessionData = (String) ServletActionContext.getContext().getSession().get(sessionField[0]);
								System.out.println("sessionData:"+sessionData);
//							if(sessionField[1].equals("INT")){
//								sessionData.matches("0-9");
//							}
							JSONObject sessionJson = new JSONObject();
							sessionJson.put(sessionField[0], sessionData);
							validateNode(rootElm, sessionJson, sessionField[0], sessionField[1]);
						}
						sessionMap.put(sessionField[0], sessionData);
					}
				}
			}
		   }
	}

	private void validateNode(Element rootElm, JSONObject singlerec,
			String keystr, String overrideDatatype) throws DataTypeException {
		Element fieldNode = (Element) rootElm.selectSingleNode("/root/panels/panel/fields/field/*[@name='"+keystr+"']");
		if (fieldNode != null) {
			String strdbdatatype = fieldNode.attributeValue("dbdatatype");
			String strdbcolsize = fieldNode.attributeValue("dbcolsize");
			String strmandatory = fieldNode.attributeValue("mandatory");
			int colsize = -1;
			
			if(overrideDatatype != null && !"".equals(overrideDatatype)){
				strdbdatatype = overrideDatatype;
			}
			
			if (strdbcolsize != null && !"".equals(strdbcolsize)) {
				colsize = Integer.parseInt(strdbcolsize);
			}
			DataType dbdatatype = PrepstmtDTO.getDataTypeFrmStr(strdbdatatype);
			if (dbdatatype == DataType.STRING) {
				System.out.println("Validating string.....");
				addError("error.numberformat", keystr,
						singlerec.getString(keystr));
				//filtering criterion required?
			} else if (dbdatatype == DataType.INT) {
				try {
					Integer.parseInt(singlerec.getString(keystr));
				} catch (NumberFormatException e) {
					addError("error.numberformat", keystr,
							singlerec.getString(keystr));
				}
			} else if (dbdatatype == DataType.FLOAT) {
				try {
					Float.parseFloat(singlerec.getString(keystr));
				} catch (NumberFormatException e) {
					addError("error.float", keystr, singlerec.getString(keystr));
				}
			} else if (dbdatatype == DataType.DOUBLE) {
				try {
					if (singlerec.getString(keystr) != null)
						Double.parseDouble(singlerec.getString(keystr));
				} catch (NumberFormatException e) {
					addError("error.double", keystr,
							singlerec.getString(keystr));
				}
			} else if (dbdatatype == DataType.DATEDDMMYYYY) {
				try {
					if (singlerec.getString(keystr) != null)
						new SimpleDateFormat("DD/MM/yyyy").parse(singlerec
								.getString(keystr));
				} catch (ParseException e) {
					addError("error.dateDDMMyyyy", keystr,
							singlerec.getString(keystr));
				}
			} else if (dbdatatype == DataType.DATE_NS) {
				try {
					if (singlerec.getString(keystr) != null)
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
								.parse(singlerec.getString(keystr));
				} catch (ParseException e) {
					addError("error.date_ns", keystr,
							singlerec.getString(keystr));
				}
			} else if (dbdatatype == DataType.TIMESTAMP) {
				try {
					if (singlerec.getString(keystr) != null)
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
								.parse(singlerec.getString(keystr));
				} catch (ParseException e) {
					addError("error.timestamp", keystr,
							singlerec.getString(keystr));
				}
			}
			if (strmandatory != null
					&& ("yes".equals(strmandatory) || "true"
							.equals(strmandatory))) {
				if (singlerec.getString(keystr).length() < 1) {
					addError("error.mandatory", keystr,
							singlerec.getString(keystr));
				}
			}
			if (colsize != -1 && singlerec.getString(keystr).length() > colsize) {
				addError("error.colsize", keystr, singlerec.getString(keystr));
			}
		}//field not is not defined for this key like 'command' 
		else{
			logger .debug("xml not defined for key="+keystr);
			System.out.println(getTextProvider().getText("global.username"));
		}
	}
	
	private void addError(String messageKey, String fieldName, String fieldValue) {
		String [] str2 = new String[]{ fieldName, fieldValue };
		System.out.println(getTextProvider().getText("error.testkey",str2 ));
	}

	private TextProvider getTextProvider()
	{
	  if (this.textProvider == null) {
	    TextProviderFactory tpf = new TextProviderFactory();
	     
	    this.textProvider = tpf.createInstance(super.getClass(), this);
	  }
	  return   this.textProvider;
	}

	@Override
	public Locale getLocale() {
		 ActionContext ctx = ActionContext.getContext();
		  if (ctx != null) {
		    return ctx.getLocale();
		  }
		  System.out.println("Action context not initialized");
		  return null;
	}
	

}