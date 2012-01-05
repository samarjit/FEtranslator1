<!DOCTYPE script PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s"  uri="/struts-tags" %>
<%@taglib prefix="sj"  uri="/struts-jquery-tags" %>

<html>
<head>
 
<sj:head jqueryui="true" jquerytheme="redmond"  />
<link rel="stylesheet" type="text/css" href="../css/ui.jqgrid.css">
<script src="../js/jquery.validate.js" > </script>
<script src="../js/additional-methods.js" > </script>
<script src="../js/i18n/grid.locale-en.js" > </script>
<script src="../js/jquery.jqGrid.min.js" > </script>
 

<script>
var rulesframework = {}; 
<s:if test = "jsrule != null" >
	 rulesframework =  ${jsrule};
</s:if>
    	var fieldlist = "cardno, userid, balatreporting, spentaftreporttrans, spentaftreportnontrans, balatblocking, cardstatus, cardinsureavail, inspolicyno, refundmode, replacecardno, refundexpirydate, refundstatus, blockedby, reportingtime, blockingtime, blockreason, policereportnumber, policereportfilename, policereportduedate, insamountclaimed, refundchannel, totalmisusedamount, unsyncsettled, insurfilerefid, insurresult, sawfilerefid, sawresult, mtrfilerefid, prevrefundstatus, prevrefundchannel".split(",");
$(document).ready(function(){
//iadt.setFieldlist(fieldlist);
$("#form1").validate($.extend(rulesframework,{debug: true}));
calljqgrid();		
});
var lastsel= {};
function calljqgrid(formdata){
jQuery("#listid").jqGrid( {

  	url:'<%= request.getContextPath() %>/jqgrid.action?command=true&screenName=CardBlockRefund&submitdata={bulkcmd="gridonload"}',
  	datatype: "json",
  	colNames:['Card No','User Id','Bal At Reporting','Spent Aft Report Trans','Spent Aft Report Nontrans','Bal At Blocking','Card Status','Card Insure Avail','Ins Policy No','Refund Mode','Replace Card No','Refund Expiry Date','Refund Status','Blocked By','Reporting Time','Blocking Time','Block Reason','Police Report Number','Police Report File Name','Police Report Due Date','Ins Amount Claimed','Refund Channel','Total Misused Amount','Unsync Settled','Insur File Ref Id','Insur Result','Saw File Ref Id','Saw Result','Mtr File Ref Id','Prev Refund Status','Prev Refund Channel'      	],
  	colModel:[
  	{name: 'cardno', index: 'cardno' , width:80, editable:true },
  	{name: 'userid', index: 'userid' , width:100, editable:true },
  	{name: 'balatreporting', index: 'balatreporting' , width:40, editable:true },
  	{name: 'spentaftreporttrans', index: 'spentaftreporttrans' , width:40, editable:true },
  	{name: 'spentaftreportnontrans', index: 'spentaftreportnontrans' , width:40, editable:true },
  	{name: 'balatblocking', index: 'balatblocking' , width:40, editable:true },
  	{name: 'cardstatus', index: 'cardstatus' , width:100, editable:true },
  	{name: 'cardinsureavail', index: 'cardinsureavail' , width:5, editable:true },
  	{name: 'inspolicyno', index: 'inspolicyno' , width:50, editable:true },
  	{name: 'refundmode', index: 'refundmode' , width:100, editable:true },
  	{name: 'replacecardno', index: 'replacecardno' , width:80, editable:true },
  	{name: 'refundexpirydate', index: 'refundexpirydate' , width:40, editable:true },
  	{name: 'refundstatus', index: 'refundstatus' , width:150, editable:true },
  	{name: 'blockedby', index: 'blockedby' , width:125, editable:true },
  	{name: 'reportingtime', index: 'reportingtime' , width:40, editable:true },
  	{name: 'blockingtime', index: 'blockingtime' , width:40, editable:true },
  	{name: 'blockreason', index: 'blockreason' , width:250, editable:true },
  	{name: 'policereportnumber', index: 'policereportnumber' , width:100, editable:true },
  	{name: 'policereportfilename', index: 'policereportfilename' , width:250, editable:true },
  	{name: 'policereportduedate', index: 'policereportduedate' , width:40, editable:true },
  	{name: 'insamountclaimed', index: 'insamountclaimed' , width:40, editable:true },
  	{name: 'refundchannel', index: 'refundchannel' , width:100, editable:true },
  	{name: 'totalmisusedamount', index: 'totalmisusedamount' , width:40, editable:true },
  	{name: 'unsyncsettled', index: 'unsyncsettled' , width:5, editable:true },
  	{name: 'insurfilerefid', index: 'insurfilerefid' , width:100, editable:true },
  	{name: 'insurresult', index: 'insurresult' , width:15, editable:true },
  	{name: 'sawfilerefid', index: 'sawfilerefid' , width:100, editable:true },
  	{name: 'sawresult', index: 'sawresult' , width:25, editable:true },
  	{name: 'mtrfilerefid', index: 'mtrfilerefid' , width:100, editable:true },
  	{name: 'prevrefundstatus', index: 'prevrefundstatus' , width:150, editable:true },
  	{name: 'prevrefundchannel', index: 'prevrefundchannel' , width:100, editable:true }
  	],
  	rowNum: 10,
  	rowList: [ 10, 20, 30],
  	pager: '#pagerid',
  	sortname: 'cardno',
    viewrecords: true,
    sortorder: "desc",
    jsonReader: {
		repeatitems : false,
		id: "0"
	},
   onSelectRow: function(id){
		    		if(id && id!==lastsel){
		    			//jQuery('#listid').jqGrid('restoreRow',lastsel);
		    			//jQuery('#listid').jqGrid('editRow',id,true);
		    			jQuery("#listid").jqGrid('GridToForm',id,"#form1");
		    			lastsel=id;
		    		}
		    	},
		    	loadComplete: function(){
		    		var ret;
		    	},
		       editurl: "${pageContext.request.contextPath}/html/simpleform.action?screenName=CardBlockRefund&bulkcmd=frmgrid",
   caption: "XXXXType the Caption here"
} ).navGrid('#pagerid',{edit:true,add:true,del:true});
jQuery("#listid").jqGrid('navButtonAdd','#pagerid',{caption:"Edit",
	onClickButton:function(){
		var gsr = jQuery("#listid").jqGrid('getGridParam','selrow');
		if(gsr){
			jQuery("#listid").jqGrid('GridToForm',gsr,"#form1");
		} else {
			alert("Please select Row");
		}							
	} 
}); 
} //end calljqgrid
function ajaxSubmit(){
jQuery.post("${pageContext.request.contextPath}/html/simpleform.action?screenName=CardBlockRefund", 
		$("#form1").serialize(),
		function(data){
	var json = jQuery.parseJSON(data);
	jQuery("#listid").trigger("reloadGrid");
  });
}

