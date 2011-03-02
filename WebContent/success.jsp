<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
<sj:head/>
<%String ctxstr = request.getContextPath(); %>
alert(ctxstr);
<script language="JavaScript" src="<% request.getContextPath();%>/js/test.js" type="text/javascript"></script>
</head>
<body>


<table>
			<tr>
				<td width="25%"><s:text name="testxsl.firstname"/> </td>
				<td>
					${firstname}
				</td>
			</tr>
			<tr>
				<td>LastName: </td>
				<td>
					${LastName}
				</td>
			</tr>
			<tr>
				<td>Order Date:</td>
				<td>
					${orderdate}
				</td>

			</tr>
			<tr>
				<td>Gender :</td>
				<td>
					${gender}
				</td>
			</tr>
			<tr>
				<td>Select the country :</td>
				<td>
					${country}
				</td>
			</tr>
			<tr>
				<td>check :</td>
				<td>
					${check}
				</td>
			</tr>
			
			
			<s:select label="Months"
       name="months"
       headerKey="-1" headerValue="Select Month"
       list="#{'01':'Jan', '02':'Feb'}"
       value="selectedMonth"
       required="true"
/>
		</table>
 
   <sj:datepicker id="jquerydatepicker" label="Select a Date" />
 
</body>
</html>
