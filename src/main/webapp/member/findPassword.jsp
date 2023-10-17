<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
var code2 = "";

function handleTelChk() {
    var tel = $("#tel").val();
    console.log(tel);
    $.ajax({
        type: "GET",
        url: "sendSMS?tel=" + tel,
        cache: false,
        success: function (data) {
            if (data == "error") {
                alert("휴대폰 번호가 올바르지 않습니다.")
                $(".successtelChk").text("유효한 번호를 입력해주세요.");
                $(".successtelChk").css("color", "red");
                $("#tel").attr("autofocus", true);
            } else {
                $("#tel2").attr("disabled", false);
                $("#telChk2").css("display", "inline-block");
                $(".successtelChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
                $(".successtelChk").css("color", "green");
                $("#tel").attr("readonly", true);
                code2 = data;
            }
            alert("인증번호 발송이 완료되었습니다.\n인증번호를 확인하여 입력해주세요.");
        }
    });
}

    // 인증번호 대조
function checkNumber() {
    if ($("#tel2").val() == code2) {
        $(".successtelChk").text("인증이 완료되었습니다.");
        $(".successtelChk").css("color", "green");
        $("#telDoubleChk").val("true");
        $("#tel2").attr("disabled", true);
    } else {
        $(".successtelChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
        $(".successtelChk").css("color", "red");
        $("#telDoubleChk").val("false");
        $(this).attr("autofocus", true);
    }
}
</script>
<body>
<h3>번호 인증</h3>
<div>
    <tr class="mobileNo">
        <th>
            <label for="tel">휴대폰 번호</label>
        </th>
        <td>
            <p>
                <input id="tel" type="text" name="tel" title="번호 입력" required/>
                <button id="telChk" class="doubleChk" onclick="handleTelChk()">인증하기</button><br/>
                <input id="tel2" type="text" name="tel2" title="인증번호 입력" disabled required/>
                <button id="telChk2" class="doubleChk" onclick="checkNumber()">본인인증</button>
                <span class="successtelChk">휴대폰 번호 입력 후 인증번호 보내기를 해주세요.</span>
                <input type="hidden" id="telDoubleChk"/>
            </p>
        </td>
    </tr>
</div>
</body>
</html>
