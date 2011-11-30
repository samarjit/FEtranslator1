<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Universal Bank</title>
        <link href="../css/body.css" rel="stylesheet" type="text/css">
        <link href="../css/main.css" rel="stylesheet" type="text/css">
		<script  src="../js/jquery-1.4.4.min.js"></script>
		<script  src="../js/jquery.validate.js"></script>
		<script type="text/javascript" src="../js/fg.menu.js"></script>
		<script type="text/javascript" src="../js/jquery.tmpl.js"></script>
		<script type="text/javascript" src="../js/jsonStringify.js"></script>
		<script type="text/javascript" src="../js/iadtframework.js"></script>
		
		<link rel="stylesheet" type="text/css" media="screen" href="../css/jquery-ui-1.8.1.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="../css/ui.jqgrid.css" />
		
		<script src="../js/i18n/grid.locale-en.js" type="text/javascript"></script>
		<script src="../js/jquery.jqGrid.src.js" type="text/javascript"></script>
        <script type="text/javascript">
        	var rulesframework = ${jsrule};
			var fieldlist ="invoice,productCode,productName,plasticCode,plasticDesc,quantity,orderBy,orderDate,authorizeStatus".split(",");
			var keylist =  "productCode,plasticCode,quantity".split(",");
			var unqFldId = "invoice"; 
			var formdata;			
            $(document).ready(function(){
				iadt.setFieldlist(fieldlist);
				
				//getJqgridData();
				calljqgrid();
				//getOrderList();				
            });
			
			
			function calljqgrid(formdata){
				jQuery("#listid").jqGrid( {

			      	url:'<%= request.getContextPath() %>/jqgrid.action?command=true&screenName=JspTest&submitdata={bulkcmd="simOnload"}',
			      	datatype: "json",
			      	colNames:['Merchant Id','Merchant Type','Merchant Location','Status'      	],
			      	colModel:[
			      	{name: 'merchantid', index: 'merchantid' , width:200 },
			      	{name: 'merchanttype', index: 'merchanttype' , width:200 },
			      	{name: 'merchantlocation', index: 'merchantlocation' , width:300 },
			      	{name: 'status', index: 'status' , width:80 }
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
			       caption: "Merchant List"
			   } ).navGrid('#pagerid',{edit:true,add:true,del:true});
			
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
			
			function gotoEditPage(){
					var act  = "jsrpc.action?screenName=JspTest&submitdata={bulkcmd:'fromViewTransition'}";					
					var frm = document.getElementById("form1");
					frm.action = act;
					frm.submit();
				}
			
			
        </script>
    </head>
    <body>
    <s:property value="jsrule" />
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
		 <table id="listid" width="500px"></table>
		 <div id="pagerid"></div>
		 
		 
        <form name="form1" id="form1" method="post">
        	 <button type="button" onclick="gotoEditPage()">Goto Edit</button>
        </form>
    </body>
    <html>
