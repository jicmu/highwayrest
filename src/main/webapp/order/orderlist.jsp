<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script>

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
                <a class="nav-link" href="#">후기</a>
              </li>
            </ul>
            <hr>
        </div>
        <div class="row">
            <div class="col-4">
                <h2>주문</h2>
            </div>
            <div class="col-8">
                <form class="d-flex" role="search" action="#" method="post">
                    <input type="hidden" name="searchType" val="3">
                    <input class="form-control me-2" type="search" name="searchWord" placeholder="메뉴명">
                    <button class="btn btn-outline-success" type="submit">검색</button>
                </form>
            </div>
            <hr>
        </div>
        <form action="#" method="post">
            <div class="row">
                <table class="text-center">
                    <!-- <c:forEach var="m" item="${list }"> -->
                    <tr><th>메뉴</th><th>가격</th><th>수량</th></tr>
                    <tr>
                        <input type="hidden" name="menu" val="${m.menuCd }">
                        <input type="hidden" name="price" val="${m.price }">
                        <td>${m.menuNm }</td>
                        <td>${m.price }</td>
                        <td><input type="number" name="amount"></td>
                    </tr>
                    <!-- </c:forEach> -->
                </table>
            </div>
            <div class="row justify-content-end mt-3 d-flex">
                <input type="hidden" name="tamount" val="">
                <input type="hidden" name="tprice" val="">
                <h5 class="justify-content-end">총 주문 수량 : </h5>
                <h5 class="justify-content-end">총 결제 금액 : </h5>
                <hr>
            </div>
            <div class="row">
                <ul class="nav justify-content-center nav-pills nav-fill">
                    <li class="nav-item">
                        <button type="button" class="btn btn-primary">수량 재설정</a>
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