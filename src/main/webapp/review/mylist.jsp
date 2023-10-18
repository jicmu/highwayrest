<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        const editReview = (reviewNo) => {
            location.href = "/highwayrest/editReview?reviewNo=" + reviewNo;
        }

        const delReview = (reviewNo) => {
            let flag = confirm("후기를 삭제하면 다시 작성할 수 없습니다. 삭제하시겠습니까?");
            if (flag) {
                location.href = "/highwayrest/delReview?reviewNo=" + reviewNo;
            } else {
                alert("삭제가 취소되었습니다.");
            }
        }
    </script>
</head>
<body>
<div id="wrapper">
    <%@ include file="/common/header.jsp"%>
    <div class="container">
        <table border="1">

            <c:forEach var="r" items="${mylist }">
                <!-- 작성자와 로그인한 회원이 동일할 때-->
                <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                    <input type="button" value="수정" class="btn btn-outline-secondary" onclick="editReview(${r.reviewNo})">
                    <input type="button" value="삭제" class="btn btn-outline-secondary" onclick="delReview(${r.reviewNo})">
                </div>
                <tr><th>휴게소</th><td>${r.svarCd}</td></tr>
                <tr><th>주문번호</th><td>${r.orderNo}</td></tr>
                <tr><th>별점</th><td>${r.star}</td></tr>
                <tr><th>후기</th><td>${r.content}</td></tr>
                    <tr>
                        <th>사진</th>
                        <td>
                            <c:if test="${not empty r.img1 }">
                                <img src="${r.img1 }">
                            </c:if>
                            <c:if test="${not empty r.img2 }">
                                <img src="${r.img2 }">
                            </c:if>
                            <c:if test="${not empty r.img3 }">
                                <img src="${r.img3 }">
                            </c:if>
                        </td>
                    </tr>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="/common/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" />
</body>

</html>