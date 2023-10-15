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
                    <article class="card p-0 mb-3" id="article-${o.ordersNo}" ident="article-${o.ordersNo}">
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
                            <span id="status-container-${o.ordersNo}" ident="status-container-${o.ordersNo}">
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
                        <c:if test="${o.status eq 0}">
                            <div class="btn-group" id="btn-group-${o.ordersNo}" ident="btn-group-${o.ordersNo}">
                                <button class="btn btn-danger btn-deny" id="btn-deny-${o.ordersNo}">거절</button>
                                <button class="btn btn-primary btn-accept" id="btn-accept-${o.ordersNo}">수락</button>
                            </div>
                        </c:if>
                        <c:if test="${o.status eq 1}">
                            <div class="btn-group" id="btn-group-${o.ordersNo}" ident="btn-group-${o.ordersNo}">
                                <button class="btn btn-primary btn-finish" id="btn-finish-${o.ordersNo}">조리 완료</button>
                            </div>
                        </c:if>
                    </article>
                </c:forEach>
            </section>
        </div>
        <%--    <%@ include file="footer.jsp" %>--%>
            <%@ include file="/common/footer.jsp"%>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        window.onload = () => {
            document.querySelectorAll(".btn-deny").forEach((element) => {
                element.addEventListener("click", () => {
                    let xhr = new XMLHttpRequest();

                    xhr.open("post", "${pageContext.request.contextPath}/api/deny");

                    let index = element.id.replace("btn-deny-", "");

                    let parameter = {
                        ordersNo: index
                    };

                    xhr.send(JSON.stringify(parameter));

                    xhr.onload = () => {
                        if (xhr.status == 200) {
                            document.querySelectorAll("[ident=btn-group-" + index + "]").forEach((elem) => {
                                elem.remove();
                            });

                            let status = document.querySelectorAll("[ident=status-container-" + index + "]");

                            status.forEach((statusElem) => {
                                statusElem.innerHTML = '<span class="status-dot bg-danger" id="status-${o.ordersNo}"></span> 거부';
                            });
                        }
                    }
                })
            });

            document.querySelectorAll(".btn-finish").forEach((element) => {
                element.addEventListener("click", () => {
                    let xhr = new XMLHttpRequest();

                    xhr.open("post", "${pageContext.request.contextPath}/api/finish");

                    let index = element.id.replace("btn-finish-", "");

                    let parameter = {
                        ordersNo: index
                    };

                    xhr.send(JSON.stringify(parameter));

                    xhr.onload = () => {
                        if (xhr.status == 200) {
                            document.querySelectorAll("[ident=btn-group-" + index + "]").forEach((elem) => {
                                elem.remove();
                            });

                            let status = document.querySelectorAll("[ident=status-container-" + index + "]");

                            status.forEach((statusElem) => {
                                statusElem.innerHTML = '<span class="status-dot bg-info" id="status-${o.ordersNo}"></span> 조리 완료';
                            });
                        }
                    }
                })
            });



            document.querySelectorAll(".btn-accept").forEach((element) => {
                element.addEventListener("click", () => {
                    let xhr = new XMLHttpRequest();

                    xhr.open("post", "${pageContext.request.contextPath}/api/accept");
                    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

                    let index = element.id.replace("btn-accept-", "");

                    let parameter = {
                        ordersNo: index
                    };

                    xhr.send(JSON.stringify(parameter));

                    xhr.onload = () => {
                        if (xhr.status == 200) {
                            document.querySelectorAll("[ident=btn-group-" + index + "]").forEach((elem) => {
                                elem.remove();
                            });

                            let status = document.querySelectorAll("[ident=status-container-" + index + "]");

                            status.forEach((statusElem) => {
                                statusElem.innerHTML = '<span class="status-dot bg-success" id="status-${o.ordersNo}"></span> 수락'
                            });

                            document.querySelectorAll("[ident=article-" + index + "]").forEach((element) => {
                                let successBtnArea = document.createElement("div");
                                successBtnArea.classList = "btn-group";
                                successBtnArea.id= "btn-group-" + index;
                                successBtnArea.setAttribute("ident", "btn-group-" + index);
                                successBtnArea.innerHTML = '<button class="btn btn-primary btn-finish" id="btn-finish-' + index + '">조리 완료</button>';

                                element.append(successBtnArea);
                            });

                             document.querySelectorAll(".btn-finish").forEach((element) => {
                                element.addEventListener("click", () => {
                                    let xhr = new XMLHttpRequest();

                                    xhr.open("post", "${pageContext.request.contextPath}/api/finish");

                                    let index = element.id.replace("btn-finish-", "");

                                    let parameter = {
                                        ordersNo: index
                                    };

                                    xhr.send(JSON.stringify(parameter));

                                    xhr.onload = () => {
                                        if (xhr.status == 200) {
                                            document.querySelectorAll("[ident=btn-group-" + index + "]").forEach((elem) => {
                                                elem.remove();
                                            });

                                            let status = document.querySelectorAll("[ident=status-container-" + index + "]");

                                            status.forEach((statusElem) => {
                                                statusElem.innerHTML = '<span class="status-dot bg-info" id="status-${o.ordersNo}"></span> 조리 완료';
                                            });
                                        }
                                    }
                                })
                            });
                        }
                    }
                })
            });
        }
    </script>
</body>
</html>
