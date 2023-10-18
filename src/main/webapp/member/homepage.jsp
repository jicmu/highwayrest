<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="containor mt-5">
        <div class="row justify-content-center">
            <div class="d-grid gap-2 col-6 mx-auto">
                <h1>고속 주문</h1>
                <hr>
                <div class="mt-3 text-start">
                    <h5>고속도로 휴게소 음식 예약 서비스</h5>
                    <h5>고속도로 / 휴게소 검색으로 음식 주문도 빠르고 간편하게!</h5>
                    <h5></h5>
                </div>
                <hr>
                <button class="btn btn-secondary mt-3" type="button" onclick="location.href='${pageContext.request.contextPath }/highwaylist'">시작하기</button>
            </div>
        </div>
    </div>
</body>
</html>