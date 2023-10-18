<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript">

    // 카카오 회원은 비밀번호 변경을 못하도록
/*    document.addEventListener("DOMContentLoaded", function() {
        var passwordInput = document.getElementById("password");
        var password2Input = document.getElementById("password2");
        var loginId = "${sessionScope.get('loginId')}";
        if (loginId.startsWith("kakao_")) {
            passwordInput.setAttribute("disabled", true);
            password2Input.setAttribute("disabled", true);
            passwordResult = true;
        }
    });*/

    const edit = () => {
        if (!(passwordResult)) {
            msg = "비밀번호를 입력해주세요."
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
    };

    const setMessage = (elementId, message, color) => {
        const element = document.getElementById(elementId);
        element.style.color = color;
        element.innerHTML = message;
    };

    function passwordClear() {
        const password = document.getElementById("password");
        password.value = "";
    }
    function password2Clear() {

        const password2 = document.getElementById("password2");
        password2.value = "";
    }

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
                telmsg.innerHTML = "입력하신 번호는 아이디, 비밀번호 찾기에 사용됩니다.";
                telResult = true;
            } else {
                telmsg.style.color = "red";
                telmsg.innerHTML = "사용 불가능한 번호입니다.";
            }
        });
    };
    const out = () => {
        if (confirm("정말로 탈퇴하시겠습니까?") == true) {
            document.forms[1].submit(); // 두 번째 폼에 접근
            alert("탈퇴가 완료되었습니다.")
        }else{
            return; // 취소
        }
    }
</script>
</head>
<body>
<h3 class="mt-3">회원정보</h3>
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <form action="${pageContext.request.contextPath}/member/edit" method="post" name="f" id="form">
                <div class="mb-3" id="msgDiv" style="display: none;">
                    <div class="alert alert-danger" role="alert" id="msg"></div>
                </div>
                <div class="mb-3 text-start">
                    <label for="id" class="form-label ms-2">아이디</label>
                    <div class="d-flex gap-2">
                        <input type="text" id="id" name="id" class="form-control" value="${m.id}" readonly>
                    </div>
                </div>
                <div class="mb-3 text-start">
                    <label for="password" class="form-label ms-2" style="text-align:left;">비밀번호</label>
                    <input type="password" id="password" name="password" class="form-control" onfocus="passwordClear()" onkeyup="passwordCheck()" value="${m.password}">
                </div>
                <div class="mb-3 text-start">
                    <label for="password2" class="form-label ms-2" style="text-align:left;">비밀번호 확인</label>
                    <input type="password" id="password2" name="password2" class="form-control" onfocus="password2Clear()" onkeyup="passwordCheck()" value="${m.password}" >
                </div>
                <div id="passwordmsg"></div>
                <div class="mb-3 text-start">
                    <label for="name" class="form-label ms-2" style="text-align:left;">이름</label>
                    <input type="text" id="name" name="name" class="form-control" value="${m.name}" readonly>
                </div>
                <div class="mb-3 text-start">
                    <label for="nickname" class="form-label ms-2" style="text-align:left;">닉네임</label>
                    <input type="text" id="nickname" name="nickname" class="form-control" onfocusout="nicknameCheck()" value="${m.nickname}">
                </div>
                <div id="nicknamemsg"></div>
                <div class="mb-3 text-start">
                    <label for="email" class="form-label ms-2" style="text-align:left;">이메일</label>
                    <input type="email" id="email" name="email" class="form-control" onfocusout="emailCheck()" value="${m.email}" readonly>
                </div>
                <div class="mb-3 text-start">
                    <label for="tel" class="form-label ms-2" style="text-align:left;">전화번호</label>
                    <input type="text" id="tel" name="tel" class="form-control" onfocusout="telCheck()" value="${m.tel}" >
                </div>
                <div id="telmsg"></div>
                <button type="submit" class="btn btn-secondary btn-lg w-100 my-3" id="joinbtn" onclick="edit()">수정</button>
            </form>
            <form action="${pageContext.request.contextPath}/member/out?id=${sessionScope.loginId}" method="post">
                <input type="button" name="b1" value="회원탈퇴" onclick="out()"><br/>
            </form>
        </div>
    </div>
</body>
</html>
