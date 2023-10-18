<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <title>주문 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <%-- <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css"> --%>
    <link rel="stylesheet" href="<c:url value='/common/css/payment.css' />" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>

<body>
    <div id="wrapper">
        <%-- <%@ include file="/common/header.jsp"%> --%>
        <div class="container main">
            <h1 class="p-1 pb-2 border-bottom border-muted">주문 확인</h1>
            <section id="orders" class="row">
                
            </section>
        </div>
    <%--    <%@ include file="footer.jsp" %>--%>
    <%--    <%@ include file="/common/footer.jsp"%> --%>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    // 취소, 수령 함수
        let setEvent = () => {
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
        }
    // /취소, 수령 함수

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

                            let article = document.createElement("article");
                            for (let o in parsed) {
                                let card = "";
                                
                                let p = JSON.parse(parsed[o]);

                                article = document.createElement("article");
                                article.classList = "card p-0 mb-3";

                                let cardHeader = `
                                <div class="card-header">
                                    ${"${p[0].orderNo}"}
                                    <input type="hidden" name="items" value="${'${p[0].ordersNo}'}">
                                    <div id="cancel-1" class="float-end text-muted" style="cursor: pointer;">
                                        <a href="${pageContext.request.contextPath}/restinfo?svarCd=${'${p[0].restNo}'}"> ${'${p[0].restNo}'}</a>
                                    </div>
                                </div>
                                <div>`;

                                card += cardHeader;

                                let contentMain = "<div class='card-body'>";

                                for (let a of p) {
                                    contentMain += `
                                        <div class="col-3 m-3 d-inline-block">
                                            <label for="menu-${'${a.ordersNo}'}">음식</label>
                                            <input class="form-control form-control-sm" type="text" id="menu-${'${a.ordersNo}'}"
                                                name="menu" value="${'${a.menu}'}" readonly>
                                        </div>
                                        <div class="col-3 m-3 d-inline-block">
                                            <label for="quantity-${'${a.ordersNo}'}">수량</label>
                                            <input class="form-control form-control-sm" type="number"
                                                id="quantity-${'${a.ordersNo}'}" name="quantity" value="${'${a.quantity}'}" readonly>
                                        </div>
                                        <div class="col-3 m-3 d-inline-block">
                                            <label for="price-${'${a.ordersNo}'}">총 가격</label>
                                            <input class="form-control form-control-sm" type="number" name="price"
                                                id="price-${'${a.ordersNo}'}" value="${'${a.pay}'}" readonly>
                                        </div>
                                    `;
                                }

                                contentMain += "</div>";

                                card += contentMain;

                                card += "<div class='card-footer'>"

                                card += `<span class="status" id="status-container-${'${p[0].ordersNo}'}" ident="status-container-${'${p[0].ordersNo}'}" orderNo="status-container-${'${p[0].orderNo}'}">`;

                                if (p[0].status == 0) {
                                    card += `<span class="status-dot bg-primary" id="status-${'${p[0].ordersNo}'}"></span> 수락 대기`;
                                } else if (p[0].status == 1) {
                                    card += `<span class="status-dot bg-success" id="status-${'${p[0].ordersNo}'}"></span> 수락`;
                                } else if (p[0].status == 2) {
                                    card += `<span class="status-dot bg-danger" id="status-${'${p[0].ordersNo}'}"></span> 거부`;
                                } else if (p[0].status == 3) {
                                    card += `<span class="status-dot bg-warning" id="status-${'${p[0].ordersNo}'}"></span> 취소`;
                                } else if (p[0].status == 4) {
                                    card += `<span class="status-dot bg-info" id="status-${'${p[0].ordersNo}'}"></span> 수령 완료<span><a href="${pageContext.request.contextPath}/addReview?orderNo=${'${p[0].orderNo}'}">후기 작성</a></span>`;
                                } else if (p[0].status == 5) {
                                    card += `<span class="status-dot bg-info" id="status-${'${p[0].ordersNo}'}"></span> 조리 완료`;
                                } else {
                                    card += '<span>알 수 없음</span>'
                                }
                                card += "</div>";

                                card += "</div>";

                                if (p[0].status == 0) {
                                    card += `<button class="btn btn-danger btn-cancel" id="btn-cancel-${'${p[0].ordersNo}'}" ident="btn-cancel-${'${p[0].ordersNo}'}" orderNo="btn-cancel-${'${p[0].orderNo}'}">주문 취소</button>`;
                                } else if (p[0].status == 5) {
                                    card += `<button class="btn btn-info btn-done" id="btn-done-${'${p[0].ordersNo}'}" ident="btn-done-${'${p[0].ordersNo}'}" orderNo="btn-done-${'${p[0].orderNo}'}">수령</button>`;
                                }

                                article.innerHTML = article.innerHTML + card;

                                section.append(article);

                            }

                            setEvent();

                            observer.unobserve(entry.target);

                            if (Object.keys(parsed).length != 0) {
                                observer.observe(document.querySelector("article:nth-last-child(2)"));
                            }
                        };
                        
                    });
                });

                let section = document.querySelector("section");

                io.observe(section);
            }
        // /무한 스크롤
        setEvent();
    </script>
</body>

</html>
