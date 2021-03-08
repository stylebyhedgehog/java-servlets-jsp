<%--
  Created by IntelliJ IDEA.
  User: VANSHIELD
  Date: 28.02.2021
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="add_film" method="Post" enctype="multipart/form-data">
   <input name="title" type="text"/>
   <input name="description" type="text"/>
    <input name="file" type="file" />
    <input type="submit" name="submitAdd" value="OK">
     </form>
</body>
</html>
