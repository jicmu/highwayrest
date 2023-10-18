<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginNum" value="${sessionScope.loginNum}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script>
        window.onload = () => {
            document.getElementById("foodNm").onchange = function() {
                var searchWord = document.getElementById("searchWord");
                var foodNm = document.getElementById("foodNm").value;
                searchWord.value = foodNm;
            }

            function eventPreventDefault(event) {
                event.preventDefault();
            }

            document.getElementById("submit").onclick = function() {
                var amounts = document.querySelectorAll("input[name=amount]");
                var sum = 0;
                for(let amount of amounts) {
                    sum += parseInt(amount.value);
                }
                var loginNum = "<c:out value='${loginNum}' />";
                var orderform = document.getElementById("order");
                if(loginNum.trim() === ""){
                    var returnLogin = confirm("로그인 후 이용해주세요 \n로그인 페이지로 이동하시겠습니까?");
                    if(returnLogin){
                        location.href = '${pageContext.request.contextPath }/member/login';
                    }
                    orderform.addEventListener("submit", eventPreventDefault);
                }else if(sum == 0) {
                    alert("주문할 메뉴를 1개 이상 선택해주세요");
                    orderform.addEventListener("submit", eventPreventDefault);
                }else {
                    orderform.removeEventListener("submit", eventPreventDefault);
                    orderform.submit();
                }
            }

            const reset = () => {
                amount = 0;
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="row">
            <ul class="nav justify-content-center nav-pills nav-fill">
              <li class="nav-item">
                <a class="nav-link" href="#">메뉴</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath }/listReview?svarCd=${svarCd }">후기</a>
              </li>
            </ul>
            <hr>
        </div>
        <div class="row">
            <div class="col-3">
                <h2>주문</h2>
            </div>
            <div class="col-9">
                <form class="d-flex" role="search" name="search" id="search" action="${pageContext.request.contextPath }/foodsearch" method="post">
                    <input type="hidden" name="searchType" value="3">
                    <input type="hidden" name="memberNo" value="${sessionScope.loginNum }">
                    <input type="hidden" id="searchWord" name="searchWord" value="">
                    <input class="form-control me-2" type="search" id="foodNm" name="name" placeholder="메뉴명">
                    <input class="btn btn-outline-success" type="submit" value="검색"></button>
                </form>
            </div>
            <hr>
        </div>

        <form action="${pageContext.request.contextPath }/order" method="post" id="order" name="order">
            <input type="hidden" name="restNo" value="${restNo }">
            <div class="row">
                <table class="table table-hover text-center" id="food-table">
                    <thead class="table-light">
                        <tr><th>메뉴</th><th>가격</th><th>수량</th></tr>
                    </thead>
                    <c:forEach var="m" items="${list }">
                    <tr>
                        <td>${m.name }<input type="hidden" id="${m.no }_name" name="foodNo" value="${m.no }"></td>
                        <td>${m.foodCost }<input type="hidden" id="${m.no }_price" name="foodCost" value="${m.foodCost }"></td>
                        <td><input type="number" id="${m.no }_amount" name="amount" value="0" min="0"></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="row mt-2">
                <hr>
                <ul class="nav justify-content-center nav-pills nav-fill">
                    <li class="nav-item">
                        <button type="button" class="btn btn-primary" onclick="reset()">수량 초기화</a>
                    </li>
                    <li class="nav-item">
                        <input type="submit" id="submit" class="btn btn-primary" value="주문하기"></a>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</body>
</html>