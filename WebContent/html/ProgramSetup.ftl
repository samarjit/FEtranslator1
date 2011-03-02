<?xml version="1.0" encoding="ISO-8859-1" ?>
<root>
	<screen name="ProgramSetup">
		<htmltempalte>
			html/ProgramSetup.html
		</htmltempalte>
		<includedjsp>

		</includedjsp>
		<scripts>
			<scriptinclude></scriptinclude>
			<![CDATA[    
				   ${ZHello}##<@s.property value="ZHello" escape="false" />
				 ${ZHello2}##<@s.property value="ZHello2" escape="false" />|||
				 ${resDTO.data}
				var selonload='<@s.push value="resDTO"><@s.property value="data" escape="false" /></@s.push>';
				 
				]]>
			<scriptinclude></scriptinclude>
		</scripts>
		<stylesheets>
			<styleinclude></styleinclude>
			<![CDATA[
				 jQuery.get("jsrpc.action?screenName=ProgramSetup&panelName=form1&command=selectonload",function (data){alert(data);});
			]]>

		</stylesheets>
	</screen>

	<panels>
		<panel id="form1">
			<crud>
			    <selectonload stackid="form1">SELECT PROGNAME "txtnewprogname",PROGDESC "txtprogramdesc",COUNTRY_ISSUE "countryofissue" FROM PROGRAMDETAILS</selectonload>
				<savefieldids>txtnewprogname,txtprogramdesc,countryofissue</savefieldids>
				<sqlselect>select USER_ID userid, PASSWD password from USER_MASTER</sqlselect>
				<sqlinsert>insert into PROGRAMDETAILS (PROGNAME,PROGDESC,COUNTRY_ISSUE)values(:txtnewprogname,:txtprogramdesc,:countryofissue)</sqlinsert>
				<sqldelete>delete from PROGRAMDETAILS where PROGNAME=:form1.txtnewprogname AND COUNTRY_ISSUE=:form1.countryofissue</sqldelete>
				<sqlselectcount>derive</sqlselectcount>
				<sqlupdate>update PROGRAMDETAILS set PROGDESC=:txtprogramdesc,COUNTRY_ISSUE=:countryofissue
				 where PROGNAME=:form1.txtnewprogname  
				</sqlupdate>
			</crud>
			<fields>
				<field>
					<div id="alertmessage" forid="alertmessage">
						<xpath position="prepend">/html/body</xpath>
						<rule></rule>
						<query></query>
					</div>
				</field>
			</fields>
			<fields>
				<field>
					<input type="text" class="userid" forid="txtnewprogname" value="dfgdfg" name="txtnewprogname" id="txtnewprogname" column="PROGNAME" dbdatatype="STRING" primarykey="YES" dbcolsize="" mask="" mandatory=""
						hidden="" label="">
						<rule></rule>
					</input>
				</field>
				<field>
					<input type="text" class="userid" forid="txtprogramdesc" value="dfgdfg" name="txtprogramdesc" id="txtprogramdesc" column="PROGDESC" dbdatatype="STRING" dbcolsize="" mask="" mandatory=""
						hidden="" label="">
						<rule></rule>
					</input>
				</field>
				<field>
					<select type="text" class="userid" forid="countryofissue" value="" name="countryofissue" id="countryofissue" column="COUNTRY_ISSUE" dbdatatype="STRING"  dbcolsize="" mask="" mandatory=""
						hidden="" label="">
						<text>
						<![CDATA[<select name="countryofissue" maxlength="50" class="field" id="countryofissue">
				        <option value="" selected="selected">Select</option>
				        <option value="INDIA">India</option>
				        <option value="SINGAPORE">Singapore</option>
				        <option value="AUSTRALIA">Australia</option>
				        <option value="PHILIPPINES">Philippines</option>
				        <option value="UAE">UAE</option>
				        </select>]]> 
				        </text>
						<rule></rule>
						<query></query>
					</select>
				</field>
			</fields>
			<button forid="loginbutton" onclick=""></button>
			<button forid="reset" onclick=""></button>
			 
			property <![CDATA[<@s.property value="selectmonthl"/> ]]>

		</panel>
		<panel id="userpanellist">
			<sqlselectall></sqlselectall>
			<sqlwhere></sqlwhere>
			<fields>
				<field>
					<customfield forid="displayuserlist">
					</customfield>
				</field>
			</fields>
		</panel>
	</panels>
</root>