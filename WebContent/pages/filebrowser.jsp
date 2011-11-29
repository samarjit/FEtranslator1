<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
<head>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:head/>
<script src="http://code.jquery.com/jquery-latest.js" ></script>


<script><!--
var url = "<s:url action="fileservice.action"/>";
var ctxpath = "${pageContext.request.contextPath}";
var dirAlias="EMAIL_TEMPLATES";
$(document).ready(function(){
	$.ajaxSetup({
	  error: function(xhr, status, error) {
	    alert("Global AJAX error : " + status + "\nError: " + error);
	  }
	});
	
	
	$("#msg").ajaxError(function(event, request, settings, exception){
		  $(this).append("<li>Error requesting page " + settings.url + " "+exception + "</li>");
		  console.dir(settings);
		});
});

function getBackwardListing(param, relativePath){
	$("#relativePath").empty();
	$("#relativePath").text(relativePath);
	getListing("");
}
function getListing(param) {
	
	var parameters = "dirAlias="+dirAlias+"&relativePath="+$("#relativePath").text();
	if(param != null){
		parameters +="/"+param;
	}
	$.get(  url,parameters , function(data){
		$("#res").empty();
		if(typeof(data.files)!= 'undefined' && data.files.length >0){
			 
			$.each(data.files, function (i,v){
				$("<div class='files'>file:<a href='"+ctxpath+data.relativePath+"/"+v+"'>"+v+"</a></div>").appendTo($("#res"));
				//$("<div class='files'>file:<a href='javascript:getListing(\""+v+"\")'>"+v+"</a></div>").appendTo($("#res"));
			});
		}
		if(typeof(data.dirs)!= 'undefined' && data.dirs.length >0){
			
			$.each(data.dirs,  function (i,v){
				$("<div class='files'>dir:<a href='javascript:getListing(\""+v+"\")'>"+v+"</a></div>").appendTo($("#res"));
			});
		}
		
		if(typeof(data.file)!= 'undefined'){
			
			alert(data.file);
			
		}
		if(typeof(data.relativePath) != 'undefined'  && data.relativePath != ""){
			var rp = data.relativePath.split('/');
			var prevSeg = "";
			var finalPath = "";
			$.each(rp,function (i,v){
				if(v == "")return true;
				prevSeg += "/"+v;
				finalPath += "/<a href='javascript:getBackwardListing(\"\",\""+prevSeg+"\")'>"+v+"</a>";
			});
			$("#relativePathView").empty();
			$("#relativePathView").html(finalPath);
			$("#relativePath").text(data.relativePath); 
		}
		if(typeof(data.error) != 'undefined')alert(data.error);
	}).error(function(xhr, status, error){
		alert("An AJAX error occured: " + status + "\nError: " + error);
	}).complete(function(data) { 
			 
		},"text");
}

--></script>

</head>
<body>

   <br/>
<s:url action="fileservice.action" />
<div id="relativePathView"></div>
Relative Path:<div id="relativePath"><s:property value="relativePath"/></div>
<form >
Email template directory
<button type="button" onclick="getListing()">get listing</button>
</form>
<div id="res"></div>
<div id="msg"></div>
</body>

</html>
