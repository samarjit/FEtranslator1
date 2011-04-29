<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ycs.workflow.actions.WorkflowController" %>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<s:head/>

<script type="text/javascript" >
function fnLogout(){
	
}
</script>
<head> 
<%String ctxstr = request.getContextPath(); %>

 
</head>
<body>
 
User: <s:property value="#session.userid"/> <br/>
Role: <s:iterator value="#session.roles">
	<s:property  value="roleId"/> ,
</s:iterator>
<br/>
Current Task: TASK1

<form action="logout.action" >
workflow Name=task1
<% WorkflowController wfc = new WorkflowController();
out.println(wfc.getNextTask("TASK1"));
%>
<button onclick="fnLogout()">Log out</button>
</form>

</body>
</html>
