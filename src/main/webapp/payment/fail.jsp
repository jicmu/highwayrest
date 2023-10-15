<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <title>결제 실패</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/common/css/common.css" />" type="text/css">
    <link rel="stylesheet" href="<c:url value='/common/css/payment.css' />" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>

<body>
    <div id="wrapper">
        <%@ include file="/common/header.jsp"%>
        <div class="container main">
            <div class="text-center">
              결제에 실패했습니다.<br>
              <button class="btn btn-primary btn-history">뒤로가기</button>
            </div>
        </div>
    <%--    <%@ include file="footer.jsp" %>--%>
        <%@ include file="/common/footer.jsp"%>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
      document.querySelector(".btn-history").addEventListener("click", () => {
        history.go(-1);
      });
    </script>
</body>

</html>
