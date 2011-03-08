package com.ycs.fe.crud;

import java.util.HashMap;
import java.util.List;

import map.ScreenMapRepo;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.ycs.fe.DataTypeException;
import com.ycs.fe.dao.FETranslatorDAO;
import com.ycs.fe.dto.PrepstmtDTO;
import com.ycs.fe.dto.PrepstmtDTO.DataType;
import com.ycs.fe.dto.PrepstmtDTOArray;
import com.ycs.fe.dto.ResultDTO;

public class JsrpcPojo {
private Logger logger = Logger.getLogger(getClass()); 
	public ResultDTO selectData(String screenName, String panelname,  JSONObject jsonObject) {
		logger.debug("calling first default(first) sqlselect query");
		return selectData(screenName, panelname,"sqlselect", jsonObject);
	}
	
	public ResultDTO selectData(String screenName, String panelname,String querynode, JSONObject jsonObject) {
		 
		 
		    String pageconfigxml =  ScreenMapRepo.findMapXML(screenName);
//			String tplpath = ServletActionContext.getServletContext().getRealPath("WEB-INF/classes/map");
			String parsedquery = "";
			ResultDTO resultDTO = new ResultDTO();
			try {
				org.dom4j.Document document1 = new SAXReader().read(pageconfigxml);
				org.dom4j.Element root = document1.getRootElement();
				Node crudnode = root.selectSingleNode("//crud");
				Node queryNode = crudnode.selectSingleNode(querynode);
				if(queryNode == null)throw new Exception("<"+querynode+"> node not defined");
				
				String outstack = ((Element) queryNode).attributeValue("outstack"); 
				panelname = outstack;
				
				String updatequery = "";
				updatequery += queryNode.getText();
				
				Element errorNode = (Element) queryNode.selectSingleNode("error");
				String errorTemplate = "";
				if(errorNode !=null)errorTemplate=errorNode.attributeValue("message");
				
				Element messageNode = (Element) queryNode.selectSingleNode("message");
				String messageTemplate = "";
				if(messageNode !=null)messageTemplate=messageNode.attributeValue("message");
				
				List<Element> nodeList = crudnode.selectNodes("../fields/field/*");
				logger.debug("fields size:"+nodeList.size());
				HashMap<String, DataType> hmfielddbtype = new HashMap<String, PrepstmtDTO.DataType>();
				QueryParser.populateFieldDBType(nodeList, hmfielddbtype);
				
				/*Pattern pattern  = Pattern.compile(":(\\w*)",Pattern.DOTALL|Pattern.MULTILINE);
				Matcher m = pattern.matcher(updatequery);
				while(m.find()){
					String val = "";
					logger.debug(m.group(0)+ " "+ m.group(1));
					if(jsonObject.has(m.group(1))){
						val = jsonObject.getString(m.group(1));
						updatequery = updatequery.replaceAll(":"+m.group(1), val);
					}
				}*/
				//SET
				List<Element> primarykeys = crudnode.selectNodes("../fields/field/*[@primarykey]");
				
				
				PrepstmtDTOArray  arparam = new PrepstmtDTOArray();
				parsedquery = QueryParser.parseQuery(updatequery, panelname, jsonObject, arparam, hmfielddbtype);
				
			       logger.debug("JsonRPC query:"+parsedquery+"\n Expanded prep:"+arparam.toString(parsedquery));
			       FETranslatorDAO fetranslatorDAO = new FETranslatorDAO();
			       resultDTO = fetranslatorDAO.executecrud(screenName, parsedquery, panelname, jsonObject, arparam, errorTemplate,messageTemplate);
			       
			}catch(InvalidXPathException e){
				logger.debug("Exception caught in InsertData",e);
			} catch (DataTypeException e) {
				logger.debug("Exception caught in InsertData",e);
			} catch (JSONException e) {
				logger.debug("Exception caught in InsertData",e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.debug("Exception caught in InsertData",e);
				e.printStackTrace();
			}  
		return resultDTO;
	}

}
