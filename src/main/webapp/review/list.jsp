<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>후기 게시판</title>
    <script type="text/javascript">
        $(document).ready(function(){ //TODO 체크박스가 활성화되면 사진이 있는 후기만 띄우기
           $("#photoReview").change(function(){
              if($("#photoReview").is(":checked")){
                  //실행문
              }
           });
        });

        const delReview = (reviewNo) =>{
            let flag = confirm("후기를 삭제하면 다시 작성할 수 없습니다. 삭제하시겠습니까?");
            if(flag){
                location.href="${pageContext.request.contextPath}/highwayrest/controller/delReview?reviewNo="+reviewNo;
            }else{
                alert("후기 삭제가 취소되었습니다.");
            }
        }
    </script>
</head>
<body>
<div style="float:left">
    <!-- 정렬순 선택-->
    <select name="review">
        <option value="recent" selected>최신순</option>
        <option value="highrate" selected>별점 높은 순</option>
        <option value="lowrate" selected>별점 낮은 순</option>
    </select>
</div>

<label><input type="checkbox" id="photoReview">사진 후기만 보기</label>

<c:forEach var="r" items="list">
    <table border="1" id="f">
        <tr><th>회원</th><td>${r.memberNo}
            <c:if test="${sessionScope.loginId}==r.memberNo">
                <input type="button" value="삭제" onclick="delReview(${r.reviewNo})">
            </c:if>
        </td></tr>
        <tr><th>날짜</th><td>${r.wDate}</td></tr>
        <tr><th>별점</th><td>${r.star}</td></tr>
        <!--<tr><th>주문한 음식</th><td></td></tr>-->
        <c:if test="${r.img1}!=null">
            <tr><th>사진</th><td><img src="/highwayrest/${r.img1}"></td></tr>
        </c:if>
        <tr><th>후기</th><td>${r.content}</td></tr>
    </table>
</c:forEach>
</body>
</html>