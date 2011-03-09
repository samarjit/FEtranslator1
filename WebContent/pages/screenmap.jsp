<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/screenmap.css"> 
<s:set name="theme" value="'simple'" scope="page" />
<s:head/>
 
<%String ctxstr = request.getContextPath(); %>

 
</head>
<body>

  
 Context()=<s:property value="#resultDTO.data.form1[0].countryofissue" /> <br/>
 set()=<s:property value="resultDTO.data.form1[0].countryofissue" /> <br/>
 stack.push() <s:property value="data.form1[0].countryofissue" /> <br/>

Screen Name=<s:property value="screenroot.screen.name"/><br/>
Form:<s:form action="jaxbtest.action">
Screen Name:<s:textfield name="screenroot.screen.name"/><br/>
HTML Template Path:<s:textfield name="screenroot.screen.htmltemplate"/><s:property value="screenroot.screen.htmltemplate"/><br/>
Include JSP:<s:textfield name="screenroot.screen.includedjsp"/><br/>
Callback Class:<s:textfield name="screenroot.screen.callbackclass"/><br/>
Scripts:<s:textfield name="screenroot.screen.scripts.content[1].name"  maxlength="200"/><br/>
Scriptinclude: <s:textfield name="screenroot.screen.scripts" maxlength="200"/><br/>
<s:iterator value="screenroot.screen.scripts.content"><br/>
 <s:if test="value != null  ">  scriptinclude is:<s:div rows="2" cols="80"  name="value"  ><pre><s:property value ="value"  /></pre> <s:property value ="name"  /> </s:div>
  </s:if>
</s:iterator><br/>
<s:iterator value="screenroot.screen.stylesheets.content"><br/>
 <s:if test="value != null">  styleinclude is:<s:textarea rows="2" cols="80"  name="value"/> <s:property value ="name"  /> 
  </s:if>
</s:iterator><br/>
<s:property value="screenroot.panels.panel[0].id"/><br/>
<button>Submit Change</button>
</s:form>
</body>
</html>
