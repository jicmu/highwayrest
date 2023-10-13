<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        let req = new XMLHttpRequest();
        req.onload = () => {
            let obj = JSON.parse(req.responseText);
            let txt = "중복된 아이디입니다.";
            if (obj.flag) {
                txt = "사용 가능한 아이디입니다.";
            }
            document.getElementById("res").innerHTML = txt;
        };
        const a = () => {
            let id = f.id.value;
            req.open("get", "${pageContext.request.contextPath}/member/idcheck?id=" + id);
            req.send();
        };

        const check = function() {
            if (document.getElementById("password").value === document.getElementById('password2').value) {
                document.getElementById("res2").style.color = "green";
                document.getElementById("res2").innerHTML = "비밀번호와 비밀번호 확인이 일치합니다.";
            } else {
                document.getElementById("res2").style.color = "red";
                document.getElementById("res2").innerHTML = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
            }
        }
    </script>
</head>
<body>
<h3>회원가입</h3>
<div class="row justify-content-center">
    <div class="col-lg-6">
        <form action="${pageContext.request.contextPath}/member/join" method="post" name="f">
            <div class="mb-3">
                <c:if test="${not empty msg}">
                    <div class="alert alert-danger" role="alert">
                        <i class="fas fa-exclamation-triangle mx-1"></i>${msg}
                    </div>
                </c:if>
            </div>
            <div class="mb-3 text-start">
                <label for="id" class="form-label ms-2">아이디</label>
                <div class="d-flex">
                    <input type="text" id="id" name="id" class="form-control" placeholder="Username" autofocus>
                    <input type="button" value="중복확인" onclick="a()">
                </div>
                <div id="res"></div>
            </div>
            <div class="mb-3 text-start">
                <label for="password" class="form-label ms-2" style="text-align:left;">비밀번호</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" onkeyup="check();">
            </div>
            <div class="mb-3 text-start">
                <label for="password2" class="form-label ms-2" style="text-align:left;">비밀번호 확인</label>
                <input type="password" id="password2" name="password2" class="form-control" placeholder="Password" onkeyup="check();">
            </div>
            <div id="res2"></div>
            <div class="mb-3 text-start">
                <label for="name" class="form-label ms-2" style="text-align:left;">이름</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Name">
            </div>
            <div class="mb-3 text-start">
                <label for="nickname" class="form-label ms-2" style="text-align:left;">닉네임</label>
                <input type="text" id="nickname" name="nickname" class="form-control" placeholder="Nickname">
            </div>
            <div class="mb-3 text-start">
                <label for="email" class="form-label ms-2" style="text-align:left;">이메일</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="email@example.com">
            </div>
            <div class="mb-3 text-start">
                <label for="tel" class="form-label ms-2" style="text-align:left;">전화번호</label>
                <input type="text" id="tel" name="tel" class="form-control" placeholder="01012345678">
            </div>
            <button type="submit" class="btn btn-secondary btn-lg w-100 mb-3">
                <i class="fa-solid fa-users-line"></i>
                <span>회원가입</span>
            </button>
    </body>
</html>