<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var passwordResult = false;
        let msg = ""

        const edit = () => {
            if (!passwordResult) {
                msg = "비밀번호를 형식에 맞게 작성해주세요."
                const $form = document.querySelector("form");
                $form.addEventListener("submit", (event) => {
                    event.preventDefault();
                    console.log(event.target);
                });
            } else {
                document.getElementById("form").submit();
            }
            const msgDiv = document.getElementById("msgDiv");
            msgDiv.style.display = "block";
            document.getElementById("msg").innerHTML = '<i class="fas fa-exclamation-triangle mx-1"></i>' + msg
        };

        const setMessage = (elementId, message, color) => {
            const element = document.getElementById(elementId);
            element.style.color = color;
            element.innerHTML = message;
        };

        const passwordCheck = () => {
            const password = f.password.value;
            const password2 = f.password2.value;

            if (!new RegExp("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$").test(password)) {
                setMessage("passwordmsg", "영어, 숫자, 특수문자를 포함하여 8자 이상, 15자 이하로 입력해주세요.", "red");
            } else if (password === password2) {
                setMessage("passwordmsg", "비밀번호와 비밀번호 확인이 일치합니다.", "green");
                passwordResult = true;
            } else {
                setMessage("passwordmsg", "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "red");
            }
        };
    </script>
</head>
<body>
<h3>비밀번호 변경</h3>
<div class="row justify-content-center">
    <div class="col-lg-6">
        <form action="${pageContext.request.contextPath}/member/reset?id=${id}" method="post" name="f" id="form">
            <div class="mb-3" id="msgDiv" style="display: none;">
                <div class="alert alert-danger" role="alert" id="msg"></div>
            </div>
            <div class="mb-3 text-start">
                <label for="password" class="form-label ms-2" style="text-align:left;">비밀번호</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" onkeyup="passwordCheck();">
            </div>
            <div class="mb-3 text-start">
                <label for="password2" class="form-label ms-2" style="text-align:left;">비밀번호 확인</label>
                <input type="password" id="password2" name="password2" class="form-control" placeholder="Password" onkeyup="passwordCheck();">
            </div>
            <div id="passwordmsg"></div>
            <button type="submit" class="btn btn-secondary btn-lg w-100 my-3" id="editbtn" onclick="edit()">변경</button>
        </form>
    </div>
</div>
</body>
</html>