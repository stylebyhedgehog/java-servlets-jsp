<%--
  Created by IntelliJ IDEA.
  User: Shamil
  Date: 18.03.2020
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Picture my form to Servlet</title>
</head>
<body>
<form method="post" action="ServletUploadFile" enctype="multipart/form-data">
    Choose a file: <input type="file" name="multiPartServlet" />
    <input type="submit" value="Upload" />
</form>
</body>
</html>
