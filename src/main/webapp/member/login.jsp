<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>로그인</h3>
<div>
    <c:if test="${not empty msg}">
        <div style="color: red">${msg}</div>
    </c:if>
</div>
<form action="${pageContext.request.contextPath }/member/login" method="post">
id:<input type="text" name="id"><br/>
password:<input type="password" name="password"><br/>
<input type="submit" value="로그인">
</form>
</body>
</html>