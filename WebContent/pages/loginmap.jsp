<?xml version="1.0" encoding="ISO-8859-1" ?>

<root>
<!-- <%@taglib uri="/struts-tags" prefix="s"%>
 <%@taglib uri="/struts-dojo-tags" prefix="sx"%> -->
	<screen>
		<htmltempalte>
			pages/logintpl.xml
		</htmltempalte>
		<includedjsp>
		
		</includedjsp>
		<scripts>
			<![CDATA[
				<script>
				function javaScriptFn(){
				}
				</script>
				<s:head/>
    			<sx:head/>
				]]>
			<scriptinclude>json.js,jquery.js</scriptinclude>
		</scripts>
		<stylesheets>
			<![CDATA[
				<style>	
				.userid{
				 text-decoration:underline;
				 }
				</style>
			]]>	
				<styleinclude>home.css</styleinclude>
		</stylesheets>
	</screen>

	<panels>
		<fields>
			<field>
				
				<input type="text" class="userid" forid="userid" value="${testHeader}"
					column="USER_ID" rulemessage="" dbcolsize="" mask="" mandatory="" hidden="" >
					<rule></rule>
					<validation></validation>
					<query></query>
				</input>
			</field>

			<field>
				<input type="text" class="password" forid="password"  value="${testFooter}"
					validation="" column="PASSWD" rulemessage="" dbcolsize="" mask="">
					<rule></rule>
					<validation></validation>
					<query></query>
				</input>
			</field>
			<field>
				<customfield type="datepicker" class=" " forid="dateid" 
					validation="" column="PASSWD" rulemessage="" dbcolsize="" mask="">
					<!-- will need to experiment to see if dojo tags of struts2 say datepicker can be added? -->
					
					<text ><![CDATA[<sx:datetimepicker name="order.date" label="Order Date" displayFormat="dd-MMM-yyyy" />]]></text>
					<rule></rule>
					<validation></validation>
					<query></query>
				</customfield>
			</field>
		</fields>
		<button forid="loginbutton" onclick=""></button>
		<button forid="reset" onclick=""></button>
		freemarker start ${testHeader} and ${testFooter}  freenarker end
	</panels>
</root>