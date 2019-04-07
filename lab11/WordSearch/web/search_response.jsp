<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Search Results</title>
  </head>
  <body>
  Answer = ${answer}
  <%
      Integer count = (Integer) request.getAttribute("count");
      if (count != null) {
  %>
  <br>
  Count = <%=count%>
  <%
      }
  %>
  </body>
</html>
