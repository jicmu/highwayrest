<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>후기 게시판</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
           $("#photoReview").change(function(){
              if($("#photoReview").is(":checked")){
                  if(f.img.value==null){
                      f.style.display='none';
                  }
              }
           });

            $("#sort").click(function(){//JQUERY를 이용해서 정렬을 시도.....서버에서 할지 AJAX할지
                $.ajax({
                    url:"${pageContext.request.contextPath}/review/listReview",
                    type:"post",
                    // dataType:"",
                    success: function (res) {

                    }
                });
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

        // const sortR = () => {
        //     let sort = document.getElementById("sort");
        //     if(sort.value){
        //         console.log(sort.options[sort.selectedIndex].value);
        //     }
        // }


    </script>
</head>
<body>
<div style="float:right">
    <select name="sort" id="sort" onclick="sortR()">
        <option value="sortRecent" selected>최신순</option>
        <option value="sortHighRate">별점 높은 순</option>
        <option value="sortLowRate">별점 낮은 순</option>
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
        <!-- TODO 업로드한 사진을 모두 출력할 수 있게 한다.-->
        <c:if test="${r.img1}!=null">
            <tr><th>사진</th><td id="img"><img src="/highwayrest/${r.img1}"></td></tr>
        </c:if>
        <tr><th>후기</th><td>${r.content}</td></tr>
    </table>
</c:forEach>
</body>
</html>