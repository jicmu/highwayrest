<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 - 휴게소 음식 주문</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

    <style>
        .status-dot {
            border-radius: 100rem;
            width: 0.5rem;
            height: 0.5rem;
            display: inline-block;
        }
    </style>
</head>
<body>
    <main class="container-sm">
        <h1 class="p-1 pb-2 border-bottom border-muted">주문 확인</h1>
        <section id="orders" class="row">
            <form action="" method="get" name="form-payment">
                <c:forEach items="${orderList}" var="o">
                    <article class="card p-0 mb-3">
                        <div class="card-header">
                            ${o.menu}
                            <div id="cancel-1" class="float-end text-muted" style="cursor: pointer;">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                </svg>
                            </div>
                        </div>
                        <div>
                            <div class="col-3 m-3 d-inline-block">
                                <label for="quantity-1">수량</label>
                                <select name="quantity" id="quantity-1" class="form-select form-select-sm" disabled>
                                    <option value="0">0</option>
                                    <option value="1" selected>1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                </select>
                            </div>
                            <div class="col-3 m-3 d-inline-block">
                                <label for="price-1">가격</label>
                                <input class="form-control form-control-sm" type="number" name="price" id="price-1" value="${o.pay}" disabled>
                            </div>
                            <div class="col-3 m-3 d-inline-block">
                                <!-- 0: 결제 완료, 1: 수락, 2: 거부, 3.취소 -->
                                <c:choose>
                                    <c:when test="${o.status eq 0}">
                                        <span class="status-dot bg-primary"></span> 결제 완료
                                    </c:when>
                                    <c:when test="${o.status eq 1}">
                                        <span class="status-dot bg-success"></span> 수락
                                    </c:when>
                                    <c:when test="${o.status eq 2}">
                                        <span class="status-dot bg-danger"></span> 거부
                                    </c:when>
                                    <c:when test="${o.status eq 3}">
                                        <span class="status-dot bg-warning"></span> 취소
                                    </c:when>
                                    <c:otherwise>
                                        <span>알 수 없음</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </article>
                </c:forEach>
            </form>
        </section>
    </main>
</body>
</html>