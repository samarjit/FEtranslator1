<%@taglib uri="/struts-tags" prefix="s" %>
<h2>An unexpected error has occurred</h2>
  <p>
    Please report this error to your system administrator
    or appropriate technical support personnel.
    Thank you for your cooperation.
  </p>
  <hr/>
  <h3>Error Message</h3>
    <s:actionerror/>
    <s:actionmessage/>
    <p>
      <s:property value="%{exception.message}"/>
    </p>
    <hr/>
    <h3>Technical Details</h3>
    <p>
      <s:property value="%{exceptionStack}"/>
    </p>