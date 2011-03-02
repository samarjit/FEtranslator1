package com.ycs.fe.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultDTO {

private List<String> messages;
private List<String> errors;
private HashMap  data;
private HashMap<String,HashMap<String,Integer>> pagination; //{currentpage:,totalpage:,pagesize:}
 

public ResultDTO() {
	errors = new ArrayList<String>();
	messages = new ArrayList<String>();
	pagination = new HashMap<String, HashMap<String,Integer>>();
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

public String toString(){
	return "MESSAGE: from toString()";//+messages+",ERRORS:"+errors+",DATA:"+data;
}


public void setPageDetails(String panelname,int currentpage, int totalpages, int pagesize) {
	 if(pagination ==null)pagination = new HashMap<String, HashMap<String,Integer>>();
	 HashMap hm = pagination.get(panelname);
	 if(hm != null ){
		 hm.put("currentpage",currentpage);
		 hm.put("totalrec",totalpages);
		 hm.put("pagesize",pagesize);
	 }
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
public HashMap  getData() {
	return data;
}
public void setData(HashMap  jobj) {
	this.data = jobj;
}

public void setPagination(HashMap<String, HashMap<String, Integer>> pagination) {
	this.pagination = pagination;
}
public HashMap<String, HashMap<String, Integer>> getPagination() {
	return pagination;
}


}
