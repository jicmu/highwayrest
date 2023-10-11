<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>주문 확인 - 휴게소 음식 주문</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
    <main class="container-sm">
        <h1 class="p-1 pb-2 border-bottom border-muted">주문 확인</h1>
        <section id="orders" class="row">
            <c:forEach items="${orderList}" var="o" varStatus="status">
                <article class="card p-0 mb-3">
                    <div class="card-header">
                        ${o.ordersNo}<input type="hidden" name="items" value="${o.ordersNo}">
                        <div id="cancel-1" class="float-end text-muted" style="cursor: pointer;">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                            </svg>
                        </div>
                    </div>
                    <div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="menu-${status.index}">음식</label>
                            <input class="form-control form-control-sm" type="number" id="menu-${status.index}" name="menu" value="${o.menu}">
                        </div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="quantity-${status.index}">수량</label>
                            <input class="form-control form-control-sm" type="number" id="quantity-${status.index}" name="quantity" value="${o.quantity}">
                        </div>
                        <div class="col-3 m-3 d-inline-block">
                            <label for="price-${status.index}">총 가격</label>
                            <input class="form-control form-control-sm" type="number" name="price" id="price-${status.index}" value="${o.pay}" readonly>
                        </div>
                    </div>
                    <c:if test="${o.status eq 0}">
                        <div class="btn-group">
                            <button class="btn btn-danger">거절</button>
                            <button class="btn btn-primary">수락</button>
                        </div>
                    </c:if>
                </article>
            </c:forEach>
        </section>
    </main>
</body>
</html>