<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        //TODO 이미지 수정 및 삭제
        $(document).ready(function(){
            $(".edit").click(function(){
                let imgNo = $(this).attr("val");
                location.href="/highwayrest/editImg?reviewNo=${r.reviewNo}&imgNo="+imgNo;
            });

            });

            $(".del").click(function(){
                let imgNo = $(this).attr("val");
                location.href="/highwayrest/delImg?reviewNo=${r.reviewNo }&imgNo="+imgNo;
        });
    </script>
</head>
<body>
<div id="wrapper">
    <%@ include file="/common/header.jsp" %>
    <div class="container">
        <h3>후기 수정</h3>
        <form action="/highwayrest/editReview" method="post" enctype="multipart/form-data">
            <table border="1">
                <tr>
                    <!--주문 메뉴가 뜨는 게 좋지 않을까-->
                    <th>주문 번호</th>
                    <td><input type="number" value="${r.orderNo}" readonly></td>
                </tr>
                <tr>
                    <th>리뷰 번호</th>
                    <td><input type="number" value="${r.reviewNo}" name="reviewNo" readonly></td>
                </tr>
                <tr>
                    <th>별점</th>
                    <td><input type="number" value="${r.star}" name="star"></td>
                </tr>
                <tr>
                    <th>후기</th>
                    <td><textarea cols="50" rows="30" name="content">${r.content}</textarea></td>
                </tr>
                <tr>
                    <th>사진</th>
                    <td>
                        <c:if test="${empty r.img1}">
                        <input type="file" name="file" id="file" accept="image/*" multiple>
                        </c:if>
                        <c:if test="${not empty r.img1 }">
                            <img src="/highwayrest/${r.img1 }">
                            <input type="button" value="변경" class="edit" val="img1" class="img-thumbnail">
                            <input type="button" value="삭제" class="del" val="img1">
                        </c:if>
                        <c:if test="${not empty r.img2 }">
                            <img src="/highwayrest/${r.img2 }" class="img-thumbnail">
                            <input type="button" value="변경" class="edit" val="img2">
                            <input type="button" value="삭제" class="del" val="img2">
                        </c:if>
                        <c:if test="${not empty r.img3 }">
                            <img src="/highwayrest/${r.img3 }" class="img-thumbnail">
                            <input type="button" value="변경" class="edit" val="img3">
                            <input type="button" value="삭제" class="del" val="img3">
                        </c:if>
                    </td>
                </tr>
                </tr>
                <tr>
                    <th>수정하기</th>
                    <td><input type="submit" value="수정"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"/>
</body>
</html>
