<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>후기 게시판</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#photoReview").change(function () {
                if ($("photoReview").is(":checked")) {
                    if (f.img.value == null) {
                        f.style.display = 'none';
                    }
                }
            });
        });

        $(function () {
            $("#sort").change(function () {
                let sort = $(this).val();
                location.href="/highwayrest/sortReview?sort=" + sort + "&svarCd=${svarCd}";
            });
        });

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
    <%@ include file="/common/header.jsp" %>
    <div class="container">
        <div style="float:right">
            <select name="sort" id="sort">
                <option value="sortRecent" selected>최신순</option>
                <option value="sortHighRate">별점높은순</option>
                <option value="sortLowRate">별점낮은순</option>
            </select>
        </div>

        <label><input type="checkbox" id="photoReview">사진 후기만 보기</label>
        <br/>

        <c:forEach var="r" items="${list}">
            <table border="1" id="f">
                <!-- 작성자와 로그인한 회원이 동일할 때-->
                <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                    <input type="button" value="수정" class="btn btn-outline-secondary" onclick="editReview(${r.reviewNo})">
                    <input type="button" value="삭제" class="btn btn-outline-secondary" onclick="delReview(${r.reviewNo})">
                </div>
                        <div class="mb-3 row">
                            <label for="member" class="col-sm-2 col-form-label">회원</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control-plaintext" id="member" value="${r.memberNo}">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="WDate" class="col-sm-2 col-form-label">작성일</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control-plaintext" id="Wdate" value="${r.WDate}">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="star" class="col-sm-2 col-form-label">별점</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control-plaintext" id="star" value="${r.star}">
                            </div>
                        </div>

                <c:if test="${empty r.img1}">
                    <label for="star" class="col-sm-2 col-form-label">사진</label>
                    <div>
                        <!--TODO 절대 경로?를 지정해야 할까? 경로를 어떻게 해야할 지 월요일에 다시 물어 보자.-->
                        <img src="${r.img1}" class="img-thumbnail">
                    </div>
                    <c:if test="${empty r.img2}">
                        <div>
                            <img src="/highwayrest/${r.img2}" class="img-thumbnail">
                        </div>
                    </c:if>
                    <c:if test="${empty r.img3}">
                        <div>
                            <img src="/highwayrest/${r.img3}" class="img-thumbnail">
                        </div>
                    </c:if>
                </c:if>

                        <div class="mb-3 row">
                            <label for="content" class="col-sm-2 col-form-label">후기</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control-plaintext" id="content" value="${r.content}">
                            </div>
                        </div>
            </table>
        </c:forEach>
    </div>
</div>
<%--    <%@ include file="footer.jsp" %>--%>
<%@ include file="/common/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"/>
</body>
</html>