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
                location.href = "/highwayrest/sortReview?sort=" + sort + "&svarCd=${svarCd}";
            });
        });

        const editReview = (reviewNo) => {
            location.href = "/highwayrest/editReview?svarCd=${svarCd}&reviewNo=" + reviewNo;
        }

        const delReview = (reviewNo) => {
            let flag = confirm("후기를 삭제하면 다시 작성할 수 없습니다. 삭제하시겠습니까?");
            if (flag) {
                location.href = "/highwayrest/delReview?svarCd=${svarCd}&reviewNo=" + reviewNo;
            } else {
                alert("삭제가 취소되었습니다.");
            }
        }

    </script>
</head>
<body>
    <div class="container">
        <div style="float:right">
            <select name="sort" id="sort">
                <option value="sortRecent" <c:if test="${param.sort eq 'sortRecent'}">selected</c:if>>최신순</option>
                <option value="sortHighRate" <c:if test="${param.sort eq 'sortHighRate'}">selected</c:if>>별점높은순</option>
                <option value="sortLowRate" <c:if test="${param.sort eq 'sortLowRate'}">selected</c:if>>별점낮은순</option>
            </select>
        </div>

        <label><input type="checkbox" id="photoReview">사진 후기만 보기</label>
        <br/>

        <c:forEach var="r" items="${list}">
            <table border="1" id="f">
<%--                <c:if test="${sessionScope.loginId == r.memberNo }">--%>
                <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                    <input type="button" value="수정" class="btn btn-outline-secondary"
                           onclick="editReview(${r.reviewNo})">
                    <input type="button" value="삭제" class="btn btn-outline-secondary"
                           onclick="delReview(${r.reviewNo})">
                </div>
<%--                </c:if>--%>
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

                <c:if test="${r.img1 != null}">
                    <div class="mb-3 row">
                        <label for="img1" class="col-sm-2 col-form-label">사진</label>
                        <img src="${r.img1}" class="rounded" id="img1" style="height:100px;width: 100px ">
                        <c:if test="${r.img2 != null}">
                            <img src="${r.img2}" class="rounded" id="img2" style="height:100px;width: 100px ">
                        </c:if>
                        <c:if test="${r.img3 != null}">
                            <img src="${r.img3}" class="rounded" id="img3" style="height:100px;width: 100px ">
                        </c:if>
                    </div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"/>
</body>
</html>