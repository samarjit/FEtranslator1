<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page import="java.util.ArrayList" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login</title>
<style>
.userid{
	color:#7e645a;
	background-color:#a5ebeb;
}
.password{
	color:#442b20;
	background-color:green;
}	
</style>
<s:head />
<sx:head />
</head>
<body>
User Id: <input type="text" id="userid" name="userid" class="userid"/>
Password: <input type="text" id="password" name="password" class="password" />
<button onclick="submitform()" id="loginbutton">Login</button>
	Hello test action
<a href="engine.action?retrievename=somename" >engine.action</a>	
<a href="<s:url action="index" namespace="config-browser" />">Launch the configuration browser</a>
<s:property value="name"/>


</body>
</html>