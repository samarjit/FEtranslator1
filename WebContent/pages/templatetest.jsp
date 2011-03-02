<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page import="java.util.ArrayList" %>
<html xmlns="http://www.w3.org/1999/xhtml">
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
	background-color:#eeb0a2;;
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
<a href="engine.action" >engine.action</a>	
<a href="<s:url action="index" namespace="config-browser" />">Launch the configuration browser</a>
<sx:datetimepicker name="order.date" label="Order Date" displayFormat="dd-MMM-yyyy" />
<s:form action="some.action" method="post" theme="%{currentTheme}">
    <s:textfield label="First Name" name="firstName"/>
    <s:textfield label="Last Name" name="lastName"/>

    <s:textfield label="Address 1" name="address1"/>
    <s:textfield label="City" name="city"/>

    <s:textfield label="State" name="state"/>
    <s:textfield label="Zip" name="postalCode"/>

    <s:submit value="Create Party" align="center">
        <s:param name="colspan" value="%{tableColSpan}" />
        <s:param name="align" value="%{'center'}" />
    </s:submit>
    
    
    <s:textfield label="Requested Delivery Date" name="order.dutyPrepaid"> 
    <s:param name="labelcolspan" value="%{2}" /> 
    <s:param name="inputcolspan" value="%{2}" /> 
</s:textfield>
<s:textfield label="Third Party Bill To" name="order.dutyPrepaid"> 
    <s:param name="labelcolspan" value="%{2}" /> 
    <s:param name="inputcolspan" value="%{2}" /> 
</s:textfield>
    
<s:textfield label="Trucker" name="order.dutyPrepaid" size="70"> 
    <s:param name="labelcolspan" value="%{2}" /> 
    <s:param name="inputcolspan" value="%{6}" /> 
</s:textfield>
    
<s:textfield label="Loading Instructions" name="order.dutyPrepaid" size="70"> 
    <s:param name="labelcolspan" value="%{2}" /> 
    <s:param name="inputcolspan" value="%{6}" /> 
</s:textfield>
    
<s:textfield label="Shipping Instructions" name="order.dutyPrepaid" size="70"> 
    <s:param name="labelcolspan" value="%{2}" /> 
    <s:param name="inputcolspan" value="%{6}" /> 
</s:textfield>
</s:form>

<%-- Setup the number of columns to be in the table layout for the form. --%>
<s:bean name="java.util.HashMap" id="qTableLayout">
    <s:param name="tablecolspan" value="%{8}" />
</s:bean>

<%ArrayList genericList = new ArrayList();
genericList.add("hello");
genericList.add("generic List");
genericList.add("world");
request.setAttribute("genericList",genericList);
pageContext.setAttribute("genericList",genericList);
%>
<s:generator val="%{'aaa,bbb,ccc,ddd,eee'}" count="4" separator="," var="genericList" />
<hr/>without qxhtml
<s:form action="complexForm.action" method="post"  >
    <s:textfield label="SO Number" name="order.fromPartyOrderNumber"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{2}" /> 
    </s:textfield>
    <s:select name="salespersonId" label="Salesperson" list="genericList" listKey="id" listValue="name"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{2}" /> 
    </s:select>

    <s:textfield label="Trucker" name="order.dutyPrepaid" size="70"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{6}" /> 
    </s:textfield>

    <s:select name="newPoRequired" label="New PO Required" list="genericList"
               listKey="id" listValue="name" />
    <s:select name="shipdateConfirmed" label="Ship Date Confirmed" 
               list="genericList" listKey="id" listValue="name" />
    <s:select name="accounting90EntryDone" label="Acctg Entry Done" 
               list="genericList" listKey="id" listValue="name" />
    <s:select name="factored" label="Factored" list="genericList" listKey="id" 
               listValue="name" />

    <tr><th align="center" colspan="8">Line Items</th></tr>

    <tr>
        <th>#</th>
        <th>Product</th>
        <th>Qty</th>
        <th>Unit Price</th>
        <th>Allocation Instructions</th>
        <th>Label Instructions</th>
        <th>Description</th>
        <th>Override Reason</th>
    </tr>

    <s:iterator value="lineItems" status="status">
        <s:component template="/components/textcell.ftl" value="%{#status.index}" />
        <s:select name="termsId" list="#{'01':'Jan', '02':'Feb'}" listKey="id" listValue="name" />
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
    </s:iterator>

    <s:submit value="Create Sales Order" align="center">
        <s:param name="colspan" value="%{8}" />
        <s:param name="align" value="%{'center'}" />
    </s:submit>
</s:form>
<hr/>

<s:form action="complexForm.action" method="post" theme="qxhtml">
    <s:textfield label="SO Number" name="order.fromPartyOrderNumber"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{2}" /> 
    </s:textfield>
    <s:select name="salespersonId" label="Salesperson" list="genericList" listKey="id" listValue="name"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{2}" /> 
    </s:select>

    <s:textfield label="Trucker" name="order.dutyPrepaid" size="70"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{6}" /> 
    </s:textfield>

    <s:select name="newPoRequired" label="New PO Required" list="genericList"
               listKey="id" listValue="name" />
    <s:select name="shipdateConfirmed" label="Ship Date Confirmed" 
               list="genericList" listKey="id" listValue="name" />
    <s:select name="accounting90EntryDone" label="Acctg Entry Done" 
               list="genericList" listKey="id" listValue="name" />
    <s:select name="factored" label="Factored" list="genericList" listKey="id" 
               listValue="name" />

    <tr><th align="center" colspan="8">Line Items</th></tr>

    <tr>
        <th>#</th>
        <th>Product</th>
        <th>Qty</th>
        <th>Unit Price</th>
        <th>Allocation Instructions</th>
        <th>Label Instructions</th>
        <th>Description</th>
        <th>Override Reason</th>
    </tr>

    <s:iterator value="lineItems" status="status">
        <s:component template="/components/textcell.ftl" value="%{#status.index}" />
        <s:select name="termsId" list="#{'01':'Jan', '02':'Feb'}" listKey="id" listValue="name" />
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
    </s:iterator>

    <s:submit value="Create Sales Order" align="center">
        <s:param name="colspan" value="%{8}" />
        <s:param name="align" value="%{'center'}" />
    </s:submit>
</s:form>


</body>
</html>