function createXMLDocument(string)
{
var browserName = navigator.appName;
var doc;
	if (browserName == 'Microsoft Internet Explorer')
	{
		doc = new ActiveXObject('Microsoft.XMLDOM');
		doc.async = 'false'
		doc.loadXML(string);
	} else {
		doc = (new DOMParser()).parseFromString(string, 'text/xml');
	}
	return doc;
}

var menuxml1 = '<?xml version="1.0" encoding="ISO-8859-1"?><root><tab name="Card Inventory" class="fg-button" id="tab1" onclick="cardinventory.html"><menu name="CardOrder" position="1" class="mi" id="tab1menu1" onclick="cmsAction.action?screenName=cardOrder" ></menu><menu name="Authorize" position="1" class="mi" id="tab1menu2" onclick="cmsAction.action?screenName=authorizeCardList"></menu><menu name="GenerateCard" position="2" class="mi" id="tab1menu3" onclick="cmsAction.action?screenName=generateCardList"></menu><menu name="Emboss" position="2" class="mi" id="tab1menu3" onclick="cmsAction.action?screenName=embossList"></menu><menu name="Card Receipt" position="2" class="mi" id="tab1menu3" onclick="../html/cardReceipt.html"></menu><menu name="Card Order Status" position="2" class="mi" id="tab1menu3" onclick="../html/cardStatus.html"></menu></tab></root>';
var tabmenuIdlist = [];

function createMenu2(divid){
	var xmldoc = createXMLDocument(menuxml);
	var div = document.createElement("div");
	$('#' + divid).append(div);
	div.setAttribute("class", "hbuttons");
	div.setAttribute("id", "tabdiv");
	var li = document.createElement("li");
	div.appendChild(li);
	li.setAttribute("class", "tabCollection");
	$(xmldoc).find("tab").each(function() {
		var a = document.createElement("a");
		$(a).attr('id',$(this).attr("id"));
		$(a).attr('target','leftFrame');
		var onclick = 'fun(this.id);';
		onclick = onclick+ "MM_goToURL('"+$(this).attr("onclick")+"');"; 
		onclick=onclick+ "return false";
		$(a).attr('onclick',onclick);
		$(a).attr('href',$(this).attr("onclick"));
		var key = $(this).attr("key");
		if (key != null && key.trim() != "" && key != undefined) {
			$(a).append(key);
		} else {
			$(a).append($(this).attr("name"));
		}
	//	$(li).append(a);
		li.appendChild(a);
		//$("#"+liid).append("<a href='"+onclick+"' target='mainframe' id='"+id+"' onclick=\"fun(this.id);MM_goToURL('"+menufilename+"');return false\" >"+name+" </a>");
		
		var menu = {tabid :$(this).attr("id"),menuFile:$(this).attr("onclick")};
		tabmenuIdlist.push(menu);
	});
 console.dir(tabmenuIdlist);
}


function createMenu(divid){
	var xmldoc = createXMLDocument(menuxml);
	var div = document.createElement("ul");
	
	$('#' + divid).append(div);
	div.setAttribute("class", "hbuttons");
	div.setAttribute("id", "nav");
	
	
	
	$(xmldoc).find("tab").each(function(k,tab) {
		//navbar
		var li = document.createElement("li");
		li.setAttribute("class", "tabCollection");
		div.appendChild(li);
		
		var a = document.createElement("a");
		$(a).attr('id',$(this).attr("id"));
		$(a).attr('target','leftFrame');
		var onclick = 'fun(this.id);';
		onclick = onclick+ "MM_goToURL('"+$(this).attr("onclick")+"');"; 
		onclick=onclick+ "return false";
		$(a).attr('onclick',onclick);
		if(typeof($(this).attr("onclick")) != 'undefined')
		 $(a).attr('href',$(this).attr("onclick"));
		var key = $(this).attr("key");
//		if (key != null && key.trim() != "" && key != undefined) {
//			$(a).append(key);
//		} else {
			$(a).append($(this).attr("name"));
//		}
	//	$(li).append(a);
		li.appendChild(a);
		var ul = document.createElement("ul");
		li.appendChild(ul);
		var menuid =$(this).attr("id");
//		var menufile = $(tab).attr('onclick');
//		if (menufile != "" && menufile != undefined) {
//			if (menufile == filename) {
				var menulist = $(tab).children("menu");
				var tabid = $(tab).attr("id");
				var ele = ul;//document.getElementById(menuid);
				updateMenu(ele,tabid,menulist,"menu");
//			}
//		}
	});
}

function updateMenu(ele,liid,list,type) {
	if(list.length > 0){
		var ulele;
		if(type == "menu"){
			ulele = ele;
		}else{
			var ulid = liid+"_s";
			$(ele).append("<ul id='"+ulid+"'></ul>");
			ulele = document.getElementById(ulid);
		}
		$(list).each(function(i,menu) {
			var name = $(menu).attr("name");
			var id = $(menu).attr("id");
			var onclick = $(menu).attr("onclick");
			$(ulele).append("<li id='"+id+"' ><a   href='"+onclick+"' target='mainFrame'>"+name+"</a></li>");
			var liele = document.getElementById(id);
			var childlist = $(menu).children("submenu");
			//alert(childlist.toString());
			if(childlist.length > 0){
				$(liele).find('a').addClass('drop');
				updateMenu(liele,id,childlist,"submenu");
			}
		});
	}
}