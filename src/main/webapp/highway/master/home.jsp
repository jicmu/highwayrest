<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Edit</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../common/css/common.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script defer src="<c:url value="/common/js/highway.js" /> " crossorigin="anonymous"></script>
    <script defer src="<c:url  ><value>/common/js/highwayfood.js</value></c:url> " crossorigin="anonymous"></script>
</head>

<body>
    <div id="wrapper">
        <%@ include file="/common/header.jsp"%>

        <div class="container">
            <form action="${pageContext.request.contextPath}/master/home" method="post">
                <div class="row">
                    <div class="col">
                        <select id="road" name="highway" class="form-select" aria-label="Default select example" onchange="roadMove(this.options[this.selectedIndex].value)">
                            <option value="-1" selected>고속도로 선택</option>
                        </select>
                    </div>
                    <div class="col">
                        <select disabled id="upDown" class="form-select" aria-label="Default select example" onchange="going(this.options[this.selectedIndex].value)">
                            <option value="-1">상행, 하행 선택</option>
                            <option value="0">상행</option>
                            <option value="1">하행</option>
                        </select>
                    </div>
                    <div class="col">
                        <select name="highwayrest" disabled id="rest" class="form-select" aria-label="Default select example">
                            <option value="-1">휴게소 선택</option>
                        </select>
                    </div>
                </div>
                <input type="submit" value="버튼">
            </form>
        </div>
    </div>
    <%@ include file="/common/footer.jsp"%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" />
</body>

</html>
