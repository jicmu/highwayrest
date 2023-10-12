<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <title>Edit</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <style rel="stylesheet">
        a {
            margin-right: 30px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        const restChange = (value) => {
            if(value != -1) {
                $.ajax({
                    url: "http://data.ex.co.kr/openapi/restinfo/restBestfoodList?key=1835622522&type=json&numOfRows=100&stdRestCd=" + value,
                    type: "get",
                    datatype: "json",
                    success: function (result) {
                        console.log(result);
                        let list = result.list;
                        let foodlist = document.getElementById("foodlist");
                        let txt = "";
                        let cnt = 1;
                        foodlist.innerHTML = txt;
                        for (let item of list) {
                            txt += "<div class='form-check form-check-inline'>";
                            txt += "    <input name='food' class='form-check-input' type='checkbox' id='inlineCheckbox" + cnt + "' value='" + item.foodNm + "/" + item.stdRestCd + "/" + item.foodCost + "'>";
                            txt += "    <label class='form-check-label' for='inlineCheckbox" + cnt + "'>" + cnt + "/" + item.foodNm + "</label>";
                            txt += "</div>"
                            cnt++;
                        }
                        foodlist.innerHTML += txt;
                    }
                })
            }
        }
    </script>
</head>

<body>
    <div class="container" style="border: 1px solid black">
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                        <span class="navbar-brand mb-0 h1">
                            <a href="#">도로 추가하기</a> <a href="#">휴게소 추가하기</a>
                        </span>
            </div>
        </nav>
    </div>

    <div class="container">
        <div class="row">
            <form action="${pageContext.request.contextPath}/master/restfood" method="post">
                <div class="col">
                    <select id="restFood" name="highway" class="form-select" aria-label="Default select example" onchange="restChange(this.options[this.selectedIndex].value)">
                        <option value="-1" selected>휴게소 선택</option>
                        <c:forEach var="item" items="${list}">
                                    <option value="${item.svarCd}">${item.svarNm}</option>
                        </c:forEach>
                    </select>
                </div>
                <div id="foodlist" class="col">
                </div>
                <input type="submit" name="선택완료">
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" />
</body>

</html>
