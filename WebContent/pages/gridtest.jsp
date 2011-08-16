<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
   "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns = "http://www.w3.org/1999/xhtml">
<head>
   <title></title>
<link rel="stylesheet" type="text/css" media="screen" href="../css/jquery-ui-1.8.15.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/ui.multiselect.css" />

<script src="../js/jquery-1.4.4.min.js" type="text/javascript"></script>
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

   <script type = "text/javascript">
   var mydata =[{"PLASTIC_CODE":"FEVCUS-FEVO Customer design","PLASTIC_DESC":"FEVO Customer design","PRODUCT_CODE":"TEST01","PRODUCT_NAME":"TESTING"},
                {"PLASTIC_CODE":"EMVBLK-EMV Black(unprinted)","PLASTIC_DESC":"EMV Black(unprinted)","PRODUCT_CODE":"EMV083","PRODUCT_NAME":"EMV Generic Product"},
                {"PLASTIC_CODE":"EMVBLK-EMV Black(unprinted)","PLASTIC_DESC":"EMV Black(unprinted)","PRODUCT_CODE":"EMV040","PRODUCT_NAME":"EMV Reloadable Generic"},
                {"PLASTIC_CODE":"EMVBLK-EMV Black(unprinted)","PLASTIC_DESC":"EMV Black(unprinted)","PRODUCT_CODE":"EMV093","PRODUCT_NAME":"EMV Reloadable SE OTC"},
                {"PLASTIC_CODE":"FEVCUS-FEVO Customer design","PLASTIC_DESC":"FEVO Customer design","PRODUCT_CODE":"EMV081","PRODUCT_NAME":"EMV081"},
                {"PLASTIC_CODE":"EMVBLK4-EMV RLDB Corporate","PLASTIC_DESC":"EMV RLDB Corporate","PRODUCT_CODE":"EMV085","PRODUCT_NAME":"EMV RLDB Corporate"},
                {"PLASTIC_CODE":"EMVBLK5-RLDB Bulk","PLASTIC_DESC":"RLDB Bulk","PRODUCT_CODE":"EMV086","PRODUCT_NAME":"RLDB Bulk"},
                {"PLASTIC_CODE":"EMV-NFC","PLASTIC_DESC":"NFC","PRODUCT_CODE":"EMV087","PRODUCT_NAME":"NFC"},
                {"PLASTIC_CODE":"EMVBLK-EMV Black(unprinted)","PLASTIC_DESC":"EMV Black(unprinted)","PRODUCT_CODE":"EMV094","PRODUCT_NAME":"EMV GIFT OTC"},
                {"PLASTIC_CODE":"FEVSTD-FEVO Standard design","PLASTIC_DESC":"FEVO Standard design","PRODUCT_CODE":"FEVO04","PRODUCT_NAME":"Standard RoadShow Gift"},
                {"PLASTIC_CODE":"FEVCUS-FEVO Customer design","PLASTIC_DESC":"FEVO Customer design","PRODUCT_CODE":"FEGTCU","PRODUCT_NAME":"FEVO Gift card Customized"},
                {"PLASTIC_CODE":"FEVSTD-FEVO Standard design","PLASTIC_DESC":"FEVO Standard design","PRODUCT_CODE":"FEGTST","PRODUCT_NAME":"FEVO Gift card Standard"},
                {"PLASTIC_CODE":"FEVWHT-FEVO White (unprinted) design","PLASTIC_DESC":"FEVO White (unprinted) design","PRODUCT_CODE":"FERSCU","PRODUCT_NAME":"FEVO Road show Customized"},
                {"PLASTIC_CODE":"EZLSTD-EZL Standard design","PLASTIC_DESC":"EZL Standard design","PRODUCT_CODE":"FEVOSP","PRODUCT_NAME":"FEVO Special Edition"},
                {"PLASTIC_CODE":"EZLWHT-EZL Standard design","PLASTIC_DESC":"EZL Standard design","PRODUCT_CODE":"EZLCUS","PRODUCT_NAME":"Personalised FEVO with ez-link"},
                {"PLASTIC_CODE":"EZLSTD-EZL Standard design","PLASTIC_DESC":"EZL Standard design","PRODUCT_CODE":"EZLSPS","PRODUCT_NAME":"FEVO Special Edition"},
                {"PLASTIC_CODE":"EZLSTD-EZLINK Standard design","PLASTIC_DESC":"EZLINK Standard design","PRODUCT_CODE":"ECLC52","PRODUCT_NAME":"Fevo W EZL customised"},
                {"PLASTIC_CODE":"EZLWHT-EZLINK Custom design","PLASTIC_DESC":"EZLINK Custom design","PRODUCT_CODE":"EZLS56","PRODUCT_NAME":"OTC FEVO w EZL Customized"},
                {"PLASTIC_CODE":"FEVWHT-FEVO White (unprinted) design","PLASTIC_DESC":"FEVO White (unprinted) design","PRODUCT_CODE":"EZLS21","PRODUCT_NAME":"SPECIAL EDITION - FEVO"},
                {"PLASTIC_CODE":"EZLSTD-EZLINK Standard design","PLASTIC_DESC":"EZLINK Standard design","PRODUCT_CODE":"EZLS53","PRODUCT_NAME":"Road show FEVO w EZL Standard"},
                {"PLASTIC_CODE":"EZLWHT-EZLINK Custom design","PLASTIC_DESC":"EZLINK Custom design","PRODUCT_CODE":"EZLC54","PRODUCT_NAME":"Road show FEVO w EZL Customised"},
                {"PLASTIC_CODE":"EZLSTD-EZLINK Standard design","PLASTIC_DESC":"EZLINK Standard design","PRODUCT_CODE":"FEVO02","PRODUCT_NAME":"Customised Online Gift"},
                {"PLASTIC_CODE":"FEVSTD-FEVO Standard design|FEVO White (unprinted) design","PLASTIC_DESC":"FEVO Standard design","PRODUCT_CODE":"FEVO01","PRODUCT_NAME":"Standard Online Gift"},
                {"PLASTIC_CODE":"FEVSTD-FEVO Standard design|FEVO White (unprinted) design","PLASTIC_DESC":"FEVO Standard design","PRODUCT_CODE":"ECLC51","PRODUCT_NAME":"Fevo W EZL standard"},
                {"PLASTIC_CODE":"EZLWHT-EZLINK Custom design","PLASTIC_DESC":"EZLINK Custom design","PRODUCT_CODE":"EZLS22","PRODUCT_NAME":"SPECIAL EDITION - EZL"},
                {"PLASTIC_CODE":"FEVCUS-FEVO Customer design","PLASTIC_DESC":"FEVO Customer design","PRODUCT_CODE":"FEVO03","PRODUCT_NAME":"Customised RoadShow Gift"},
                {"PLASTIC_CODE":"EZLSTD-EZLINK Standard design","PLASTIC_DESC":"EZLINK Standard design","PRODUCT_CODE":"EZONLI","PRODUCT_NAME":"EZONLI"},
                {"PLASTIC_CODE":"EZLSTD-EZLINK Standard design","PLASTIC_DESC":"EZLINK Standard design","PRODUCT_CODE":"EZLS55","PRODUCT_NAME":"OTC FEVO w EZL Standard"},
                {"PLASTIC_CODE":"EZLWHT-EZLINK Custom design","PLASTIC_DESC":"EZLINK Custom design","PRODUCT_CODE":"EZLS24","PRODUCT_NAME":"OTC SPECIAL EDITION - EZL"},
                {"PLASTIC_CODE":"FEVSTD-FEVO Standard design","PLASTIC_DESC":"FEVO Standard design","PRODUCT_CODE":"EPFEVO","PRODUCT_NAME":"EPFEVO MAIN"},
                {"PLASTIC_CODE":"EMVBLK-EMV Black(unprinted)","PLASTIC_DESC":"EMV Black(unprinted)","PRODUCT_CODE":"EMV082","PRODUCT_NAME":"EMV Generic"}, 
                {"PLASTIC_CODE":"EMVBLK-EMV Black(unprinted)","PLASTIC_DESC":"EMV Black(unprinted)","PRODUCT_CODE":"EMV084","PRODUCT_NAME":"EMV084 Generic Product Type"}, 
                {"PLASTIC_CODE":"EMVBLK-EMV Black(unprinted)","PLASTIC_DESC":"EMV Black(unprinted)","PRODUCT_CODE":"EMV091","PRODUCT_NAME":"EMV Reloadable Generic"},
                {"PLASTIC_CODE":"EMVBLK-EMV Black(unprinted)","PLASTIC_DESC":"EMV Black(unprinted)","PRODUCT_CODE":"EMV092","PRODUCT_NAME":"EMV Reloadable SE"}];
   
     function fn(){
   /*       $.getJSON('jsrpc.action?screenName=TestPage&submitdata={bulkcmd="prodgrid"}&q=2&_search=false&nd=1314263082628&rows=10&page=2&sidx=id&sord=desc',
          function(data){
          	 alert(data.data);
          }).success(function(data1){*/
	 // jsonData = data1.data.form1;
      jQuery("#list2").jqGrid( {
      	//url: 'jsrpc.action?screenName=TestPage&submitdata={bulkcmd="prodgrid"}&q=2&_search=false&nd=1314263082628&rows=10&page=2&sidx=id&sord=desc',
      	url: '<%= request.getContextPath() %>/jqgrid.action?command=true',
      	//url: 'http://localhost/jqgrid/server.php?q=2',
   	    datatype: "json",
   	   // data: mydata,
      	colNames: ['PRODUCT_COD' ,  'PRODUCT_NAME' ,  'PLASTIC_CODE',   'PLASTIC_DESC' ],
      	colModel: [
      		{name:'PLASTIC_CODo',index:'PLASTIC_CODE', width:55 },
      		{name:'PRODUCT_NAME',index:'PRODUCT_NAME', width:90 },
      		{name:'PLASTIC_CODE',index:'PLASTIC_CODE', width:100 },
      		{name:'PLASTIC_DESC',index:'PLASTIC_DESC', width:80 },
      	],
      	rowNum: 10,
      	rowList: [ 10, 20, 30],
      	pager: '#pager2',
      	sortname: 'PLASTIC_CODE',
        viewrecords: true,
        sortorder: "desc",
       caption: "JSON Example"
   } );

    jQuery("#list2").jqGrid('navGrid','#pager2',{del:false,add:false,edit:false},{},{},{},{multipleSearch:true});
   //  }   );
}

function fn2(){
	jQuery("#list3").jqGrid({
		url: '<%= request.getContextPath() %>/jqgrid.action',
		datatype: "json",
	   	colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
	   	colModel:[
	   		{name:'id',index:'id', width:55},
	   		{name:'invdate',index:'invdate', width:90},
	   		{name:'name',index:'name asc, invdate', width:100},
	   		{name:'amount',index:'amount', width:80, align:"right"},
	   		{name:'tax',index:'tax', width:80, align:"right"},		
	   		{name:'total',index:'total', width:80,align:"right"},		
	   		{name:'note',index:'note', width:150, sortable:false}		
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#pager3',
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    caption:"JSON Example"
	});
	jQuery("#list3").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false});
}

   </script>
</head>
<body>
   <input type="button" onclick="fn()" value="click" />
   <table id="list2"></table>
   <div id="pager2"></div>
	
	<table id="list3"></table>
<div id="pager3" ></div>

</body>
</html>
