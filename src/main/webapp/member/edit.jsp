<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        var passwordInput = document.getElementById("password");
        if ("<%= session.getId().startsWith("kakao_") %>") {
            passwordInput.readOnly = true;
        }
    });
        <%--<script type="text/javascript">--%>
        <%--    const a = () => {--%>
        <%--        if (confirm("정말로 탈퇴하시겠습니까?") == true) {--%>
        <%--            document.forms[0].submit(); // 첫번째 폼에 접근하여 submit--%>
        <%--        }else{--%>
        <%--            return; // 취소--%>
        <%--        }--%>
        <%--    }--%>
        <%--</script>--%>
</script>
</head>
<body>
<h3>회원정보</h3>
<form action="${pageContext.request.contextPath}/member/edit" method="post">
id:<input type="text" name="id" value="${m.id}" readonly><br/>
password:<input type="password" name="password" value="${m.password}" id="password"><br/>
name:<input type="text" name="name" value="${m.name}" readonly><br/>
nickname:<input type="text" name="nickname" value="${m.nickname}"><br/>
email:<input type="text" name="email" value="${m.email}" readonly><br/>
tel:<input type="text" name="tel" value="${m.tel}"><br/>
<input type="submit" value="수정">
<a href="${pageContext.request.contextPath }/controller/yeonann/memberindex.jsp">취소</a>
<%--<form action="${pageContext.request.contextPath }/member/out?id=${sessionScope.loginId}" method="post">--%>
<%--    <input type="button" name="b1" value="회원탈퇴" onclick="a()"><br/>--%>
<%--</form>--%>
</form>
</body>
</html>