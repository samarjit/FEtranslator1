<!DOCTYPE script PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s"  uri="/struts-tags" %>
<%@taglib prefix="sj"  uri="/struts-jquery-tags" %>
<%--@taglib prefix="sjg"  uri="/struts-jquery-grid-tags" --%>
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
        	var fieldlist = "filerefid, sentfilename, sentfiletype, sentstatus, senterrormsg, sentdate, recvfilename, recvdate, recvstatus, recverrormsg, recvfiletype".split(",");
   $(document).ready(function(){
	//iadt.setFieldlist(fieldlist);
	$("#form1").validate($.extend(rulesframework,{debug: true}));
	calljqgrid();		
   });
  var lastsel= {};
  function calljqgrid(formdata){
   jQuery("#listid").jqGrid( {

      	url:'<%= request.getContextPath() %>/jqgrid.action?command=true&screenName=EzlRetnFileTrack&submitdata={bulkcmd="gridonload"}',
      	datatype: "json",
      	colNames:['File Ref Id','Sent File Name','Sent File Type','Sent Status','Sent Error Msg','Sent Date','Recv File Name','Recv Date','Recv Status','Recv Error Msg','Recv File Type'      	],
      	colModel:[
      	{name: 'filerefid', index: 'filerefid' , width:140, editable:true },
      	{name: 'sentfilename', index: 'sentfilename' , width:700, editable:true },
      	{name: 'sentfiletype', index: 'sentfiletype' , width:350, editable:true },
      	{name: 'sentstatus', index: 'sentstatus' , width:70, editable:true },
      	{name: 'senterrormsg', index: 'senterrormsg' , width:350, editable:true },
      	{name: 'sentdate', index: 'sentdate' , width:0, editable:true },
      	{name: 'recvfilename', index: 'recvfilename' , width:700, editable:true },
      	{name: 'recvdate', index: 'recvdate' , width:0, editable:true },
      	{name: 'recvstatus', index: 'recvstatus' , width:70, editable:true },
      	{name: 'recverrormsg', index: 'recverrormsg' , width:350, editable:true },
      	{name: 'recvfiletype', index: 'recvfiletype' , width:350, editable:true }
      	],
      	rowNum: 10,
      	rowList: [ 10, 20, 30],
      	pager: '#pagerid',
      	sortname: 'filerefid',
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
			       editurl: "${pageContext.request.contextPath}/html/simpleform.action?screenName=EzlRetnFileTrack&bulkcmd=grid",
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
	jQuery.post("${pageContext.request.contextPath}/html/simpleform.action?screenName=EzlRetnFileTrack", 
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
<form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/html/simpleform.action?screenName=EzlRetnFileTrack">
        	 <table>
        	   <tr><td>File Ref Id </td><td><input name="filerefid" id="filerefid" value="${resultDTO.data.formonload[0].filerefid}"/></td></tr>
        	   <tr><td>Sent File Name </td><td><input name="sentfilename" id="sentfilename" value="${resultDTO.data.formonload[0].sentfilename}"/></td></tr>
        	   <tr><td>Sent File Type </td><td><input name="sentfiletype" id="sentfiletype" value="${resultDTO.data.formonload[0].sentfiletype}"/></td></tr>
        	   <tr><td>Sent Status </td><td><input name="sentstatus" id="sentstatus" value="${resultDTO.data.formonload[0].sentstatus}"/></td></tr>
        	   <tr><td>Sent Error Msg </td><td><input name="senterrormsg" id="senterrormsg" value="${resultDTO.data.formonload[0].senterrormsg}"/></td></tr>
        	   <tr><td>Sent Date </td><td><input name="sentdate" id="sentdate" value="${resultDTO.data.formonload[0].sentdate}"/></td></tr>
        	   <tr><td>Recv File Name </td><td><input name="recvfilename" id="recvfilename" value="${resultDTO.data.formonload[0].recvfilename}"/></td></tr>
        	   <tr><td>Recv Date </td><td><input name="recvdate" id="recvdate" value="${resultDTO.data.formonload[0].recvdate}"/></td></tr>
        	   <tr><td>Recv Status </td><td><input name="recvstatus" id="recvstatus" value="${resultDTO.data.formonload[0].recvstatus}"/></td></tr>
        	   <tr><td>Recv Error Msg </td><td><input name="recverrormsg" id="recverrormsg" value="${resultDTO.data.formonload[0].recverrormsg}"/></td></tr>
        	   <tr><td>Recv File Type </td><td><input name="recvfiletype" id="recvfiletype" value="${resultDTO.data.formonload[0].recvfiletype}"/></td></tr>
        	   
 			 </table>
        	 bulkcmd: <input name="bulkcmd" value="frmgridadd"/>
        	 <button >submit</button>
        	 <button type="button" onclick="ajaxSubmit()">Ajax Submit</button></form>
</body>

</html>