</script>

</head>

<body>
  
 <table id="listid" ></table>
		 <div id="pagerid"></div>
<!--Submit Form -->
<form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/html/simpleform.action?screenName=CardBlockRefundAjax">
        	 <table>
        	   <tr><td>Card No </td><td><input name="cardno" id="cardno" value="${resultDTO.data.formonload[0].cardno}"/></td></tr>
        	   <tr><td>User Id </td><td><input name="userid" id="userid" value="${resultDTO.data.formonload[0].userid}"/></td></tr>
        	   <tr><td>Bal At Reporting </td><td><input name="balatreporting" id="balatreporting" value="${resultDTO.data.formonload[0].balatreporting}"/></td></tr>
        	   <tr><td>Spent Aft Report Trans </td><td><input name="spentaftreporttrans" id="spentaftreporttrans" value="${resultDTO.data.formonload[0].spentaftreporttrans}"/></td></tr>
        	   <tr><td>Spent Aft Report Nontrans </td><td><input name="spentaftreportnontrans" id="spentaftreportnontrans" value="${resultDTO.data.formonload[0].spentaftreportnontrans}"/></td></tr>
        	   <tr><td>Bal At Blocking </td><td><input name="balatblocking" id="balatblocking" value="${resultDTO.data.formonload[0].balatblocking}"/></td></tr>
        	   <tr><td>Card Status </td><td><input name="cardstatus" id="cardstatus" value="${resultDTO.data.formonload[0].cardstatus}"/></td></tr>
        	   <tr><td>Card Insure Avail </td><td><input name="cardinsureavail" id="cardinsureavail" value="${resultDTO.data.formonload[0].cardinsureavail}"/></td></tr>
        	   <tr><td>Ins Policy No </td><td><input name="inspolicyno" id="inspolicyno" value="${resultDTO.data.formonload[0].inspolicyno}"/></td></tr>
        	   <tr><td>Refund Mode </td><td><input name="refundmode" id="refundmode" value="${resultDTO.data.formonload[0].refundmode}"/></td></tr>
        	   <tr><td>Replace Card No </td><td><input name="replacecardno" id="replacecardno" value="${resultDTO.data.formonload[0].replacecardno}"/></td></tr>
        	   <tr><td>Refund Expiry Date </td><td><input name="refundexpirydate" id="refundexpirydate" value="${resultDTO.data.formonload[0].refundexpirydate}"/></td></tr>
        	   <tr><td>Refund Status </td><td><input name="refundstatus" id="refundstatus" value="${resultDTO.data.formonload[0].refundstatus}"/></td></tr>
        	   <tr><td>Blocked By </td><td><input name="blockedby" id="blockedby" value="${resultDTO.data.formonload[0].blockedby}"/></td></tr>
        	   <tr><td>Reporting Time </td><td><input name="reportingtime" id="reportingtime" value="${resultDTO.data.formonload[0].reportingtime}"/></td></tr>
        	   <tr><td>Blocking Time </td><td><input name="blockingtime" id="blockingtime" value="${resultDTO.data.formonload[0].blockingtime}"/></td></tr>
        	   <tr><td>Block Reason </td><td><input name="blockreason" id="blockreason" value="${resultDTO.data.formonload[0].blockreason}"/></td></tr>
        	   <tr><td>Police Report Number </td><td><input name="policereportnumber" id="policereportnumber" value="${resultDTO.data.formonload[0].policereportnumber}"/></td></tr>
        	   <tr><td>Police Report File Name </td><td><input name="policereportfilename" id="policereportfilename" value="${resultDTO.data.formonload[0].policereportfilename}"/></td></tr>
        	   <tr><td>Police Report Due Date </td><td><input name="policereportduedate" id="policereportduedate" value="${resultDTO.data.formonload[0].policereportduedate}"/></td></tr>
        	   <tr><td>Ins Amount Claimed </td><td><input name="insamountclaimed" id="insamountclaimed" value="${resultDTO.data.formonload[0].insamountclaimed}"/></td></tr>
        	   <tr><td>Refund Channel </td><td><input name="refundchannel" id="refundchannel" value="${resultDTO.data.formonload[0].refundchannel}"/></td></tr>
        	   <tr><td>Total Misused Amount </td><td><input name="totalmisusedamount" id="totalmisusedamount" value="${resultDTO.data.formonload[0].totalmisusedamount}"/></td></tr>
        	   <tr><td>Unsync Settled </td><td><input name="unsyncsettled" id="unsyncsettled" value="${resultDTO.data.formonload[0].unsyncsettled}"/></td></tr>
        	   <tr><td>Insur File Ref Id </td><td><input name="insurfilerefid" id="insurfilerefid" value="${resultDTO.data.formonload[0].insurfilerefid}"/></td></tr>
        	   <tr><td>Insur Result </td><td><input name="insurresult" id="insurresult" value="${resultDTO.data.formonload[0].insurresult}"/></td></tr>
        	   <tr><td>Saw File Ref Id </td><td><input name="sawfilerefid" id="sawfilerefid" value="${resultDTO.data.formonload[0].sawfilerefid}"/></td></tr>
        	   <tr><td>Saw Result </td><td><input name="sawresult" id="sawresult" value="${resultDTO.data.formonload[0].sawresult}"/></td></tr>
        	   <tr><td>Mtr File Ref Id </td><td><input name="mtrfilerefid" id="mtrfilerefid" value="${resultDTO.data.formonload[0].mtrfilerefid}"/></td></tr>
        	   <tr><td>Prev Refund Status </td><td><input name="prevrefundstatus" id="prevrefundstatus" value="${resultDTO.data.formonload[0].prevrefundstatus}"/></td></tr>
        	   <tr><td>Prev Refund Channel </td><td><input name="prevrefundchannel" id="prevrefundchannel" value="${resultDTO.data.formonload[0].prevrefundchannel}"/></td></tr>
        	   


        	   <tr><td>Card No </td><td><s:property value="#resultDTO.data.formonload[0].cardno"  /></td></tr>
        	   <tr><td>User Id </td><td><s:property value="#resultDTO.data.formonload[0].userid"  /></td></tr>
        	   <tr><td>Bal At Reporting </td><td><s:property value="#resultDTO.data.formonload[0].balatreporting"  /></td></tr>
        	   <tr><td>Spent Aft Report Trans </td><td><s:property value="#resultDTO.data.formonload[0].spentaftreporttrans"  /></td></tr>
        	   <tr><td>Spent Aft Report Nontrans </td><td><s:property value="#resultDTO.data.formonload[0].spentaftreportnontrans"  /></td></tr>
        	   <tr><td>Bal At Blocking </td><td><s:property value="#resultDTO.data.formonload[0].balatblocking"  /></td></tr>
        	   <tr><td>Card Status </td><td><s:property value="#resultDTO.data.formonload[0].cardstatus"  /></td></tr>
        	   <tr><td>Card Insure Avail </td><td><s:property value="#resultDTO.data.formonload[0].cardinsureavail"  /></td></tr>
        	   <tr><td>Ins Policy No </td><td><s:property value="#resultDTO.data.formonload[0].inspolicyno"  /></td></tr>
        	   <tr><td>Refund Mode </td><td><s:property value="#resultDTO.data.formonload[0].refundmode"  /></td></tr>
        	   <tr><td>Replace Card No </td><td><s:property value="#resultDTO.data.formonload[0].replacecardno"  /></td></tr>
        	   <tr><td>Refund Expiry Date </td><td><s:property value="#resultDTO.data.formonload[0].refundexpirydate"  /></td></tr>
        	   <tr><td>Refund Status </td><td><s:property value="#resultDTO.data.formonload[0].refundstatus"  /></td></tr>
        	   <tr><td>Blocked By </td><td><s:property value="#resultDTO.data.formonload[0].blockedby"  /></td></tr>
        	   <tr><td>Reporting Time </td><td><s:property value="#resultDTO.data.formonload[0].reportingtime"  /></td></tr>
        	   <tr><td>Blocking Time </td><td><s:property value="#resultDTO.data.formonload[0].blockingtime"  /></td></tr>
        	   <tr><td>Block Reason </td><td><s:property value="#resultDTO.data.formonload[0].blockreason"  /></td></tr>
        	   <tr><td>Police Report Number </td><td><s:property value="#resultDTO.data.formonload[0].policereportnumber"  /></td></tr>
        	   <tr><td>Police Report File Name </td><td><s:property value="#resultDTO.data.formonload[0].policereportfilename"  /></td></tr>
        	   <tr><td>Police Report Due Date </td><td><s:property value="#resultDTO.data.formonload[0].policereportduedate"  /></td></tr>
        	   <tr><td>Ins Amount Claimed </td><td><s:property value="#resultDTO.data.formonload[0].insamountclaimed"  /></td></tr>
        	   <tr><td>Refund Channel </td><td><s:property value="#resultDTO.data.formonload[0].refundchannel"  /></td></tr>
        	   <tr><td>Total Misused Amount </td><td><s:property value="#resultDTO.data.formonload[0].totalmisusedamount"  /></td></tr>
        	   <tr><td>Unsync Settled </td><td><s:property value="#resultDTO.data.formonload[0].unsyncsettled"  /></td></tr>
        	   <tr><td>Insur File Ref Id </td><td><s:property value="#resultDTO.data.formonload[0].insurfilerefid"  /></td></tr>
        	   <tr><td>Insur Result </td><td><s:property value="#resultDTO.data.formonload[0].insurresult"  /></td></tr>
        	   <tr><td>Saw File Ref Id </td><td><s:property value="#resultDTO.data.formonload[0].sawfilerefid"  /></td></tr>
        	   <tr><td>Saw Result </td><td><s:property value="#resultDTO.data.formonload[0].sawresult"  /></td></tr>
        	   <tr><td>Mtr File Ref Id </td><td><s:property value="#resultDTO.data.formonload[0].mtrfilerefid"  /></td></tr>
        	   <tr><td>Prev Refund Status </td><td><s:property value="#resultDTO.data.formonload[0].prevrefundstatus"  /></td></tr>
        	   <tr><td>Prev Refund Channel </td><td><s:property value="#resultDTO.data.formonload[0].prevrefundchannel"  /></td></tr>
        	 </table>
        	 bulkcmd: <input name="bulkcmd" value="frmgridadd"/>
        	 <button >submit</button>
        	 <button type="button" onclick="ajaxSubmit()">Ajax Submit</button></form>
        	 
</body>

</html>