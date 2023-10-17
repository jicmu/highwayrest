<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        let req = new XMLHttpRequest();
        var idResult = false;
        var passwordResult = false;
        var nameResult = false;
        var nicknameResult = false;
        var emailResult = false;
        var telResult = false;
        let msg = ""

        const join = () => {
            if (!idResult) {
                msg = "ID 중복확인을 해주세요."
                const form = document.querySelector("form");
                form.addEventListener("submit", (event) => {
                    event.preventDefault();
                    console.log(event.target);
                });
            } else if (!(passwordResult && nameResult && nicknameResult && emailResult && telResult)) {
                msg = "모든 항목을 작성해주세요."
                const $form = document.querySelector("form");
                $form.addEventListener("submit", (event) => {
                    event.preventDefault();
                    console.log(event.target);
                });
            } else {
                document.getElementById("f").submit();
            }
            const msgDiv = document.getElementById("msgDiv");
            msgDiv.style.display = "block";
            document.getElementById("msg").innerHTML = '<i class="fas fa-exclamation-triangle mx-1"></i>' + msg
        }

        const setMessage = (elementId, message, color) => {
            const element = document.getElementById(elementId);
            element.style.color = color;
            element.innerHTML = message;
        };

        const idCheck = () => {
            let id = f.id.value;
            req.open("get", "${pageContext.request.contextPath}/member/idcheck?id=" + id);
            req.send();
        };

        req.onload = () => {
            let obj = JSON.parse(req.responseText);
            document.getElementById("idmsg").style.color = "red";
            let txt = obj.msg;
            if (obj.flag) {
                document.getElementById("idmsg").style.color = "green";
                txt = obj.msg;
                idResult = true;
            }
            document.getElementById("idmsg").innerHTML = txt;
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

        const nameCheck = () => {
            const name = f.name.value.trim();

            if (name.length === 0) {
                setMessage("namemsg", "이름을 입력해주세요.", "red");
            } else if (new RegExp("^[a-zA-Z]+$").test(name) || new RegExp("^[가-힣]+$").test(name)) {
                setMessage("namemsg", "", "black");
                nameResult = true;
            } else {
                setMessage("namemsg", "영어만 또는 한글만 입력할 수 있습니다.", "red");
            }
        };

        const emailCheck = () => {
            const email = f.email.value.trim();
            const emailmsg = document.getElementById("emailmsg");

            if (email.length === 0) {
                emailmsg.style.color = "red";
                emailmsg.innerHTML = "이메일을 입력해주세요.";
                return;
            }

            const request = new XMLHttpRequest();
            request.open("get", "${pageContext.request.contextPath}/member/emailcheck?email=" + email, true);
            request.send();
            request.addEventListener('load', (event) => {
                const data = JSON.parse(event.target.responseText);
                if (!new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").test(email)) {
                    emailmsg.style.color = "red";
                    emailmsg.innerHTML = "올바른 이메일 형식으로 입력해주세요.";
                } else if (data.flag == true) {
                    emailmsg.style.color = "green";
                    emailmsg.innerHTML = "사용 가능한 이메일입니다.";
                    emailResult = true;
                } else {
                    emailmsg.style.color = "red";
                    emailmsg.innerHTML = "사용 불가능한 이메일입니다.";
                }
            });
        };

        const nicknameCheck = () => {
            const nickname = f.nickname.value.trim();
            const nicknamemsg = document.getElementById("nicknamemsg");

            if (nickname.length < 1) {
                nicknamemsg.style.color = "red";
                nicknamemsg.innerHTML = "닉네임을 입력해주세요.";
                return;
            }

            const request = new XMLHttpRequest();
            request.open("get", "${pageContext.request.contextPath}/member/nicknamecheck?nickname=" + nickname, true);
            request.send();
            request.addEventListener('load', (event) => {
                const data = JSON.parse(event.target.responseText);
                if (!new RegExp("^[가-힣a-zA-Z0-9]*$").test(nickname)) {
                    nicknamemsg.style.color = "red";
                    nicknamemsg.innerHTML = "특수문자는 입력할 수 없습니다.";
                } else if (data.flag == true) {
                    nicknamemsg.style.color = "green";
                    nicknamemsg.innerHTML = "사용 가능한 닉네임입니다.";
                    nicknameResult = true;
                } else {
                    nicknamemsg.style.color = "red";
                    nicknamemsg.innerHTML = "중복된 닉네임입니다.";
                }
            });
        };

        const telCheck = () => {
            const tel = f.tel.value.trim();
            const telmsg = document.getElementById("telmsg");

            if (tel.length === 0) {
                telmsg.style.color = "red";
                telmsg.innerHTML = "번호를 입력해주세요.";
                return;
            }

            const request = new XMLHttpRequest();
            request.open("get", "${pageContext.request.contextPath}/member/telcheck?tel=" + tel, true);
            request.send();
            request.addEventListener('load', (event) => {
                const data = JSON.parse(event.target.responseText);
                if (!new RegExp("^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$").test(tel)) {
                    telmsg.style.color = "red";
                    telmsg.innerHTML = "올바른 번호를 입력해주세요.";
                } else if (data.flag == true) {
                    telmsg.style.color = "green";
                    telmsg.innerHTML = "사용 가능한 번호입니다.";
                    telResult = true;
                } else {
                    telmsg.style.color = "red";
                    telmsg.innerHTML = "사용 불가능한 번호입니다.";
                }
            });
        };
    </script>
</head>
<body>
<h3>회원가입</h3>
<div class="row justify-content-center">
    <div class="col-lg-6">
        <form action="${pageContext.request.contextPath}/member/join" method="post" name="f" id="form">
            <div class="mb-3" id="msgDiv" style="display: none;">
                    <div class="alert alert-danger" role="alert" id="msg"></div>
            </div>
            <div class="mb-3 text-start">
                <label for="id" class="form-label ms-2">아이디</label>
                <div class="d-flex gap-2">
                    <input type="text" id="id" name="id" class="form-control" placeholder="Username" autofocus>
                    <input type="button" value="중복확인" onclick="idCheck()">
                </div>
            </div>
            <div id="idmsg"></div>
            <div class="mb-3 text-start">
                <label for="password" class="form-label ms-2" style="text-align:left;">비밀번호</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" onkeyup="passwordCheck();">
            </div>
            <div class="mb-3 text-start">
                <label for="password2" class="form-label ms-2" style="text-align:left;">비밀번호 확인</label>
                <input type="password" id="password2" name="password2" class="form-control" placeholder="Password" onkeyup="passwordCheck();">
            </div>
            <div id="passwordmsg"></div>
            <div class="mb-3 text-start">
                <label for="name" class="form-label ms-2" style="text-align:left;">이름</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Name" onfocusout="nameCheck()">
            </div>
            <div id="namemsg"></div>
            <div class="mb-3 text-start">
                <label for="nickname" class="form-label ms-2" style="text-align:left;">닉네임</label>
                <input type="text" id="nickname" name="nickname" class="form-control" placeholder="Nickname" onfocusout="nicknameCheck()">
            </div>
            <div id="nicknamemsg"></div>
            <div class="mb-3 text-start">
                <label for="email" class="form-label ms-2" style="text-align:left;">이메일</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="email@example.com" onfocusout="emailCheck()">
            </div>
            <div id="emailmsg"></div>
            <div class="mb-3 text-start">
                <label for="tel" class="form-label ms-2" style="text-align:left;">전화번호</label>
                <input type="text" id="tel" name="tel" class="form-control" placeholder="01012345678" onfocusout="telCheck()">
            </div>
            <div id="telmsg"></div>
            <button type="submit" class="btn btn-secondary btn-lg w-100 my-3" id="joinbtn" onclick="join()">
                <i class="fa-solid fa-users-line"></i>
                <span>회원가입</span>
            </button>
        </form>
    </div>
</div>
</body>
</html>