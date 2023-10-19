<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/common/css/payment.css' />" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <style>
        .child {
            position: absolute;
            top: 50%;
            left: 50%;
            /*요소의 높이/너비의 반(50%)만큼 위/왼쪽으로 이동*/
            transform: translate(-50%, -50%);
        }
        [type="radio"] {
            vertical-align: middle;
            appearance: none;
            border: max(2px, 0.1em) solid gray;
            border-radius: 50%;
            width: 1.25em;
            height: 1.25em;
            transition: border 0.5s ease-in-out;
        }
    </style>
</head>
<body>

    <%@ include file="modal/highwayAdd.jsp" %>
    <%@ include file="modal/restfood.jsp" %>
    <%@ include file="modal/restStatus.jsp" %>
    <%@ include file="modal/foodMenu.jsp" %>
    <%@ include file="modal/orderList.jsp" %>

    <div class="container child" style="width: auto; height: auto; border: 3px solid black; padding: 0">
        <div style="width: 600px; height: 600px;">
            <div style="width: 600px; height: 300px;">
                <div style="position: relative; width: 300px; height: 300px; background-color: aquamarine; float: left; border-right: 3px solid black; border-bottom: 3px solid black">
                    <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: end;">
                        <img src="<c:url value="/common/logo/call.png" />" alt="Bootstrap" width="100" height="100" /><br/>
                        <span style="font-size: 30px;">: 고속주문</span>
                    </div>
                </div>

                <div style="position: relative; width: 300px; height: 300px; background-color: white; float: right; border-bottom: 3px solid black">
                    <div style="margin-left: 30px; width: 300px; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: start">
                        <span style="font-size: 30px;">휴게소 관리</span>
                        <ul>
                            <li>
                                <a data-bs-toggle="modal" data-bs-target="#exampleModal1">
                                    도로 추가
                                </a>
                            </li>
                            <li>
                                <a data-bs-toggle="modal" data-bs-target="#exampleModal2">
                                    음식 추가
                                </a>
                            </li>
                            <li>
                                <a data-bs-toggle="modal" data-bs-target="#exampleModal3">
                                    휴게소 확인
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div style="width: 600px; height: 300px; background-color: black">
                <div style="position: relative; width: 300px; height: 300px; background-color: yellow; float: left; border-right: 3px solid black">
                    <div style="position: relative; width: 300px; height: 300px; background-color: white; float: left; border-right: 3px solid black">
                        <div style="margin-left: 30px; width: 300px; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: start">
                            <span style="font-size: 30px;">가게 관리</span>
                            <ul>
                                <li>
                                    <a data-bs-toggle="modal" data-bs-target="#exampleModal4">
                                        음식 관리
                                    </a>
                                </li>
                                <li>
                                    <a data-bs-toggle="modal" data-bs-target="#exampleModal5">
                                        주문 관리
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div style="position: relative; width: 300px; height: 300px; background-color: beige; float: right">
                    <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: start">
                        <span style="font-size: 30px;">준비중</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" />
    <script>
        const myModal = document.getElementById('myModal')
        const myInput = document.getElementById('myInput')

        myModal.addEventListener('shown.bs.modal', () => {
            myInput.focus()
        })
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            const toastTrigger = document.getElementById('liveToastBtn')
            const toastLiveExample = document.getElementById('liveToast')

            if (toastTrigger) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
                toastTrigger.addEventListener('click', () => {
                    // toastBootstrap.show()
                    alert("확인!");
                })
            }

            let status = "${status}";
            if(status == "1") {
                alert("가입 성공!");
            } else if(status == "0") {
                alert("가입 실패!");
            }
        })

        const changeToggle = (id) => {
            let toggle = document.getElementById(id + "_label");

            if(toggle.innerHTML == "폐업") {
                toggle.innerHTML = "영업";
                console.log("영업 id: " + id);
                $.ajax({
                    type: "post",
                    url: "http://49.50.163.148/highwayrest/restmaster/status",
                    data: {"id" : id, "status" : 1},
                    datatype: "text",
                    success: function (result) {
                        console.log("영업 전환 성공!" + result)
                    }, error: function () {
                        console.log("실패!");
                    }
                })
            } else {
                toggle.innerHTML = "폐업";
                console.log("폐업 id: " + id);
                $.ajax({
                    type: "post",
                    url: "http://49.50.163.148/highwayrest/restmaster/status",
                    data: {"id" : id, "status" : 0},
                    datatype: "text",
                    success: function () {
                        console.log("영업 전환 성공!")
                    }, error: function () {
                        console.log("실패!");
                    }
                })
            }
        }
    </script>
    <script defer src="<c:url value="/common/js/highway.js" /> " crossorigin="anonymous"></script>
    <script defer src="<c:url value="/common/js/highwayfood.js" />" crossorigin="anonymous"></script>
    <script defer src="<c:url value="/common/js/restfoodmenu.js" />" crossorigin="anonymous"></script>
    <script defer src="<c:url value="/common/js/orderlist.js" />" crossorigin="anonymous"></script>
    <script defer src="<c:url value="/common/js/storeorderlist.js" />" crossorigin="anonymous"></script>
</body>
</html>
