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
<script src="../js/json2.js" > </script>

<script>
	var rulesframework = {}; 
	<s:if test = "jsrule != null" >
		 rulesframework =  ${jsrule};
	</s:if>
        	var fieldlist = "cin, nric, fname, lname, gender, dob, custcategory, makerid, secques, secquesans, membershipno, creationmode, regstatus, smsblastsubscribed, emailblastsubscribed, cardinsurancesubscribed, cardinsurpolicyyrstart, cardinsurpolicynum, mobileatreg, progunsubscribedreason, producttype".split(",");
   $(document).ready(function(){
	//iadt.setFieldlist(fieldlist);
	$("#form1").validate($.extend(rulesframework,{debug: true}));
	calljqgrid();		
   });
  var lastsel= {};
  function calljqgrid(formdata){
   jQuery("#listid").jqGrid( {

      	url:'<%= request.getContextPath() %>/jqgrid.action?command=true&screenName=CustomerMaster&submitdata={bulkcmd="gridonload"}',
      	datatype: "json",
      	colNames:['Cin','Nric','F Name','L Name','Gender','Dob','Cust Category','Maker Id','Sec Ques','Sec Ques Ans','Membership No','Creation Mode','Reg Status','Sms Blast Subscribed','Email Blast Subscribed','Card Insurance Subscribed','Card Insur Policy Yr Start','Card Insur Policy Num','Mobile At Reg','Prog Unsubscribed Reason','Product Type'      	],
      	colModel:[
      	{name: 'cin', index: 'cin' , width:112, editable:true },
      	{name: 'nric', index: 'nric' , width:140, editable:true },
      	{name: 'fname', index: 'fname' , width:150, editable:true },
      	{name: 'lname', index: 'lname' , width:150, editable:true },
      	{name: 'gender', index: 'gender' , width:70, editable:true },
      	{name: 'dob', index: 'dob' , width:20, editable:true },
      	{name: 'custcategory', index: 'custcategory' , width:70, editable:true },
      	{name: 'makerid', index: 'makerid' , width:175, editable:true },
      	{name: 'secques', index: 'secques' , width:28, editable:true },
      	{name: 'secquesans', index: 'secquesans' , width:100, editable:true },
      	{name: 'membershipno', index: 'membershipno' , width:100, editable:true },
      	{name: 'creationmode', index: 'creationmode' , width:105, editable:true },
      	{name: 'regstatus', index: 'regstatus' , width:84, editable:true },
      	{name: 'smsblastsubscribed', index: 'smsblastsubscribed' , width:14, editable:true },
      	{name: 'emailblastsubscribed', index: 'emailblastsubscribed' , width:14, editable:true },
      	{name: 'cardinsurancesubscribed', index: 'cardinsurancesubscribed' , width:14, editable:true },
      	{name: 'cardinsurpolicyyrstart', index: 'cardinsurpolicyyrstart' , width:20, editable:true },
      	{name: 'cardinsurpolicynum', index: 'cardinsurpolicynum' , width:140, editable:true },
      	{name: 'mobileatreg', index: 'mobileatreg' , width:175, editable:true },
      	{name: 'progunsubscribedreason', index: 'progunsubscribedreason' , width:100, editable:true },
      	{name: 'producttype', index: 'producttype' , width:140, editable:true }
      	],
      	rowNum: 10,
      	rowList: [ 10, 20, 30],
      	pager: '#pagerid',
      	sortname: 'cin',
        viewrecords: true,
        sortorder: "desc",
        jsonReader: {
    		repeatitems : false,
    		userdata: 'userdata',
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
			    		$("#messagegrid").text(JSON.stringify(jQuery("#listid").getGridParam('userData'), null, 2));
			    	},
			       editurl: "${pageContext.request.contextPath}/html/simpleform.action?screenName=CustomerMaster&bulkcmd=grid",
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
	jQuery.post("${pageContext.request.contextPath}/html/simpleform.action?screenName=CustomerMaster", 
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
		 <div id="messagegrid"></div>
<!--Submit Form -->
<form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/html/simpleform.action?screenName=CustomerMaster">
        	 <table>
        	   <tr><td>Cin </td><td><input name="cin" id="cin" value="${resultDTO.data.formonload[0].cin}"/></td></tr>
        	   <tr><td>Nric </td><td><input name="nric" id="nric" value="${resultDTO.data.formonload[0].nric}"/></td></tr>
        	   <tr><td>F Name </td><td><input name="fname" id="fname" value="${resultDTO.data.formonload[0].fname}"/></td></tr>
        	   <tr><td>L Name </td><td><input name="lname" id="lname" value="${resultDTO.data.formonload[0].lname}"/></td></tr>
        	   <tr><td>Gender </td><td><input name="gender" id="gender" value="${resultDTO.data.formonload[0].gender}"/></td></tr>
        	   <tr><td>Dob </td><td><input name="dob" id="dob" value="${resultDTO.data.formonload[0].dob}"/></td></tr>
        	   <tr><td>Cust Category </td><td><input name="custcategory" id="custcategory" value="${resultDTO.data.formonload[0].custcategory}"/></td></tr>
        	   <tr><td>Maker Id </td><td><input name="makerid" id="makerid" value="${resultDTO.data.formonload[0].makerid}"/></td></tr>
        	   <tr><td>Sec Ques </td><td><input name="secques" id="secques" value="${resultDTO.data.formonload[0].secques}"/></td></tr>
        	   <tr><td>Sec Ques Ans </td><td><input name="secquesans" id="secquesans" value="${resultDTO.data.formonload[0].secquesans}"/></td></tr>
        	   <tr><td>Membership No </td><td><input name="membershipno" id="membershipno" value="${resultDTO.data.formonload[0].membershipno}"/></td></tr>
        	   <tr><td>Creation Mode </td><td><input name="creationmode" id="creationmode" value="${resultDTO.data.formonload[0].creationmode}"/></td></tr>
        	   <tr><td>Reg Status </td><td><input name="regstatus" id="regstatus" value="${resultDTO.data.formonload[0].regstatus}"/></td></tr>
        	   <tr><td>Sms Blast Subscribed </td><td><input name="smsblastsubscribed" id="smsblastsubscribed" value="${resultDTO.data.formonload[0].smsblastsubscribed}"/></td></tr>
        	   <tr><td>Email Blast Subscribed </td><td><input name="emailblastsubscribed" id="emailblastsubscribed" value="${resultDTO.data.formonload[0].emailblastsubscribed}"/></td></tr>
        	   <tr><td>Card Insurance Subscribed </td><td><input name="cardinsurancesubscribed" id="cardinsurancesubscribed" value="${resultDTO.data.formonload[0].cardinsurancesubscribed}"/></td></tr>
        	   <tr><td>Card Insur Policy Yr Start </td><td><input name="cardinsurpolicyyrstart" id="cardinsurpolicyyrstart" value="${resultDTO.data.formonload[0].cardinsurpolicyyrstart}"/></td></tr>
        	   <tr><td>Card Insur Policy Num </td><td><input name="cardinsurpolicynum" id="cardinsurpolicynum" value="${resultDTO.data.formonload[0].cardinsurpolicynum}"/></td></tr>
        	   <tr><td>Mobile At Reg </td><td><input name="mobileatreg" id="mobileatreg" value="${resultDTO.data.formonload[0].mobileatreg}"/></td></tr>
        	   <tr><td>Prog Unsubscribed Reason </td><td><input name="progunsubscribedreason" id="progunsubscribedreason" value="${resultDTO.data.formonload[0].progunsubscribedreason}"/></td></tr>
        	   <tr><td>Product Type </td><td><input name="producttype" id="producttype" value="${resultDTO.data.formonload[0].producttype}"/></td></tr>
        	   


        	   <tr><td>Cin </td><td><s:property value="#resultDTO.data.formonload[0].cin"  /></td></tr>
        	   <tr><td>Nric </td><td><s:property value="#resultDTO.data.formonload[0].nric"  /></td></tr>
        	   <tr><td>F Name </td><td><s:property value="#resultDTO.data.formonload[0].fname"  /></td></tr>
        	   <tr><td>L Name </td><td><s:property value="#resultDTO.data.formonload[0].lname"  /></td></tr>
        	   <tr><td>Gender </td><td><s:property value="#resultDTO.data.formonload[0].gender"  /></td></tr>
        	   <tr><td>Dob </td><td><s:property value="#resultDTO.data.formonload[0].dob"  /></td></tr>
        	   <tr><td>Cust Category </td><td><s:property value="#resultDTO.data.formonload[0].custcategory"  /></td></tr>
        	   <tr><td>Maker Id </td><td><s:property value="#resultDTO.data.formonload[0].makerid"  /></td></tr>
        	   <tr><td>Sec Ques </td><td><s:property value="#resultDTO.data.formonload[0].secques"  /></td></tr>
        	   <tr><td>Sec Ques Ans </td><td><s:property value="#resultDTO.data.formonload[0].secquesans"  /></td></tr>
        	   <tr><td>Membership No </td><td><s:property value="#resultDTO.data.formonload[0].membershipno"  /></td></tr>
        	   <tr><td>Creation Mode </td><td><s:property value="#resultDTO.data.formonload[0].creationmode"  /></td></tr>
        	   <tr><td>Reg Status </td><td><s:property value="#resultDTO.data.formonload[0].regstatus"  /></td></tr>
        	   <tr><td>Sms Blast Subscribed </td><td><s:property value="#resultDTO.data.formonload[0].smsblastsubscribed"  /></td></tr>
        	   <tr><td>Email Blast Subscribed </td><td><s:property value="#resultDTO.data.formonload[0].emailblastsubscribed"  /></td></tr>
        	   <tr><td>Card Insurance Subscribed </td><td><s:property value="#resultDTO.data.formonload[0].cardinsurancesubscribed"  /></td></tr>
        	   <tr><td>Card Insur Policy Yr Start </td><td><s:property value="#resultDTO.data.formonload[0].cardinsurpolicyyrstart"  /></td></tr>
        	   <tr><td>Card Insur Policy Num </td><td><s:property value="#resultDTO.data.formonload[0].cardinsurpolicynum"  /></td></tr>
        	   <tr><td>Mobile At Reg </td><td><s:property value="#resultDTO.data.formonload[0].mobileatreg"  /></td></tr>
        	   <tr><td>Prog Unsubscribed Reason </td><td><s:property value="#resultDTO.data.formonload[0].progunsubscribedreason"  /></td></tr>
        	   <tr><td>Product Type </td><td><s:property value="#resultDTO.data.formonload[0].producttype"  /></td></tr>
        	 </table>
        	 bulkcmd: <input name="bulkcmd" value="frmgridadd"/>
        	 <button >submit</button>
        	 <button type="button" onclick="ajaxSubmit()">Ajax Submit</button></form>
</body>

</html>