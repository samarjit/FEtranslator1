<%@page pageEncoding="Cp1252" contentType="text/html; charset=Cp1252" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
<title>Merchant View Details</title>
<style>
.listtable .data{
font-weight: normal;
color: #666;
}
</style>
<s:head   />
<%String ctxstr = request.getContextPath(); %>
<link href="../css/body.css" rel="stylesheet" type="text/css">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<link href="../css/jquery.validation.css" rel="stylesheet" type="text/css">
<link href="../css/jquery-ui-1.8.15.custom.css" rel="stylesheet" type="text/css">
<link href="../css/ui.jqgrid.css" rel="stylesheet" type="text/css">
<link href="../css/ui.multiselect.css" rel="stylesheet" type="text/css">


<script src="../js/jquery-1.4.4.min.js"></script>
<%-- <script src="../js/jquery-1.6.2.min.js"></script> --%>
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
<script src="../js/jquery.tablednd.js" type="text/javascript"></script>
<script src="../js/jquery.contextmenu.js" type="text/javascript"></script> 
<script src="../js/jquery.jqgrid.fluid.js" type="text/javascript"></script> 
<script>
function dealList(){
	  jQuery("#list2").jqGrid( {
	      	//url: 'jsrpc.action?screenName=TestPage&submitdata={bulkcmd="prodgrid"}&q=2&_search=false&nd=1314263082628&rows=10&page=2&sidx=id&sord=desc',
	      	url: '<%= request.getContextPath() %>/jqgrid.action?command=true&screenName=deal_view&submitdata={bulkcmd="search_deal"}',
	      	//url: 'http://localhost/jqgrid/server.php?q=2',
	   	    datatype: "json",
	   	   // data: mydata,
	      	colNames: ['Deal id','Merchant id','Offering quantity','Offering qty left','Offering item','Deal start date','Deal end date','Short desc','Detailed desc','Partial reg limit','Full reg limit','Partial reg limit left','Full reg limit left','Create date','Promotion id','Min transits per interval','Transit interval period days','Units per member per interval','Units deal per interval','Deal interval days'],
	      	colModel: [
	      		{name: 'dealid', index: 'dealid' , width:20 },
				{name: 'merchantid', index: 'merchantid' , width:20 },
				{name: 'offeringquantity', index: 'offeringquantity' , width:6 },
				{name: 'offeringqtyleft', index: 'offeringqtyleft' , width:6 },
				{name: 'offeringitem', index: 'offeringitem' , width:20 },
				{name: 'dealstartdate', index: 'dealstartdate' , width:8 },
				{name: 'dealenddate', index: 'dealenddate' , width:8 },
				{name: 'shortdesc', index: 'shortdesc' , width:50 },
				{name: 'detaileddesc', index: 'detaileddesc' , width:30 },
				{name: 'partialreglimit', index: 'partialreglimit' , width:6 },
				{name: 'fullreglimit', index: 'fullreglimit' , width:6 },
				{name: 'partialreglimitleft', index: 'partialreglimitleft' , width:6 },
				{name: 'fullreglimitleft', index: 'fullreglimitleft' , width:6 },
				{name: 'createdate', index: 'createdate' , width:8 },
				{name: 'promotionid', index: 'promotionid' , width:20 },
				{name: 'mintransitsperinterval', index: 'mintransitsperinterval' , width:6 },
				{name: 'transitintervalperioddays', index: 'transitintervalperioddays' , width:6 },
				{name: 'unitspermemberperinterval', index: 'unitspermemberperinterval' , width:6 },
				{name: 'unitsdealperinterval', index: 'unitsdealperinterval' , width:6 },
				{name: 'dealintervaldays', index: 'dealintervaldays' , width:6 }
	      	],
	      	rowNum: 10,
	      	rowList: [ 10, 20, 30],
	      	pager: '#pager2',
	      	sortname: 'dealid',
	        viewrecords: true,
	        sortorder: "desc",
	        jsonReader: {
	    		repeatitems : false,
	    		id: "0"
	    	},
	    	ondblClickRow: function(id){ alert("You double click row with id: "+id);
	    	var ret = jQuery("#list2").jqGrid('getRowData',id);
	    	console.log(ret);
	    	},
	       caption: "Merchant List"
	   } );

	    jQuery("#list2").jqGrid('navGrid','#pager2',{del: false,add: false,edit:false},{},{},{},{multipleSearch:true});
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
<body onload="dealList()">
 
 
<s:actionerror/>
<s:actionmessage/>

<table id="list2"></table>
<div id="pager2"></div>
 
<form>

<p>&nbsp;</p>
<table width="100%" class="listtable">
 <tr>
 <th colspan="2" class="">Deals</th> </tr>
 <tr><td>Deal ID:</td><td class="data"><input type="text" id="merchantid"                 name="merchantid"                      /></td></tr>
<tr><td>Merchant Id                   </td><td><input type="text" id="merchantid"                 name="merchantid"                      /></td></tr>
<tr><td>Offering Quantity             </td><td><input type="text" id="offeringquantity"           name="offeringquantity"                /></td></tr>
<tr><td>Offering Qty Left             </td><td><input type="text" id="offeringqtyleft"            name="offeringqtyleft"           /></td></tr>
<tr><td>Offering Item                 </td><td><input type="text" id="offeringitem"               name="offeringitem"              /></td></tr>
<tr><td>Deal Start Date               </td><td><input type="text" id="dealstartdate"              name="dealstartdate"             /></td></tr>
<tr><td>Deal End Date                 </td><td><input type="text" id="dealenddate"                name="dealenddate"               /></td></tr>
<tr><td>Short Desc                    </td><td><input type="text" id="shortdesc"                  name="shortdesc"                 /></td></tr>
<tr><td>Detailed Desc                 </td><td><input type="text" id="detaileddesc"               name="detaileddesc"              /></td></tr>
<tr><td>Partial Reg Limit             </td><td><input type="text" id="partialreglimit"            name="partialreglimit"           /></td></tr>
<tr><td>Full Reg Limit                </td><td><input type="text" id="fullreglimit"               name="fullreglimit"              /></td></tr>
<tr><td>Partial Reg Limit Left        </td><td><input type="text" id="partialreglimitleft"        name="partialreglimitleft"       /></td></tr>
<tr><td>Full Reg Limit Left           </td><td><input type="text" id="fullreglimitleft"           name="fullreglimitleft"          /></td></tr>
<tr><td>Create Date                   </td><td><input type="text" id="createdate"                 name="createdate"                /></td></tr>
<tr><td>Promotion Id                  </td><td><input type="text" id="promotionid"                name="promotionid"               /></td></tr>
<tr><td>Min Transits Per Interval     </td><td><input type="text" id="mintransitsperinterval"     name="mintransitsperinterval"    /></td></tr>
<tr><td>Transit Interval Period Days  </td><td><input type="text" id="transitintervalperioddays"  name="transitintervalperioddays" /></td></tr>
<tr><td>Units Per Member Per Interval </td><td><input type="text" id="unitspermemberperinterval"  name="unitspermemberperinterval" /></td></tr>
<tr><td>Units Deal Per Interval       </td><td><input type="text" id="unitsdealperinterval"       name="unitsdealperinterval"      /></td></tr>
<tr><td>Deal Interval Days            </td><td><input type="text" id="dealintervaldays"           name="dealintervaldays"          /></td></tr>
<tr><td>Terms And Cond                </td><td><input type="text" id="termsandcond"               name="termsandcond"              /></td></tr>

</table>

</body>
</html>
