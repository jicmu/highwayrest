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
            <h2>제공 서비스, 사용 안내 등등...</h2>
        </div>
        <div class="row justify-content-center mt-5">
            <div class="d-grid gap-2 col-6 mx-auto">
              <button class="btn btn-primary" type="button" onclick="location.href='${pageContext.request.contextPath }/highwaylist'">시작하기</button>
            </div>
        </div>
    </div>
</body>
</html>