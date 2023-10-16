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
            document.getElementById("idmsg").style.color = "red";
            let txt = obj.msg;
            if (obj.flag) {
                document.getElementById("idmsg").style.color = "green";
                txt = obj.msg;
            }
            document.getElementById("idmsg").innerHTML = txt;
        };
        const idcheck = () => {
            let id = f.id.value;
            req.open("get", "${pageContext.request.contextPath}/member/idcheck?id=" + id);
            req.send();
        };

        const passwordcheck = function() {
            console.log(f.password.value)
            if (!new RegExp("^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$").test(f.password.value)) {
                document.getElementById("passwordmsg").style.color = "red";
                document.getElementById("passwordmsg").innerHTML = "영어, 숫자, 특수문자를 포함하여 8자 이상, 15자 이하로 입력해주세요.";
            } else if (f.password.value === f.password2.value) {
                document.getElementById("passwordmsg").style.color = "green";
                document.getElementById("passwordmsg").innerHTML = "비밀번호와 비밀번호 확인이 일치합니다.";
            } else {
                document.getElementById("passwordmsg").style.color = "red";
                document.getElementById("passwordmsg").innerHTML = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
            }
        }

        const namecheck = function() {
            var namecheck = false;
            if(f.name.value.trim().length == 0){
                namemsg.style.color = "red";
                namemsg.innerHTML = "이름을 입력해주세요.";
                return;
            } else if (new RegExp("^[a-zA-Z]+$").test(f.name.value) || new RegExp("^[가-힣]+$").test(f.name.value)) {
                document.getElementById("namemsg").innerHTML = "";
                namecheck = true;
            } else {
                document.getElementById("namemsg").style.color = "red";
                document.getElementById("namemsg").innerHTML = "영어만 또는 한글만 입력할 수 있습니다.";
            }
        }

        window.addEventListener('load', function(event){

            var email = document.getElementById("email");
            var emailmsg = document.getElementById("emailmsg");

            var nickname = document.getElementById("nickname");
            var nicknamemsg = document.getElementById("nicknamemsg");

            // 중복 검사 통과 여부
            var emailcheck = false;
            var nicknamecheck = false;
            var telcheck = false;

            email.addEventListener("focusout", function(event){
                if(email.value.trim().length == 0){
                    emailmsg.style.color = "red";
                    emailmsg.innerHTML = "이메일을 입력해주세요.";
                    return;
                }

                var request = new XMLHttpRequest();
                request.open("get","${pageContext.request.contextPath}/member/emailcheck?email=" + f.email.value, true);
                request.send();
                request.addEventListener('load', function(event) {
                    var data = JSON.parse(event.target.responseText);
                    if (!new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$").test(f.email.value)) {
                        emailmsg.style.color = "red";
                        emailmsg.innerHTML = "올바른 이메일 형식으로 입력해주세요.";
                    } else if (data.flag == true) {
                        emailmsg.style.color = "green";
                        emailmsg.innerHTML = "사용 가능한 이메일입니다.";
                        emailcheck = true;
                    } else {
                        emailmsg.style.color = "red";
                        emailmsg.innerHTML = "사용 불가능한 이메일입니다.";
                    }
                })
            });

            nickname.addEventListener(
                "focusout", function(event){
                    if(nickname.value.trim().length < 1){
                        nicknamemsg.style.color = "red";
                        nicknamemsg.innerHTML = "닉네임을 입력하세요";
                        nicknamecheck = false;
                        return;
                    }

                    var request = new XMLHttpRequest();
                    request.open("get", "${pageContext.request.contextPath}/member/nicknamecheck?nickname=" + f.nickname.value, true);
                    request.send();
                    // 데이터를 가져왔을 때 호출될 메소드를 설정
                    request.addEventListener(
                        'load', function(event){
                            var data = JSON.parse(event.target.responseText);
                            if (!new RegExp("^[a-zA-Z0-9]+$").test(f.nickname.value)) {
                                nicknamemsg.style.color = "red";
                                nicknamemsg.innerHTML = "특수문자는 입력할 수 없습니다..";
                            } else if (data.result == true) {
                                nicknamemsg.style.color = "green";
                                nicknamemsg.innerHTML = "사용 가능한 닉네임입니다.";
                                nicknamecheck = true;
                            } else {
                                nicknamemsg.style.color = "red";
                                nicknamemsg.innerHTML = "중복된 닉네임입니다.";
                            }
                        });
                });

            tel.addEventListener("focusout", function(event){
                if(tel.value.trim().length == 0){
                    telmsg.style.color = "red";
                    telmsg.innerHTML = "번호를 입력해주세요.";
                    return;
                }

                var request = new XMLHttpRequest();
                request.open("get","${pageContext.request.contextPath}/member/telcheck?tel=" + f.tel.value, true);
                request.send();
                request.addEventListener('load', function(event) {
                    var data = JSON.parse(event.target.responseText);
                    if (!new RegExp("^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$").test(f.tel.value)) {
                        telmsg.style.color = "red";
                        telmsg.innerHTML = "올바른 번호를 입력해주세요.";
                    } else if (data.flag == true) {
                        telmsg.style.color = "green";
                        telmsg.innerHTML = "사용 가능한 번호입니다.";
                        telcheck = true;
                    } else {
                        telmsg.style.color = "red";
                        telmsg.innerHTML = "사용 불가능한 번호입니다.";
                    }
                })
            });
        });
    </script>
