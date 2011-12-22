<!DOCTYPE script PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s"  uri="/struts-tags" %>
<%@taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%@taglib prefix="sjg"  uri="/struts-jquery-grid-tags" %>
<html>
<head>
 
<sj:head jqueryui="true" jquerytheme="redmond"  />
<link rel="stylesheet" type="text/css" href="../jqgrid/css/ui.jqgrid.css">
<script src="../js/jquery.validate.js" > </script>
<script src="../js/additional-methods.js" > </script>
<script src="../jqgrid/js/i18n/grid.locale-en.js" > </script>
<script src="../jqgrid/js/jquery.jqGrid.min.js" > </script>

<script>
	var rulesframework = {}; 
	<s:if test = "jsrule != null" >
		 rulesframework =  ${jsrule};
	</s:if>
        	var fieldlist = "userid, nric, dob, firstname, lastname, gender, mobileno".split(",");
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
      	colNames:['User Id','Nric','Dob','First Name','Last Name','Gender','Mobile No'      	],
      	colModel:[
      	{name: 'userid', index: 'userid' , width:140, editable:true },
      	{name: 'nric', index: 'nric' , width:70, editable:true },
      	{name: 'dob', index: 'dob' , width:56, editable:true },
      	{name: 'firstname', index: 'firstname' , width:175, editable:true },
      	{name: 'lastname', index: 'lastname' , width:175, editable:true },
      	{name: 'gender', index: 'gender' , width:7, editable:true },
      	{name: 'mobileno', index: 'mobileno' , width:56, editable:true }
      	],
      	rowNum: 10,
      	rowList: [ 10, 20, 30],
      	pager: '#pagerid',
      	sortname: 'userid',
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
			       editurl: "${pageContext.request.contextPath}/html/simpleform.action?screenName=CustomerMaster&bulkcmd=grid",
       caption: "XXXXType the Caption here"
   } ).navGrid('#pager1',{edit:true,add:true,del:true});
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
<!--Submit Form -->
<form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/html/simpleform.action?screenName=CustomerMaster">
        	 <table>
        	   <tr><td>User Id </td><td><input name="userid" id="userid" value="${resultDTO.data.formonload[0].userid}"/></td></tr>
        	   <tr><td>Nric </td><td><input name="nric" id="nric" value="${resultDTO.data.formonload[0].nric}"/></td></tr>
        	   <tr><td>Dob </td><td><input name="dob" id="dob" value="${resultDTO.data.formonload[0].dob}"/></td></tr>
        	   <tr><td>First Name </td><td><input name="firstname" id="firstname" value="${resultDTO.data.formonload[0].firstname}"/></td></tr>
        	   <tr><td>Last Name </td><td><input name="lastname" id="lastname" value="${resultDTO.data.formonload[0].lastname}"/></td></tr>
        	   <tr><td>Gender </td><td><input name="gender" id="gender" value="${resultDTO.data.formonload[0].gender}"/></td></tr>
        	   <tr><td>Mobile No </td><td><input name="mobileno" id="mobileno" value="${resultDTO.data.formonload[0].mobileno}"/></td></tr>
        	   


        	   <tr><td>User Id </td><td><s:property value="#resultDTO.data.formonload[0].userid"  /></td></tr>
        	   <tr><td>Nric </td><td><s:property value="#resultDTO.data.formonload[0].nric"  /></td></tr>
        	   <tr><td>Dob </td><td><s:property value="#resultDTO.data.formonload[0].dob"  /></td></tr>
        	   <tr><td>First Name </td><td><s:property value="#resultDTO.data.formonload[0].firstname"  /></td></tr>
        	   <tr><td>Last Name </td><td><s:property value="#resultDTO.data.formonload[0].lastname"  /></td></tr>
        	   <tr><td>Gender </td><td><s:property value="#resultDTO.data.formonload[0].gender"  /></td></tr>
        	   <tr><td>Mobile No </td><td><s:property value="#resultDTO.data.formonload[0].mobileno"  /></td></tr>
        	 </table>
        	 bulkcmd: <input name="bulkcmd" value="frmgridadd"/>
        	 <button >submit</button>
        	 <button type="button" onclick="ajaxSubmit()">Ajax Submit</button></form>
</body>

</html>