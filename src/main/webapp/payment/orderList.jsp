<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <title>주문 확인</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/common/css/payment.css' />" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>

<body>
    <div id="wrapper">
        <%@ include file="/common/header.jsp"%>
        <div class="container main">
            <h1 class="p-1 pb-2 border-bottom border-muted">주문 확인</h1>
            <section id="orders" class="row">
                <c:forEach items="${orderList}" var="o" varStatus="status">
                    <article class="card p-0 mb-3">
                        <div class="card-header">
                            ${o.ordersNo}<input type="hidden" name="items" value="${o.ordersNo}">
                            <div id="cancel-1" class="float-end text-muted" style="cursor: pointer;">
                                ${o.restNo}
                            </div>
                        </div>
                        <div>
                            <div class="col-3 m-3 d-inline-block">
                                <label for="menu-${o.ordersNo}">음식</label>
                                <input class="form-control form-control-sm" type="text" id="menu-${o.ordersNo}"
                                    name="menu" value="${o.menu}" readonly>
                            </div>
                            <div class="col-3 m-3 d-inline-block">
                                <label for="quantity-${o.ordersNo}">수량</label>
                                <input class="form-control form-control-sm" type="number"
                                    id="quantity-${o.ordersNo}" name="quantity" value="${o.quantity}" readonly>
                            </div>
                            <div class="col-3 m-3 d-inline-block">
                                <label for="price-${o.ordersNo}">총 가격</label>
                                <input class="form-control form-control-sm" type="number" name="price"
                                    id="price-${o.ordersNo}" value="${o.pay}" readonly>
                            </div>
                            <span id="status-container-${o.ordersNo}" ident="status-container-${o.ordersNo}" orderNo="status-container-${o.orderNo}">
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
                                    <c:when test="${o.status eq 4}">
                                        <span class="status-dot bg-info" id="status-${o.ordersNo}"></span> 수령 완료
                                        <span><a href="${pageContext.request.contextPath}/addReview?orderNo=${o.ordersNo}">후기 작성</a></span>
                                    </c:when>
                                    <c:when test="${o.status eq 5}">
                                        <span class="status-dot bg-info" id="status-${o.ordersNo}"></span> 조리 완료
                                    </c:when>
                                    <c:otherwise>
                                        <span>알 수 없음</span>
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                        <c:choose>
                            <c:when test="${o.status eq 0}">
                                <button class="btn btn-danger btn-cancel" id="btn-cancel-${o.ordersNo}" ident="btn-cancel-${o.ordersNo}" orderNo="btn-cancel-${o.orderNo}">주문 취소</button>
                            </c:when>
                            <c:when test="${o.status eq 5 }">
                                <button class="btn btn-info btn-done" id="btn-done-${o.ordersNo}" ident="btn-done-${o.ordersNo}">수령</button>
                            </c:when>
                        </c:choose>
                    </article>
                </c:forEach>
            </section>
        </div>
    <%--    <%@ include file="footer.jsp" %>--%>
        <%@ include file="/common/footer.jsp"%>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.querySelectorAll(".btn-cancel").forEach((element) => {
            element.addEventListener("click", () => {
                let xhr = new XMLHttpRequest();

                let cancelUrl;
                let partial = confirm("이 주문만 취소하시겠습니까? 취소를 누르시면 전체 취소됩니다.");

                if (partial) {
                    cancelUrl = "${pageContext.request.contextPath}/api/partial-cancel";
                } else {
                    if (!confirm("같이 주문했던 음식도 취소됩니다. 취소하시겠습니까?")) {
                        return;
                    }
                    cancelUrl = "${pageContext.request.contextPath}/api/cancel";
                }

                xhr.open("post", cancelUrl);

                console.log(cancelUrl);

                xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

                let index = element.getAttribute("ident").replace("btn-cancel-", "");
                let orderNo = element.getAttribute("orderNo").replace("btn-cancel-", "");

                let parameter;
                if (partial) {
                    parameter = {
                        orderNo: orderNo,
                    }
                } else {
                    parameter = {
                        ordersNo: index,
                    };
                }

                xhr.send(JSON.stringify(parameter));

                xhr.onload = () => {
                    if (xhr.status == 200) {
                        if (partial) {
                            document.querySelector("[orderNo=btn-cancel-" + orderNo + "]").remove();

                            let status = document.querySelector("[orderNo=status-container-" + orderNo + "]");

                            status.innerHTML = '<span class="status-dot bg-warning" id="status-${o.ordersNo}"></span> 취소'
                        } else {
                            document.querySelectorAll("[ident=btn-cancel-" + index + "]").forEach((elem) => {
                                elem.remove();
                            });

                            let status = document.querySelectorAll("[ident=status-container-" + index + "]");

                            status.forEach((statusElem) => {
                                statusElem.innerHTML = '<span class="status-dot bg-warning" id="status-${o.ordersNo}"></span> 취소'
                            });
                        }
                    }
                }
            })
        });

        document.querySelectorAll(".btn-done").forEach((element) => {
            element.addEventListener("click", () => {
                let xhr = new XMLHttpRequest();

                xhr.open("post", "${pageContext.request.contextPath}/api/done");
                xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

                let index = element.getAttribute("ident").replace("btn-done-", "");

                let parameter = {
                    ordersNo: index
                };

                xhr.send(JSON.stringify(parameter));

                xhr.onload = () => {
                    if (xhr.status == 200) {
                        document.querySelectorAll("[ident=btn-done-" + index + "]").forEach((elem) => {
                            elem.remove();
                        });

                        let status = document.querySelectorAll("[ident=status-container-" + index + "]");

                        status.forEach((statusElem) => {
                            statusElem.innerHTML = '<span class="status-dot bg-info" id="status-${o.ordersNo}"></span>수령 완료<span><a href="${pageContext.request.contextPath}/addReview">후기 작성</a></span>'
                        });
                    }
                }
            });
        });
    </script>
</body>

</html>
