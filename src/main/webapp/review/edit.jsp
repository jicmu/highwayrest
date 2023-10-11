<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>후기 수정</h3>
<form action="${pageContext.request.contextPath}/listReview" method="post" enctype="multipart/form-data">
    <table border="1">
        <tr>
            <th>주문 번호</th><!--주문 메뉴가 뜨는 게 좋지 않을까-->
            <td><input type="number" value="${r.orderNo}" readonly></td>
        </tr>
        <tr>
            <th>별점</th>
            <!-- 별점 수정 시에는..?-->
            <td><input type="number" value="${r.star}"></td>
        </tr>
        <tr>
            <th>후기</th>
            <td><textarea cols="50" rows="30" value="${r.content}"></textarea></td>
        </tr>
        <tr>
            <!-- TODO 업로드한 모든 사진을 한 번에 수정 가능하게?-->
            <c:if test="${not empty r.File1!=null}">
                <th>사진</th>
                <!-- 사진크기-->
                <td><img src="${r.File1}" style="width:200px;height:200px"></td>
            </c:if>
        </tr>
        <tr>
            <th>수정하기</th>
            <td><input type="submit" value="수정"></td>
        </tr>
    </table>
</form>
</body>
</html>
