package com.ycs.fe.crud;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.ycs.fe.dto.PrepstmtDTO;
import com.ycs.fe.dto.PrepstmtDTOArray;
import com.ycs.fe.dto.PrepstmtDTO.DataType;

public class QueryParser{
	private static Logger logger = Logger.getLogger(QueryParser.class);
	
	/**
	 * @param nodeList List<org.dom4j.Element>[in]
	 * @param hmfielddbtype [out]
	 * @throws JSONException
	 * @throws Exception
	 */
	public static void populateFieldDBType(List<Element> nodeList, HashMap<String, DataType> hmfielddbtype) throws JSONException, Exception{
		for (Element nodeelm : nodeList) {
			String col = nodeelm.attributeValue("column");
			String fldname = nodeelm.attributeValue("name");
			String dbtype = nodeelm.attributeValue("dbdatatype");
			
			if(fldname != null && !"".equals(fldname)){
				if(dbtype != null  ){
//					updatequery += " "+col+"= TO_DATE('"+jsonObject.getString(fldname)+"', 'DD/MM/YYYY')";
					hmfielddbtype.put(fldname, PrepstmtDTO.getDataTypeFrmStr(dbtype)  );
				}else{
//					updatequery += " "+col+"='"+jsonObject.getString(fldname)+"',";
					hmfielddbtype.put(fldname, PrepstmtDTO.getDataTypeFrmStr("STRING"));
				}
			}
		}
	}
	
	/**
	 * Hash dependency on Action context for accessing ValueStack
	 * @param updatequery [in] Not null it is returned after replacemeent of :xxxx with '?' as i prepared statement
	 * @param panelname [in] can be null, then all parameters will be treated as Ognl expression  
	 * @param jsonObject [in] containing key/value pair of properties to be filled in query :xxxx can be null is there is no :xxx
	 * @param arparam [out]
	 * @param hmfielddbtype [in]
	 * @return updatequery is returned after replacemeent of :xxxx with '?' as i prepared statement
	 * @throws Exception
	 */
	public static String parseQuery(String updatequery,String panelname,JSONObject jsonObject, PrepstmtDTOArray  arparam, HashMap<String, DataType> hmfielddbtype) throws Exception{
		//Where
//		String updatewhere = crudnode.selectSingleNode("sqlwhere").getText();
		String PATTERN = "\\:(\\w*)\\[?(\\d*)\\]?\\.?([^,\\s\\|]*)\\|?([^,\\s]*)";
		
		Pattern   pattern = Pattern.compile(PATTERN,Pattern.DOTALL|Pattern.MULTILINE);
		
		logger.debug("Input Query:"+updatequery+" \nlength:"+updatequery.length());
		logger.debug("PATTERN="+PATTERN);
		
		Matcher m1 = pattern.matcher(updatequery); // get a matcher object
	       int count = 0;
	       int end = 0;
	       String parsedquery = "";
		while(m1.find()) {
	          
	          String prop =  m1.group();
	          logger.debug("Start preparing "+prop +" "+ m1.start() + " "+m1.end()+" "+m1.group(1)+" "+m1.group(2)+" "+m1.group(3)+" "+m1.group(4));
	          if(! "".equals(m1.group(2))){
	        	  if(panelname!=null && m1.group(1).equals(panelname)){ //:form[0].param === :param use jsonObject and get group(3) val 
	        		  //fill with present panel row object
	        		  String propname = m1.group(3);
	        		  String propval = "";
	        		  if(jsonObject.has(propname)){
	        			  propval = jsonObject.getString(propname);
	        			  parsedquery += updatequery.substring(end,m1.start());//
	        			  
	        			  if(!"".equals(m1.group(4)) ){
	        				  arparam.add(PrepstmtDTO.getDataTypeFrmStr(m1.group(4)),propval);
	        			  }else{
	        				  arparam.add(hmfielddbtype.get(propname),propval);
	        			  }
	        				   
	        			  
	        			 
	        			  parsedquery += "?";
	        		  }
	        		  end = m1.end(); 
	        		  logger.debug("This is not prefered Mode with dot"+m1.group(2)+". "+propname);
	        	  }else{ //:formXX[0].param
	        		  logger.debug("TODO:does it come here"+  m1.group(1)+" need to process Ognl with "+m1.group());
	        		  //TODO: implement for object filling from related panels.
	        		  String expr = m1.group();
	        		  expr = expr.substring(1); //remove ':' in :xxe[].xp
	        		  String propval = ActionContext.getContext().getValueStack().findString("#resultDTO.data."+expr);
	        		  logger.debug("Ognl Expression result="+propval);
	        		  String propname;
	        		  propname = expr.substring(expr.lastIndexOf('.')+1, expr.length());
	        		  logger.debug("property name="+propname);

	        		  if(!"".equals(m1.group(4)) ){
        				  arparam.add(PrepstmtDTO.getDataTypeFrmStr(m1.group(4)),propval);
        			  }else{
        				  arparam.add(hmfielddbtype.get(propname),propval);
        			  }
	        		  
	        		  parsedquery += updatequery.substring(end,m1.start());//
	        		  parsedquery += "?";
	        		  end = m1.end(); 
	        	  }
	          }else{ //fill with present panel row object :formxparam
	        	  String propval;
	        	  if(jsonObject!=null && jsonObject.has(m1.group(1)) ){
	        		  String propname = m1.group(1);
	        		  propval = jsonObject.getString(m1.group(1));
	        		  parsedquery += updatequery.substring(end,m1.start());//
					 
	        		  if(!"".equals(m1.group(4)) ){
        				  arparam.add(PrepstmtDTO.getDataTypeFrmStr(m1.group(4)),propval);
        			  }else{
        				  arparam.add(hmfielddbtype.get(propname),propval);
        			  }
        			   
	        		  parsedquery += "?";
	        	  }
	        	  
        		  end = m1.end(); 
        		  logger.debug("else no dot "+m1.group(1));
	          }
	          count++;
	       }
		   logger.debug("Last part end="+end);
	       parsedquery += updatequery.substring(end);
	       updatequery = parsedquery;
	       logger.debug("Parsed Query:"+ parsedquery);
	       return parsedquery;
	}
	
}