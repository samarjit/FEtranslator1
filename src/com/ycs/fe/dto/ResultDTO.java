package com.ycs.fe.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class ResultDTO {

private List<String> messages;
private List<String> errors;
private HashMap<String, Object>  data;
private Map<String,Map<String,Integer>> pagination; //{currentpage:,totalpage:,pagesize:}
 

public ResultDTO() {
	data = new HashMap<String,Object>();
	errors = new ArrayList<String>();
	messages = new ArrayList<String>();
	pagination = new HashMap<String, Map<String,Integer>>();
	HashMap<String, Integer> hm = new HashMap<String, Integer>();
	hm.put("currentpage",1);
	hm.put("totalrec",1);
	hm.put("pagesize",1);
	pagination.put("formx", hm);
}


public void addError(String e){
	errors.add(e);
}

public void addMessage(String m){
	messages.add(m);
}

//public String toString(){
//	return "MESSAGE: from toString()";//+messages+",ERRORS:"+errors+",DATA:"+data;
//}


public void setPageDetails(String panelname,int currentpage, int totalpages, int pagesize) {
	 if(pagination ==null)pagination = new HashMap<String, Map<String,Integer>>();
	 Map<String, Integer> hm = pagination.get(panelname);
	 if(hm != null ){
		 hm.put("currentpage",currentpage);
		 hm.put("totalrec",totalpages);
		 hm.put("pagesize",pagesize);
	 }else{
		 hm = new HashMap<String, Integer>();
		 hm.put("currentpage",currentpage);
		 hm.put("totalrec",totalpages);
		 hm.put("pagesize",pagesize);
	 }
	 pagination.put(panelname, hm);
}

public List<String> getMessages() {
	return messages;
}
public void setMessages(List<String> messages) {
	this.messages = messages;
}
public List<String> getErrors() {
	return errors;
}
public void setErrors(List<String> errors) {
	this.errors = errors;
}
public HashMap<String, Object>  getData() {
	return data;
}
public void setData(HashMap<String, Object>  jobj) {
	this.data = jobj;
}

private void setPagination(Map<String, Map<String, Integer>> pagination) {
	this.pagination = pagination;
}
public Map<String, Map<String, Integer>> getPagination() {
	return pagination;
}

/**
 * Merges tempDTO into this object. It overrites previous object with same outstack for pagination. Like previous pagination 
 * with form1:{val1:1} will be overwritten by input form1:{val2:2}. It wont become form1:{val1:1,val2:2} instead the output will be form1:{val2};
 * But with "data", an attempt will be made to merge with depth 2. So the above case will be merged in case of data. But if this fails, it will fall back 
 * and act like pagination merge.
 *  
 * @param tempDTO
 */
 
@SuppressWarnings("unchecked")
public void merge(ResultDTO tempDTO){
	

	HashMap<String, Object> tempdata = tempDTO.getData();
	Map<String, Map<String, Integer>> temppagination = tempDTO.getPagination();
	for (String keyi : tempdata.keySet()) {
		Object val = tempdata.get(keyi);
		Object thisdataval = null;
		if(data.containsKey(keyi))
			thisdataval  = data.get(keyi);
		
		if(thisdataval!=null && val instanceof List<?> && thisdataval instanceof List<?> ){
			((List<Map<String,String>>)thisdataval).addAll((List<Map<String, String>>) val); 
		}else{
			data.putAll(tempDTO.getData());
		}
	}
	
	
	errors.addAll(tempDTO.getErrors());
	messages.addAll(tempDTO.getMessages());
	pagination.putAll(tempDTO.getPagination());
}

public static ResultDTO fromJsonString(JSONObject resDTOjson){
	ResultDTO tempDTO = new ResultDTO();
	 HashMap<String,Object> tmpHm = new HashMap<String, Object>();
	 JSONObject data1 = resDTOjson.getJSONObject("data");
	 tmpHm.putAll(data1);
	 tempDTO.setData(tmpHm);
	 tempDTO.setErrors(resDTOjson.getJSONArray("errors"));
	 tempDTO.setMessages(resDTOjson.getJSONArray("messages"));
	 Map<String, Map<String,Integer>> pagination =   (Map<String, Map<String, Integer>>) resDTOjson.getJSONObject("pagination");
	 System.out.println(pagination);
	 tempDTO.setPagination(pagination);
	 
	return tempDTO;
	
}

}
