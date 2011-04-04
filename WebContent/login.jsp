<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
 
<%String ctxstr = request.getContextPath(); %>

 
</head>
<body>

 Property=<s:push  value="resultDTO" ><s:property value="data.form1[0]" /></s:push>  <br/>
 
 Context()=<s:property value="#resultDTO.data.form1[0].countryofissue" /> <br/>
 set()=<s:property value="resultDTO.data.form1[0].countryofissue" /> <br/>
 stack.push() <s:property value="data.form1[0].countryofissue" /> <br/>
</body>
</html>
