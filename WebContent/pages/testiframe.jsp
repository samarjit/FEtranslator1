<html>
<title>Iframe test</title>

<head>
<script>
function parentJsCall(){
	window.parent.testIframeAction('hi');
}
</script>
</head>

<body>
<input type=button value="call parent js" onclick="parentJsCall()" />
</body>

</html>