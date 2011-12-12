<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Merchant Edit</title>
        <link href="../css/body.css" rel="stylesheet" type="text/css">
        <link href="../css/main.css" rel="stylesheet" type="text/css">
		<script  src="../js/jquery-1.5.2.js"></script>
		<script  src="../js/jquery.validate.js"></script>
		<script  src="../js/additional-methods.js"></script>
		<script type="text/javascript" src="../js/fg.menu.js"></script>
		<script type="text/javascript" src="../js/jquery.tmpl.js"></script>
		<script type="text/javascript" src="../js/jsonStringify.js"></script>
		<script type="text/javascript" src="../js/iadtframework.js"></script>
		
		<link rel="stylesheet" type="text/css" media="screen" href="../css/jquery-ui-1.8.1.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="../css/ui.jqgrid.css" />
		
		<script src="../js/i18n/grid.locale-en.js" type="text/javascript"></script>
		<script src="../js/jquery.jqGrid.src.js" type="text/javascript"></script>
        <script type="text/javascript">
            var rulesframework = {}; 
        	<s:if test = "jsrule != null" >
        		 rulesframework =  ${jsrule};
        	</s:if>
			var fieldlist ="invoice,productCode,productName,plasticCode,plasticDesc,quantity,orderBy,orderDate,authorizeStatus".split(",");
			var keylist =  "productCode,plasticCode,quantity".split(",");
			var unqFldId = "invoice"; 
			var formdata;			
            $(document).ready(function(){
				iadt.setFieldlist(fieldlist);
				$("#form1").validate($.extend(rulesframework,{debug: true}));
				calljqgrid();		
            });
			
			var lastsel= {};
			function calljqgrid(formdata){
				jQuery("#listid").jqGrid( {

			      	url:'${pageContext.request.contextPath}/jqgrid.action?command=true&screenName=JspTestEditCrud&submitdata={bulkcmd="gridonload"}',
			      	datatype: "json",
			      	colNames:['Merchant Id','Merchant Type','Merchant Location','Status'      	],
			      	colModel:[
			      	{name: 'merchantid', index: 'merchantid' , width:200, editable:true  },
			      	{name: 'merchanttype', index: 'merchanttype' , width:200, editable:true },
			      	{name: 'merchantlocation', index: 'merchantlocation' , width:300, editable:true },
			      	{name: 'status', index: 'status' , width:80, editable: true }
			      	],
			      	rowNum: 10,
			      	rowList: [ 10, 20, 30],
			      	pager: '#pagerid',
			      	sortname: 'merchantid',
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
			       editurl: "${pageContext.request.contextPath}/html/simpleform.action?screenName=JspTestEditCrud&bulkcmd=grid",	
			       caption: "Merchant List"
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
			}
			

			function ajaxSubmit(){
				jQuery.post("${pageContext.request.contextPath}/html/simpleform.action?screenName=JspTestEditCrud", 
						$("#form1").serialize(),
						function(data){
					var json = jQuery.parseJSON(data);
					jQuery("#listid").trigger("reloadGrid");
                });
			}
			
		function getJqgridData(){
			var json;
				  jQuery.get("jsrpc.action?screenName=authorizeCardList&submitdata={form1:[{command:'orderList'}]}", function(data){
//					alert(data);
					 json = jQuery.parseJSON(data);
					  formdata = json.data.form1
						calljqgrid(formdata);
                    });
				
			}
			
			function getOrderList(){
				  jQuery.get("jsrpc.action?screenName=authorizeCardList&submitdata={form1:[{command:'orderList'}]}", function(data){
						var json = jQuery.parseJSON(data);
						var formdata = json.data.form1;
						iadt.updateUIRows(formdata);
                    });
					//tableToGrid("#tablespace");
			}
			
			
        </script>
    </head>
    <body>
    <s:property value="jsrule" />
    <s:property value="#resultDTO" />
    <s:if test="jsrule != null">hi</s:if>
    
   resDTO= ${resultDTO.data.formonload[0].merchantlocation}
    	<table width="100%" cellpadding="4" cellspacing="0" border="0" class="head1"  height="20" >
                <tr>
                    <td>
                        Jsp Test View (MERCHANT) 
                    </td>
                </tr>
		</table>
		<table width="100%" cellpadding="4" cellspacing="0" border="0"  height="20" >
                <tr>
                    <td>
                       &nbsp;
                    </td>
                </tr>
				
		</table>
		<table>
		       <tr><td>Merchant Id </td><td><s:property value="#resultDTO.data.formonload[0].merchantid"  /></td></tr>
        	   <tr><td>Merchant Type </td><td><s:property value="#resultDTO.data.formonload[0].merchanttype"  /></td></tr>
        	   <tr><td>Merchant Location </td><td><s:property value="#resultDTO.data.formonload[0].merchantlocation"  /></td></tr>
        	   <tr><td>Status </td><td><s:property value="#resultDTO.data.formonload[0].status"  /></td></tr>
         </table>
		 <table id="listid" ></table>
		 <div id="pagerid"></div>
		 
		 
        <form name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/html/simpleform.action?screenName=JspTestEditCrud">
        	 <table>
        	  <tr><td>Merchant Id </td><td><input name="merchantid" id="merchantid" value="${resultDTO.data.formonload[0].merchantid}"/></td></tr>
        	   <tr><td>Merchant Type </td><td><input name="merchanttype" id="merchanttype" value="${resultDTO.data.formonload[0].merchanttype}"/></td></tr>
        	   <tr><td>Merchant Location </td><td><input name="merchantlocation" id="merchantlocation" value="${resultDTO.data.formonload[0].merchantlocation}"/></td></tr>
        	   <tr><td>Status </td><td><input name="status" id="status" value="${resultDTO.data.formonload[0].status}"/></td></tr>
        	   

			 </table>
        	 
        	 bulkcmd: <input name="bulkcmd" value="frmgridadd"/>
        	 <button >submit</button>
        	 <button type="button" onclick="ajaxSubmit()">Ajax Submit</button>
        </form>
    </body>
    <html>
