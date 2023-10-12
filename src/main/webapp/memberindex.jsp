<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        const a = () => {
            if (confirm("정말로 탈퇴하시겠습니까?") == true) {
                document.forms[0].submit(); // 첫번째 폼에 접근하여 submit
            }else{
                return; // 취소
            }
        }
    </script>
</head>
<body>
<h3>휴게소 음식 예약 서비스</h3>
<c:if test="${sessionScope.loginId == null }">
    <a href="${pageContext.request.contextPath }/member/login">로그인</a>
    <a href="${pageContext.request.contextPath }/member/join">회원가입</a>
</c:if>
<c:if test="${sessionScope.loginId != null }">
    <div>${sessionScope.nickname}님 환영합니다.</div>
    <a href="${pageContext.request.contextPath }/member/edit?id=${sessionScope.loginId}">내정보</a>
    <a href="${pageContext.request.contextPath }/member/out">로그아웃</a></td>
    <form action="${pageContext.request.contextPath }/member/out?id=${sessionScope.loginId}" method="post">
        <input type="button" name="b1" value="회원탈퇴" onclick="a()"><br/>
    </form>
</c:if>
</body>
</html>
