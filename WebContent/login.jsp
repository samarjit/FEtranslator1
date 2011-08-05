<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
</head>

<body>
<h1>Struts 2 localization example</h1>

<s:form action="loginPage.action" >
		<table>
			<tr>
				<td><s:textfield name="userid"  key="global.username" value="sam_admin"/></td>
			</tr>
			<tr>
				<td><s:password name="passwd" key="global.password" value="sam_admin"/></td>
			</tr>
			<tr>
				<td><s:submit key="global.submit" name="submit" /></td>
			</tr>
		</table>

		
</s:form>

<s:url id="localeEN" namespace="/" action="locale" >
   <s:param name="request_locale" >en</s:param>
</s:url>
<s:url id="localezhCN" namespace="/" action="locale" >
   <s:param name="request_locale" >zh_CN</s:param>
</s:url>
<s:url id="localeDE" namespace="/" action="locale" >
   <s:param name="request_locale" >de</s:param>
</s:url>
<s:url id="localeFR" namespace="/" action="locale" >
   <s:param name="request_locale" >fr</s:param>
</s:url>

<s:a href="%{localeEN}" >English</s:a>
<s:a href="%{localezhCN}" >Chinese</s:a>
<s:a href="%{localeDE}" >German</s:a>
<s:a href="%{localeFR}" >France</s:a>
 
</body>
</html>