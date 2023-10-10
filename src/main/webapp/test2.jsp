<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 2023-10-06
  Time: 오전 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>test2 jsp</h1>
    <form action="${pageContext.request.contextPath}/justlikeryu.jsp" method="post">
        <input type="submit" value="버튼">
    </form>
</body>
</html>
