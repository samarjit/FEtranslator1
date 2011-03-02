<%@ taglib uri="/struts-tags" prefix="struts2"%>
<html>
<head>
</head>
<body>
My Result
<struts2:form method="post" action="testxsl.action" >
<table>
<tr><td><struts2:textfield key="testHeader"
                   label="Enter Header :"/></td></tr>
<tr><td><struts2:textfield key="testFooter"
                   label="Enter Footer :"/></td></tr>
<tr><td colspan="2"><struts2:submit value="Register"/></td></tr>
</table>
</struts2:form>

My jsp map Result
<struts2:form method="post" action="jspmap.action" >
<table>
<tr><td><struts2:textfield key="testHeader"
                   label="Enter Header :"/></td></tr>
<tr><td><struts2:textfield key="testFooter"
                   label="Enter Footer :"/></td></tr>
<tr><td colspan="2"><struts2:submit value="Register"/></td></tr>
</table>
</struts2:form>

XSL result
<struts2:form method="post" action="testxsl1.action" >
<table>
<tr><td><struts2:textfield key="testHeader"
                   label="Enter Header :"/></td></tr>
<tr><td><struts2:textfield key="testFooter"
                   label="Enter Footer :"/></td></tr>
<tr><td colspan="2"><struts2:submit value="Register"/></td></tr>
</table>
</struts2:form>

</body>
</html>