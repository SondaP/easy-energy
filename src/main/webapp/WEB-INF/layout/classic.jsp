
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>

<head>
    <title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>

<tiles:insertAttribute name="body" />
<br><br>
<center>
<tiles:insertAttribute name="footer" />
</center>

</body>
</html>
