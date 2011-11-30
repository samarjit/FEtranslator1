<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String ctxstr = request.getContextPath(); %>

<body>
<h2>Index</h2><br/>
<hr/>

Debugging tools

<a href="<%=ctxstr%>/config-browser/actionNames.action?namespace=%2Fconfig-browser" >config browser</a>|
<a href="<%=ctxstr%>/engine.action?debug=browser" >debug browser</a>	<br/>
<a href="http://localhost:8082/">H2 testing database Console</a>
<hr/>
<h2><a href="<%=ctxstr%>/file.action">XML file sync (everytime required after a server restart)</a><br/></h2>


<a href="cms/login.jsp">Card Management System Login</a> <br>

<a href="cms/cmsAction.action?screenName=locale">Set Language</a> <br>



<a href="<%=ctxstr%>/pages/gridtest.jsp">Grid Demo</a><br/>
<a href="<%=ctxstr%>/pages/filebrowser.jsp">filebrowser</a><br/>
<a href="<%=ctxstr%>/logout.jsp">logout</a><br/>
 <br/><br/>
Old demo: 
<div style="display:block;" readOnly="readOnly">
<a href="html/programsetupview.action">Program Setup (Demo)</a> <br/>
<a href="pages/jaxbtest.action">Program Setup Admin - under construction</a> <br/><br/>
</div>
 
</body>
</html>
