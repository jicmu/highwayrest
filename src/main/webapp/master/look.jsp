<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Look</title>
</head>
<body>
    <c:forEach var="i" items="${list}" >
        <img style="width: 300px; height: 300px" src="${i}" ><br/>
    </c:forEach>
</body>
</html>
