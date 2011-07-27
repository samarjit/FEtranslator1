function fun(id) {
	$.each(tabmenuIdlist,function(k,v){
		var tab = v.tabid;
		if(tab != id){
			document.getElementById(tab).style.background = 'url(../images/menu_gray.gif)';	
		}else{
			document.getElementById(id).style.background = 'url(../images/menu_orange.gif)';
		}
	});
}

function MM_goToURL(leftframe) { 
		document.MM_returnValue = false;
		parent.frames['topFrame1'].location = "lefttop.html";
		parent.frames['leftFrame'].location = leftframe;
		parent.frames['bottomFrame'].location = "leftbottom.html";
		//parent.frames['mainFrame'].location = mainframe;
}

function prifun() {
	var print_enab = parent.mainFrame.document.getElementById("print_enab");

	if (print_enab.value == 'Y') {

		var ele = parent.mainFrame.document.getElementById("template1");
		var eleimage = document.getElementById("template2");

		var html = "";

		html = eleimage.innerHTML + ele.innerHTML;

		var printWin = window.open("", "PrintPreview");
		printWin.document.open();
		printWin.document
				.write("<html><head><scri"
						+ "pt language='javascript' src='css_script.js'></scr"
						+ "ipt>");
		printWin.document
				.write("<style>Top Margin: 0px;Left Margin:0px;Bottom Margin:0px;Right Margin: 0px;</style></head><body class='body'>");
		printWin.document.write("<table width='100%'><tr><td>");
		printWin.document.write(html);
		printWin.document.write("</td></tr><tr><td>");
		printWin.document
				.write("<table id='printbutton' width='100%'><tr align=center><td><a href='#'><img src='buttons/print.png' border=0 style='cursor:hand;backgroun-color:white' onclick='printfun()'></a></td></tr></table></td></tr></table></body>");
		printWin.document.write("<scri" + "pt language='javascript'>");
		printWin.document
				.write("function printfun(){ var printobj=document.getElementById('printbutton'); printobj.style.display='none'; window.print();}</scri"
						+ "pt>");
		printWin.document.write("</html>");
		printWin.document.close();
	}

	else {
		alert("Sorry : Print option is not enabled to this page....");
	}
}

function getMenuXml() {
alert("getMenuXml");
	$.ajax({
		url : 'menu.xml',
		type : 'GET',
		async : false,
		success : function(data) { // grab content from another page
			menuxml = data;
			alert(menuxml);
		},
		dataType : 'xml'
	});
}

function tabmenu(divid) {
	//getMenuXml();
	var div = document.createElement("div");
	$('#' + divid).append(div);
	div.setAttribute("class", "hbuttons");
	div.setAttribute("id", "tabdiv");
	var li = document.createElement("li");
	div.appendChild(li);
	li.setAttribute("class", "tabCollection");
	xmlDoc = $.parseXML(menuxml);
	$(xmlDoc).find("tab").each(function() {
		var a = document.createElement("a");
		$(a).attr('id',$(this).attr("id"));
		$(a).attr('target','leftFrame');
		var onclick = 'fun(this.id);';
		onclick = onclick+ "MM_goToURL('"+$(this).attr("onclick")+"');"; 
		onclick=onclick+ "return false";
		$(a).attr('onclick',onclick);
		$(a).attr('href',$(this).attr("onclick"));
		$(a).append($(this).attr("name"));
//		$(li).append(a);
		li.appendChild(a);
		var menu = {tabid :$(this).attr("id"),menuFile:$(this).attr("onclick")};
		tabmenuIdlist.push(menu);
	});
}

function createLeftMenu(filename, menuid) {
	xmlDoc = $.parseXML(menuxml);
	$(xmlDoc).find("tab").each(function() {
		var menufile = $(this).attr('onclick');
		if (menufile != "" && menufile != undefined) {
			if (menufile == filename) {
				$(this).find("menu").each(function() {
					var li = document.createElement("li");
					$("#" + menuid).append(li);
					var a = document.createElement("a");
					$(li).append(a);
					$(a).attr("id", $(this).attr("id"));
					$(a).attr("target", "mainFrame");
					$(a).attr("href", $(this).attr('onclick'));
					$(a).append($(this).attr("name"));
					if ($(this).find("submenu").length > 0) {
						var ul = document.createElement("ul");
						$(li).append(ul);
						$(this).find("submenu").each(function() {
							var sli = document.createElement("li");
							$(ul).append(sli);
							var sa = document.createElement("a");
							$(sli).append(sa);
							$(sa).attr("id", $(this).attr("id"));
							$(sa).attr("target", "mainFrame");
							$(sa).attr("href", $(this).attr('onclick'));
							$(sli).append($(this).text());
						});
					}
				});
			}
		}
	});
}


//Global Variables

var menuxml = '<?xml version="1.0" encoding="ISO-8859-1"?><root><tab name="Card Inventory" class="fg-button" id="tab1" onclick="cardinventory.html"><menu name="CardOrder" position="1" class="mi" id="tab1menu1" onclick="cmsAction.action?screenName=cardOrder" ></menu><menu name="Authorize" position="1" class="mi" id="tab1menu2" onclick="cmsAction.action?screenName=authorizeCardList"></menu><menu name="GenerateCard" position="2" class="mi" id="tab1menu3" onclick="cmsAction.action?screenName=generateCardList"></menu><menu name="Emboss" position="2" class="mi" id="tab1menu3" onclick="cmsAction.action?screenName=embossList"></menu><menu name="Card Receipt" position="2" class="mi" id="tab1menu3" onclick="../html/cardReceipt.html"></menu><menu name="Card Order Status" position="2" class="mi" id="tab1menu3" onclick="../html/cardStatus.html"></menu></tab></root>';
var tabmenuIdlist = [];