<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>주문 확인 - 휴게소 음식 주문</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
            crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
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
                <c:forEach items="${orderList}" var="o" varStatus="status">
                    <article class="card p-0 mb-3">
                        <div class="card-header">
                            ${o.ordersNo}<input type="hidden" name="items" value="${o.ordersNo}">
                            <div id="cancel-1" class="float-end text-muted" style="cursor: pointer;">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                    class="bi bi-x" viewBox="0 0 16 16">
                                    <path
                                        d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                                </svg>
                            </div>
                        </div>
                        <div>
                            <div class="col-3 m-3 d-inline-block">
                                <label for="menu-${o.ordersNo}">음식</label>
                                <input class="form-control form-control-sm" type="text" id="menu-${o.ordersNo}"
                                    name="menu" value="${o.menu}">
                            </div>
                            <div class="col-3 m-3 d-inline-block">
                                <label for="quantity-${o.ordersNo}">수량</label>
                                <input class="form-control form-control-sm" type="number"
                                    id="quantity-${o.ordersNo}" name="quantity" value="${o.quantity}">
                            </div>
                            <div class="col-3 m-3 d-inline-block">
                                <label for="price-${o.ordersNo}">총 가격</label>
                                <input class="form-control form-control-sm" type="number" name="price"
                                    id="price-${o.ordersNo}" value="${o.pay}" readonly>
                            </div>
                            <span id="status-container-${o.ordersNo}">
                                <c:choose>
                                    <c:when test="${o.status eq 0}">
                                        <span class="status-dot bg-primary" id="status-${o.ordersNo}"></span> 수락 대기
                                    </c:when>
                                    <c:when test="${o.status eq 1}">
                                        <span class="status-dot bg-success" id="status-${o.ordersNo}"></span> 수락
                                    </c:when>
                                    <c:when test="${o.status eq 2}">
                                        <span class="status-dot bg-danger" id="status-${o.ordersNo}"></span> 거부
                                    </c:when>
                                    <c:when test="${o.status eq 3}">
                                        <span class="status-dot bg-warning" id="status-${o.ordersNo}"></span> 취소
                                    </c:when>
                                    <c:otherwise>
                                        <span>알 수 없음</span>
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                        <c:if test="${o.status eq 0}">
                            <button class="btn btn-danger btn-cancel" id="btn-cancel-${o.ordersNo}">주문 취소</button>
                        </c:if>
                    </article>
                </c:forEach>
            </section>
        </main>
        <script>
            document.querySelectorAll(".btn-cancel").forEach((element) => {
                element.addEventListener("click", () => {
                    let xhr = new XMLHttpRequest();

                    xhr.open("post", "${pageContext.request.contextPath}/api/cancel");
                    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

                    let index = element.id.replace("btn-cancel-", "");

                    let parameter = {
                        ordersNo: index
                    };

                    xhr.send(JSON.stringify(parameter));

                    xhr.onload = () => {
                        if (xhr.status == 200) {
                            document.querySelector("#btn-cancel-" + index).remove();

                            let status = document.querySelector("#status-container-" + index);

                            status.innerHTML = '<span class="status-dot bg-warning" id="status-${o.ordersNo}"></span> 취소'
                        }
                    }
                })
            });
        </script>
    </body>
</html>