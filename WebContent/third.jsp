<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<book><%String dynamic = "<b>some dynamic content</b>"; %>
   <isbn>123</isbn>
   <title>Web Servers for Fun and Profit <%=dynamic %></title>
   <quantity>10</quantity>
   <price>$17.95</price>
   <body></body>
</book>