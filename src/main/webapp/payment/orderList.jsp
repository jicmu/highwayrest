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
                
            </section>
        </div>
    <%--    <%@ include file="footer.jsp" %>--%>
        <%@ include file="/common/footer.jsp"%>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // 무한 스크롤
            window.onload = () => {
                let page = 1;

                const io = new IntersectionObserver((entries, observer) => {
                    entries.forEach(entry => {
                        if (!entry.isIntersecting) return;

                        let xhr = new XMLHttpRequest();

                        xhr.open("get", "${pageContext.request.contextPath}/api/page?page=" + page++ + "&amount=" + 10);

                        xhr.send();

                        xhr.onload = () => {
                            let parsed = JSON.parse(xhr.responseText);

                            let sectionOrders = document.querySelector("section#orders");

                            for (let o in parsed) {
                                let article = document.createElement("article");
                                article.classList = "card p-0 mb-3";

                                let p = JSON.parse(parsed[o]);

                                let txt = `
                                <div class="card-header">
                                    ${"${p.ordersNo}"}<input type="hidden" name="items" value="${'${p.ordersNo}'}">
                                    <div id="cancel-1" class="float-end text-muted" style="cursor: pointer;">
                                        ${'${p.restNo}'}
                                    </div>
                                </div>
                                <div>
                                    <div class="col-3 m-3 d-inline-block">
                                        <label for="menu-${'${p.ordersNo}'}">음식</label>
                                        <input class="form-control form-control-sm" type="text" id="menu-${'${p.ordersNo}'}"
                                            name="menu" value="${'${p.menu}'}" readonly>
                                    </div>
                                    <div class="col-3 m-3 d-inline-block">
                                        <label for="quantity-${'${p.ordersNo}'}">수량</label>
                                        <input class="form-control form-control-sm" type="number"
                                            id="quantity-${'${p.ordersNo}'}" name="quantity" value="${'${p.quantity}'}" readonly>
                                    </div>
                                    <div class="col-3 m-3 d-inline-block">
                                        <label for="price-${'${p.ordersNo}'}">총 가격</label>
                                        <input class="form-control form-control-sm" type="number" name="price"
                                            id="price-${'${p.ordersNo}'}" value="${'${p.pay}'}" readonly>
                                    </div>
                                    <span id="status-container-${'${p.ordersNo}'}" ident="status-container-${'${p.ordersNo}'}" orderNo="status-container-${'${p.orderNo}'}">
                                `;

                                if (p.status == 0) {
                                    txt += `<span class="status-dot bg-primary" id="status-${'${p.ordersNo}'}"></span> 수락 대기`;
                                } else if (p.status == 1) {
                                    txt += `<span class="status-dot bg-success" id="status-${'${p.ordersNo}'}"></span> 수락`;
                                } else if (p.status == 2) {
                                    txt += `<span class="status-dot bg-danger" id="status-${'${p.ordersNo}'}"></span> 거부`;
                                } else if (p.status == 3) {
                                    txt += `<span class="status-dot bg-warning" id="status-${'${p.ordersNo}'}"></span> 취소`;
                                } else if (p.status == 4) {
                                    txt += `<span class="status-dot bg-info" id="status-${'${p.ordersNo}'}"></span> 수령 완료<span><a href="${pageContext.request.contextPath}/addReview?orderNo=${'${p.orderNo}'}">후기 작성</a></span>`;
                                } else if (p.status == 5) {
                                    txt += `<span class="status-dot bg-info" id="status-${'${p.ordersNo}'}"></span> 조리 완료`;
                                } else {
                                    txt += '<span>알 수 없음</span>'
                                }

                                if (p.status == 0) {
                                    txt += `<button class="btn btn-danger btn-cancel" id="btn-cancel-${'${p.ordersNo}'}" ident="btn-cancel-${'${p.ordersNo}'}" orderNo="btn-cancel-${'${p.orderNo}'}">주문 취소</button>`;
                                } else if (p.status == 5) {
                                    txt += `<button class="btn btn-info btn-done" id="btn-done-${'${p.ordersNo}'}" ident="btn-done-${'${p.ordersNo}'}" orderNo="btn-done-${'${p.orderNo}'}">수령</button>`;
                                }

                                article.innerHTML = txt;

                                section.append(article);
                            }

                            observer.unobserve(entry.target);

                            if (parsed[9] != null) {
                                observer.observe(document.querySelector("article:nth-last-child(2)"));
                            }
                        };
                        
                    });
                });

                let section = document.querySelector("section");

                io.observe(section);
            }
        // /무한 스크롤

        document.querySelectorAll(".btn-cancel").forEach((element) => {
            element.addEventListener("click", () => {
                let xhr = new XMLHttpRequest();

                let cancelUrl = "${pageContext.request.contextPath}/api/cancel";
                if (!confirm("같이 주문했던 음식도 취소됩니다. 취소하시겠습니까?")) {
                    return;
                }

                xhr.open("post", cancelUrl);

                xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

                let index = element.getAttribute("ident").replace("btn-cancel-", "");

                let parameter = {
                        ordersNo: index,
                };

                xhr.send(JSON.stringify(parameter));

                xhr.onload = () => {
                    if (xhr.status == 200) {
                        document.querySelectorAll("[ident=btn-cancel-" + index + "]").forEach((elem) => {
                            elem.remove();
                        });

                        let status = document.querySelectorAll("[ident=status-container-" + index + "]");

                        status.forEach((statusElem) => {
                            statusElem.innerHTML = '<span class="status-dot bg-warning" id="status-${'${p.ordersNo}'}"></span> 취소'
                        });
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
                let orderNo = element.getAttribute("orderNo").replace("btn-done-", "");

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
                            statusElem.innerHTML = '<span class="status-dot bg-info" id="status-${'${p.ordersNo}'}"></span>수령 완료<span><a href="${pageContext.request.contextPath}/addReview?' + orderNo + '">후기 작성</a></span>'
                        });
                    }
                }
            });
        });
    </script>
</body>

</html>
