<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>후기 작성</title>
    <!-- TODO: 내 주문 내역에 후기 작성 버튼이 있으며, 해당 버튼은 주문한 날로부터 7일 내로만 볼 수 있다-->
</head>
<body>
<form action="${pageContext.request.contextPath}/addReview" method="post" enctype="multipart/form-data">
    <table border="1">
        <tr>
            <th>주문 번호</th><!--주문 메뉴가 뜨는 게 좋지 않을까-->
            <td>${orderNo}</td>
        </tr>
        <tr>
            <th>별점</th>
            <td>
                <label><input type="radio" name="star" value="1">★</label><br/>
                <label><input type="radio" name="star" value="2">★★</label><br/>
                <label><input type="radio" name="star" value="3">★★★</label><br/>
                <label><input type="radio" name="star" value="4">★★★★</label><br/>
                <label><input type="radio" name="star" value="5">★★★★★</label><br/>
            </td>
        </tr>
        <tr>
            <th>후기</th>
            <td><textarea rows="30" cols="50" name="content"></textarea></td>
        </tr>
        <tr>
            <th>사진</th>
            <td><input type="file" name="img1"></td>
        </tr>
        <tr><th>작성하기</th>
            <td><input type="submit" value="작성"></td>
        </tr>
    </table>
</form>
</body>
</html>
