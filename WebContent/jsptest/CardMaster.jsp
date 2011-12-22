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
        	var fieldlist = "cardno, cin, producttype, cardstatus, statusflag, makerdttm".split(",");
   $(document).ready(function(){
	//iadt.setFieldlist(fieldlist);
	$("#form1").validate($.extend(rulesframework,{debug: true}));
	calljqgrid();		
   });
  var lastsel= {};
  function calljqgrid(formdata){
   jQuery("#listid").jqGrid( {

      	url:'<%= request.getContextPath() %>/jqgrid.action?command=true&screenName=CardMaster&submitdata={bulkcmd="gridonload"}',
      	datatype: "json",
      	colNames:['Card No','Cin','Product Type','Card Status','Status Flag','Maker Dttm'      	],
      	colModel:[
      	{name: 'cardno', index: 'cardno' , width:175, editable:true },
      	{name: 'cin', index: 'cin' , width:175, editable:true },
      	{name: 'producttype', index: 'producttype' , width:140, editable:true },
      	{name: 'cardstatus', index: 'cardstatus' , width:56, editable:true },
      	{name: 'statusflag', index: 'statusflag' , width:210, editable:true },
      	{name: 'makerdttm', index: 'makerdttm' , width:56, editable:true }
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
			       editurl: "${pageContext.request.contextPath}/html/simpleform.action?screenName=CardMaster&bulkcmd=grid",
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
	jQuery.post("${pageContext.request.contextPath}/html/simpleform.action?screenName=CardMaster", 
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
<form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/html/simpleform.action?screenName=CardMaster">
        	 <table>
        	   <tr><td>Card No </td><td><input name="cardno" id="cardno" value="${resultDTO.data.formonload[0].cardno}"/></td></tr>
        	   <tr><td>Cin </td><td><input name="cin" id="cin" value="${resultDTO.data.formonload[0].cin}"/></td></tr>
        	   <tr><td>Product Type </td><td><input name="producttype" id="producttype" value="${resultDTO.data.formonload[0].producttype}"/></td></tr>
        	   <tr><td>Card Status </td><td><input name="cardstatus" id="cardstatus" value="${resultDTO.data.formonload[0].cardstatus}"/></td></tr>
        	   <tr><td>Status Flag </td><td><input name="statusflag" id="statusflag" value="${resultDTO.data.formonload[0].statusflag}"/></td></tr>
        	   <tr><td>Maker Dttm </td><td><input name="makerdttm" id="makerdttm" value="${resultDTO.data.formonload[0].makerdttm}"/></td></tr>
        	   


        	   <tr><td>Card No </td><td><s:property value="#resultDTO.data.formonload[0].cardno"  /></td></tr>
        	   <tr><td>Cin </td><td><s:property value="#resultDTO.data.formonload[0].cin"  /></td></tr>
        	   <tr><td>Product Type </td><td><s:property value="#resultDTO.data.formonload[0].producttype"  /></td></tr>
        	   <tr><td>Card Status </td><td><s:property value="#resultDTO.data.formonload[0].cardstatus"  /></td></tr>
        	   <tr><td>Status Flag </td><td><s:property value="#resultDTO.data.formonload[0].statusflag"  /></td></tr>
        	   <tr><td>Maker Dttm </td><td><s:property value="#resultDTO.data.formonload[0].makerdttm"  /></td></tr>
        	 </table>
        	 bulkcmd: <input name="bulkcmd" value="frmgridadd"/>
        	 <button >submit</button>
        	 <button type="button" onclick="ajaxSubmit()">Ajax Submit</button></form>
</body>

</html>