
<html>
<head>
<%@page pageEncoding="Cp1252" contentType="text/html; charset=Cp1252" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
<title>Merchant Search</title>
<s:head   />
<%String ctxstr = request.getContextPath(); %>
<link href="../css/body.css" rel="stylesheet" type="text/css">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<link href="../css/jquery.validation.css" rel="stylesheet" type="text/css">
<link href="../css/jquery-ui-1.8.15.custom.css" rel="stylesheet" type="text/css">
<link href="../css/ui.jqgrid.css" rel="stylesheet" type="text/css">
<link href="../css/ui.multiselect.css" rel="stylesheet" type="text/css">


<script src="../js/jquery-1.4.4.min.js"></script>
<!--  <%-- <script src="../js/jquery-1.6.2.min.js"></script> --%>-->
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/fg.menu.js"></script>
<script type="text/javascript" src="../js/jquery.tmpl.js"></script>
<script type="text/javascript" src="../js/jsonStringify.js"></script>
<script type="text/javascript" src="../js/iadtframework.js"></script>
<script src="../js/jquery-ui-1.8.15.custom.min.js" type="text/javascript"></script>
<script src="../js/jquery.layout.js" type="text/javascript"></script>
<script src="../js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script type="text/javascript">
    $.jgrid.no_legacy_api = true;
    $.jgrid.useJSON = true;
</script>
<script src="../js/ui.multiselect.js" type="text/javascript"></script>
<script src="../js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="../js/jquery.jqgrid.fluid.js" type="text/javascript"></script>
<script src="../js/jquery.tablednd.js" type="text/javascript"></script>
<script src="../js/jquery.contextmenu.js" type="text/javascript"></script> 
<script>
function search(){
	var griddata = [
{MERCHANT_ID:'10',MERCHANT_TYPE:'POS',MERCHANT_LOCATION:'Suntec City' }, 
{MERCHANT_ID:'11',MERCHANT_TYPE:'POS',MERCHANT_LOCATION:'Raffles City' }, 
{MERCHANT_ID:'12',MERCHANT_TYPE:'POS',MERCHANT_LOCATION:'Plaza Singapura' } 
];
	  jQuery("#list2").jqGrid( {
	      	//url: 'jsrpc.action?screenName=TestPage&submitdata={bulkcmd="prodgrid"}&q=2&_search=false&nd=1314263082628&rows=10&page=2&sidx=id&sord=desc',
	      	url: '<%= request.getContextPath() %>/jqgrid.action?command=true&screenName=merchant_view&submitdata={bulkcmd:"search_mer"}',
	      	//url: 'http://localhost/jqgrid/server.php?q=2',
	   	    datatype: "json", 
	   	   //  data: griddata,
	   	   // data: mydata,
	      	colNames: [ 'Merchant ID',  'Merchant Type','Location' ],
	      	colModel: [
	      		{name:'MERCHANT_ID',index:'MERCHANT_ID', width:200 },
	      		{name:'MERCHANT_TYPE',index:'MERCHANT_TYPE', width:200, editable: true },
	      		{name:'MERCHANT_LOCATION',index:'MERCHANT_LOCATION', width:150, editable: true},
	      	],
	      	rowNum: 10,
	      	rowList: [ 10, 20, 30],
	      	pager: '#pager2',
	      	sortname: 'MERCHANT_ID',
	        viewrecords: true,
	        sortorder: "desc",
	        jsonReader: {
	    		repeatitems : false,
	    		id: "0"
	    	},
	    	loadError : function(xhr,st,err) { jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText); },
	    	
	        editurl: '<%= request.getContextPath() %>/simpleform.action?screenName=merchant_view&bulkcmd=blk',	        
	    	ondblClickRow: function(id){ alert("You double click row with id: "+id);},
	       caption: "Merchant List"
	   } );

	 //   jQuery("#list2").jqGrid('navGrid','#pager2',{del: false,add: false,edit:false},{},{},{},{multipleSearch:true});
	  jQuery("#list2").jqGrid('navGrid','#pager2',{del: true,add:true,edit: true});
	  resize_the_grid();
}
function resize_the_grid()
{
 jQuery('#list2').fluidGrid({});
}

$(window).resize(resize_the_grid);

function editMerchant(){
	var id= jQuery('#list2').jqGrid('getGridParam','selrow'); 
	if (id)	{
		var ret = jQuery("#list2").jqGrid('getRowData',id);
		alert("MERCHANT_ID="+ret.MERCHANT_ID+" MERCHANT_TYPE="+ret.MERCHANT_TYPE+" MERCHANT_LOCATION="+ret.MERCHANT_LOCATION);
	} else { alert("Please select row");}
}
</script>
</head>
<body onload="search()">
<h1>Search Merchant</h1>
 
<s:actionerror/>
<s:actionmessage/>

<p>
<table class="table1">
<tr><td>Merchant ID:</td><td>
<input type="text" name="mearchantid_search" id="mearchantid_search" /></td><td><button onclick="search()">Search</button>
</td>
</tr>
</table>
<p/>
<p> 
 <span id="rsperror" style="color:red"></span>
 <table id="list2" style="width:500px"></table>
 <div id="pager2"></div>
</p>   
<button type="button">View Merchant Details</button>
<button type="button">Edit Merchant Details </button>
<button type="button">Delete Merchant</button>

</body>
</html>
