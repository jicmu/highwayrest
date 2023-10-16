<%@ page isELIgnored="false" pageEncoding="UTF-8" language="java" %>
<div class="container" style="border: 1px solid mediumturquoise; background-color: mediumturquoise; border-radius: 0 0 40px 40px / 0 0 40px 40px">
    <div class="row" style="margin-top: 20px; margin-bottom: 20px">
        <div class="col-4" style="align-items: center; display: flex">
            <img src="<c:url value="/common/logo/call.png" />" alt="Bootstrap" width="55" height="50" />
            <a id="nav-logo" href="#">: 고속주문</a>
        </div>
        <div class="col-8" style="align-items: center; display: inline-grid; margin-top: 9px">
            <ul class="nav justify-content-end">
                <c:choose>
                    <c:when test="${empty list}">
                        <li class="nav-item">
                            <a class="nav-link" href="#">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">회원가입</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">리스트</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="#">마이페이지</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">주문목록</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">포인트</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>