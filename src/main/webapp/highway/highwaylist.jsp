<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
  <div class="container">
    <div class="row">
        <div class="col mt-3">
            <form class="d-flex" role="search" action="#" method="post">
                <input type="hidden" name="searchType" val="1">
                <input class="form-control me-2" type="search" name="searchWord" placeholder="고속도로명">
                <button class="btn btn-outline-success" type="submit">검색</button>
            </form>
        </div>
    </div>
    <div class="mt-3">
        <h2>전국 고속도로 목록</h2>
    </div>
    <div class="col-4 mt-3">
        <!-- <c:forEach var="h" items="${list }"> -->
            <a href="#?routeCd=${h.routeCd }" class="list-group-item list-group-item-action">${h.routeNm}</a>
        <!-- </c:forEach> -->
    </div>
    <nav class="mt-3" aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </nav>
  </div>
</body>
</html>