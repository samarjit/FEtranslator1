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
Scripts:<s:textfield name="screenroot.screen.scripts.scriptincludeOrText.name"  maxlength="200"/><br/>
Scriptinclude: <s:textfield name="screenroot.screen.scripts" maxlength="200"/><br/>
<s:iterator value="screenroot.screen.scripts.scriptincludeOrText"><br/>
 <s:if test="value != null  ">  scriptinclude is:<s:div rows="2" cols="80"  name="value"  ><pre><s:property value ="value"  /></pre> <s:property value ="name"  /> </s:div>
  </s:if>
</s:iterator><br/>
<s:iterator value="screenroot.screen.stylesheets.styleincludeOrText"><br/>
 <s:if test="value != null">  styleinclude is:<s:textarea rows="2" cols="80"  name="value"/> <s:property value ="name"  /> 
  </s:if>
</s:iterator><br/>

Commands
<s:iterator value="screenroot.screen.commands.cmd">
  cmd: <s:property value="name" />, Opt=<s:property value="opt" />, instack=<s:property value="instack" />, processor=<s:property value="processor" /><br/>
</s:iterator>
crud<br/>
<s:iterator value="screenroot.screen.crud.jsonrpc">
  jsonrpc: <s:property value="outstack" />, id=<s:property value="id" />, outstack=<s:property value="outstack" />, 
  content= 
  <s:iterator value="content" var="me">
     <s:if test="%{#me instanceof java.lang.String}">
         <s:property  value="toString()"/> <br/>
     </s:if>
     <s:if test="%{#me instanceof map.generated.Countquery}">
     <b>Count Query:</b>   PageSize= <s:property  value="pagesize"/> <br/><s:property  value="content"/> <br/>
     </s:if>
     <s:if test="%{#me instanceof map.generated.Error}">
         Error message=<s:property  value="message"/> <br/>
     </s:if>
     <s:if test="%{#me instanceof map.generated.Message}">
        Message= <s:property  value="message"/> <br/>
     </s:if>
  </s:iterator>
</s:iterator>

<s:iterator value="screenroot.screen.crud.selectonload">
  selectonload: <s:property value="outstack" />, id=<s:property value="id" />, outstack=<s:property value="outstack" />, 
  content= 
  <s:iterator value="content" var="me">
     <s:if test="%{#me instanceof java.lang.String}">
         <s:property  value="toString()"/> <br/>
     
     </s:if>
  </s:iterator>
</s:iterator>

<s:iterator value="screenroot.screen.crud.sqlselect">
  sqlselect: <s:property value="outstack" />, id=<s:property value="id" />, outstack=<s:property value="outstack" />, 
  content= 
  <s:iterator value="content" var="me">
     <s:if test="%{#me instanceof java.lang.String}">
         <s:property  value="toString()"/> <br/>
     
     </s:if>
  </s:iterator>
</s:iterator>

<s:iterator value="screenroot.screen.crud.sqlinsert">
  sqlinsert: <s:property value="outstack" />, id=<s:property value="id" />, outstack=<s:property value="outstack" />, 
  content= 
  <s:iterator value="content" var="me">
     <s:if test="%{#me instanceof java.lang.String}">
         <s:property  value="toString()"/> <br/>
     
     </s:if>
  </s:iterator>
</s:iterator>

<s:iterator value="screenroot.screen.crud.sqldelete">
  sqldelete: <s:property value="outstack" />, id=<s:property value="id" />, outstack=<s:property value="outstack" />, 
  content= 
  <s:iterator value="content" var="me">
     <s:if test="%{#me instanceof java.lang.String}">
         <s:property  value="toString()"/> <br/>
     
     </s:if>
  </s:iterator>
</s:iterator>

<s:iterator value="screenroot.screen.crud.sqlupdate">
  sqlupdate: <s:property value="outstack" />, id=<s:property value="id" />, outstack=<s:property value="outstack" />, 
  content= 
  <s:iterator value="content" var="me">
     <s:if test="%{#me instanceof java.lang.String}">
         <s:property  value="toString()"/> <br/>
     
     </s:if>
  </s:iterator>
</s:iterator>

<s:iterator value="screenroot.screen.dm.txnproc">
TXNPROC:  <s:property  value="id"/> <br/>
</s:iterator>

<s:iterator value="screenroot.screen.bl.buslogic">
BUSINESS LOGIC: <s:property  value="id"/> <br/>
</s:iterator>

<s:iterator value="screenroot.screen.anyprocs.proc">
ANY PROC: Id=<s:property  value="id"/> XML=<s:property  value="xml"/>
 DATA=<s:property  value="content" /><br/>
</s:iterator>


<s:property value="screenroot.panels.panel[0].id"/><br/>
<s:iterator value="screenroot.panels.panel" >
   <s:property value="id" />
   <s:iterator value="buttonOrFieldsOrTestscriptlet">
       <em><s:property value="getClass().getName()" /></em><br/>
       <s:if test="getClass().getName() == 'map.generated.Fields'">
         <s:iterator value="field">
           <s:property value="compositefieldOrCustomfieldOrDiv[0].name" />  
            <s:property value="compositefieldOrCustomfieldOrDiv[0].column" />  
            <s:property value="compositefieldOrCustomfieldOrDiv[0].dbdatatype" />
            <s:if test="compositefieldOrCustomfieldOrDiv[0].rule.length() > 0">
            <br/><pre>Rule=<s:property value="compositefieldOrCustomfieldOrDiv[0].rule" /></pre>
            </s:if>
            <s:if test="compositefieldOrCustomfieldOrDiv[0].query.content.length() >0">
            <br/><pre>Query=<s:property value="compositefieldOrCustomfieldOrDiv[0].query.content" /></pre>
            </s:if>
            <s:if test="compositefieldOrCustomfieldOrDiv[0].validation.length()  > 0">
            <br/><pre>Validation=<s:property value="compositefieldOrCustomfieldOrDiv[0].validation" /></pre>
            </s:if>
            <s:if test="compositefieldOrCustomfieldOrDiv[0].text != ''">
            <br/><pre>Text=<s:property value="compositefieldOrCustomfieldOrDiv[0].text" /></pre>
            </s:if>
             <br/>
         </s:iterator>
       </s:if>
       <s:iterator value=""/>
   </s:iterator>
</s:iterator>
<button>Submit Change</button>
</s:form>
</body>
</html>