</head>
<body>
<h3>회원가입</h3>
<div class="row justify-content-center">
    <div class="col-lg-6">
        <form action="${pageContext.request.contextPath}/member/join" method="post" name="f" id="joinform">
            <div class="mb-3">
                <c:if test="${not empty msg}">
                    <div class="alert alert-danger" role="alert">
                        <i class="fas fa-exclamation-triangle mx-1"></i>${msg}
                    </div>
                </c:if>
            </div>
            <div class="mb-3 text-start">
                <label for="id" class="form-label ms-2">아이디</label>
                <div class="d-flex gap-2">
                    <input type="text" id="id" name="id" class="form-control" placeholder="Username" autofocus>
                    <input type="button" value="중복확인" onclick="idcheck()">
                </div>
            </div>
            <div id="idmsg"></div>
            <div class="mb-3 text-start">
                <label for="password" class="form-label ms-2" style="text-align:left;">비밀번호</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" onkeyup="passwordcheck();">
            </div>
            <div class="mb-3 text-start">
                <label for="password2" class="form-label ms-2" style="text-align:left;">비밀번호 확인</label>
                <input type="password" id="password2" name="password2" class="form-control" placeholder="Password" onkeyup="passwordcheck();">
            </div>
            <div id="passwordmsg"></div>
            <div class="mb-3 text-start">
                <label for="name" class="form-label ms-2" style="text-align:left;">이름</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Name" onfocusout="namecheck()">
            </div>
            <div id="namemsg"></div>
            <div class="mb-3 text-start">
                <label for="nickname" class="form-label ms-2" style="text-align:left;">닉네임</label>
                <input type="text" id="nickname" name="nickname" class="form-control" placeholder="Nickname">
            </div>
            <div id="nicknamemsg"></div>
            <div class="mb-3 text-start">
                <label for="email" class="form-label ms-2" style="text-align:left;">이메일</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="email@example.com">
            </div>
            <div id="emailmsg"></div>
            <div class="mb-3 text-start">
                <label for="tel" class="form-label ms-2" style="text-align:left;">전화번호</label>
                <input type="text" id="tel" name="tel" class="form-control" placeholder="01012345678">
            </div>
            <div id="telmsg"></div>
            <button type="submit" class="btn btn-secondary btn-lg w-100 my-3" id="joinbtn">
                <i class="fa-solid fa-users-line"></i>
                <span>회원가입</span>
            </button>
        </form>
    </div>
</div>
</body>
</html>