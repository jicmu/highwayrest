<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script>
        window.onload = () => {
            document.getElementById("foodNm").onchange = function(){
                let searchWord = document.getElementById("searchWord");
                let foodNm = document.getElementById("foodNm").value;
                searchWord.value = foodNm;
            }

            const calc = () => {
                let amount = document.getElementById("${m.no }_amount").value;
                let price = document.getElementById("${m.no }_price").value;

                let sum;
                sum += amount;

                let tamount = document.getElementById("tamount");
                tamount.innerHTML = "총 주문 수량 : '" + sum + "'";

                let inTamount = document.getElementById("inTamount");
                inTamount.value = sum;

                let sumtotal;
                sumtotal += (amount * price);

                let tprice = document.getElementById("tprice");
                tprice.innerHTML = "총 결제 금액 : '" + sumtotal + "'";

                let inTprice = document.getElementById("inTprice");
                inTprice.value = sumtotal;
            }

            const reset = () => {
                let amount = document.querySelectorAll("input[name='amount']").value;
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
                <form class="d-flex" role="search" action="${pageContext.request.contextPath }/foodsearch" method="post">
                    <input type="hidden" name="searchType" value="3">
                    <input type="hidden" name="memberNo" value="1">
                    <input type="hidden" id="searchWord" name="searchWord" value="">
                    <input class="form-control me-2" type="search" id="foodNm" name="name" placeholder="메뉴명">
                    <button class="btn btn-outline-success" type="submit">검색</button>
                </form>
            </div>
            <hr>
        </div>
        <form action="${pageContext.request.contextPath }/order" method="get">
            <input type="hidden" name="stdRestCd" value="${stdRestCd }">
            <div class="row">
                <table class="text-center" id="food-table">
                    <tr><th>메뉴</th><th>가격</th><th>수량</th></tr>
                    <c:forEach var="m" items="${list }">
                    <tr>
                        <td>${m.name }<input type="hidden" id="${m.no }_name" name="foodNo" value="${m.no }"></td>
                        <td>${m.foodCost }<input type="hidden" id="${m.no }_price" name="foodCost" value="${m.foodCost }"></td>
                        <td><input type="number" id="${m.no }_amount" name="amount"></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="row d-flex justify-content-end mt-3">
                <input type="hidden" id= name="tamount" value="">
                <input type="hidden" id= name="tprice" value="">
                <h5 class="d-flex justify-content-end" id="tamount"></h5>
                <h5 class="d-flex justify-content-end" id="tprice"></h5>
                <hr>
            </div>
            <div class="row">
                <ul class="nav justify-content-center nav-pills nav-fill">
                    <li class="nav-item">
                        <button type="button" class="btn btn-primary" onclick="reset()">수량 재설정</a>
                    </li>
                    <li class="nav-item">
                        <input type="submit" class="btn btn-primary" value="주문하기"></a>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</body>
</html>