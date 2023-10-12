<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>휴게소 음식 예약 서비스</h3>
${msg }
<c:if test="${sessionScope.loginId == null }">
    <a href="${pageContext.request.contextPath }/member/login">로그인</a>
    <a href="${pageContext.request.contextPath }/member/join">회원가입</a>
</c:if>
<c:if test="${sessionScope.loginId != null }">
    <a href="${pageContext.request.contextPath }/member/edit?id=${sessionScope.loginId}">내정보</a>
    <a href="${pageContext.request.contextPath }/member/out">로그아웃</a></td>
    <form action="${pageContext.request.contextPath }/member/out?id=${sessionScope.loginId}" method="post">
        <button>회원탈퇴</button>
    </form>
</c:if>
</body>
</html>
