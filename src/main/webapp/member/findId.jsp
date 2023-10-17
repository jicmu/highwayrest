<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script>
        var randomNumber = "";
        var checkResult = false;

        function handleTelChk() {
            var tel = $("#tel").val();
            $.ajax({
                type: "GET",
                url: "sendSMS?tel=" + tel,
                cache: false,
                success: function (data) {
                    console.log(data);
                    if (tel == null) {
                        $(".successChk").text("번호를 입력해주세요.");
                        $(".successChk").css("color", "red");
                        $("#tel").attr("autofocus", true);
                    } else if (!new RegExp("^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$").test(tel)) {
                        $(".successChk").text("올바른 번호를 입력해주세요.");
                        $(".successChk").css("color", "red");
                        $("#tel").attr("autofocus", true);
                    } else {
                        alert("인증번호 발송이 완료되었습니다.\n인증번호를 확인하여 입력해주세요.");
                        $("#tel2").attr("disabled", false);
                        $("#telChk").prop("disabled", true);
                        $("#telChk2").css("display", "inline-block");
                        $("#telChk2").attr("autofocus", true);
                        $(".successChk").text("인증번호 입력 후 본인인증을 눌러주십시오.");
                        $(".successChk").css("color", "midnightblue");
                        $("#tel").attr("readonly", true);
                        randomNumber = data;
                    }
                }
            });
        }

        // 인증번호 대조
        function checkNumber() {
            if ($("#tel2").val() == randomNumber) {
                $(".successChk").text("인증이 완료되었습니다.");
                $(".successChk").css("color", "green");
                $("#chkmsg").val("true");
                $("#tel2").attr("readonly", true);
                $("#nextbtn").prop("disabled", false);
            } else {
                $(".successChk").text("인증번호가 일치하지 않습니다. 다시 확인해 주세요.");
                $(".successChk").css("color", "red");
                $(this).attr("autofocus", true);
            }
        }
    </script>
</head>
<body>
<h3>아이디 찾기</h3>
<div class="row justify-content-center">
    <div class="col-lg-6">
        <form action="${pageContext.request.contextPath}/member/findId" method="post" name="f" id="form">
            <div class="mb-3">
                <c:if test="${not empty msg}">
                    <div class="alert alert-danger" role="alert">
                        <i class="fas fa-exclamation-triangle mx-1"></i>${msg}
                    </div>
                </c:if>
            </div>
            <div class="mb-3 text-start">
                <div class="d-flex gap-2">
                    <input type="text" id="tel" name="tel" placeholder="가입 시 등록한 전화번호" autofocus>
                    <input type="button" id="telChk" class="check" value="인증하기" onclick="handleTelChk()">
                </div>
            </div>
            <div class="mb-3 text-start">
                <div class="d-flex gap-2">
                    <input type="text" id="tel2" name="tel2" placeholder="인증번호 입력" disabled required/>
                    <input type="button" id="telChk2" class="check" value="본인인증" onclick="checkNumber()">
                </div>
            </div>
            <div>
                <span class="successChk" style="color:midnightblue">번호 입력 후 인증하기를 눌러주세요.</span>
                <input type="hidden" id="chkmsg"/>
            </div>
            <button type="submit" class="btn btn-secondary btn-lg w-100 my-3" id="nextbtn" disabled>다음</button>
        </form>
    </div>
</div>
</body>
</html>