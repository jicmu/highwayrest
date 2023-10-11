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
        $(document).ready(function() {
            $.ajax({
                url: "http://data.ex.co.kr/openapi/roadEtcInfo/spinRouteList?key=1835622522&type=json&useYn=Y",
                type: "get",
                datatype: "json",
                success: function(res) {
                    console.log("전국 고속도로 개수: " + res.count);
                    for(let list of res.list) {
                        console.log(list);
                    }
                }
            })
        })
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
        <div class="col">
            <select class="form-select" aria-label="Default select example">
                <option selected>Open this select menu</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
            </select>
        </div>
        <div class="col">
            <select class="form-select" aria-label="Default select example">
                <option selected>Open this select menu</option>
                <option value="1">4</option>
                <option value="2">5</option>
                <option value="3">6</option>
            </select>
        </div>
        <div class="col">
            <select class="form-select" aria-label="Default select example">
                <option selected>Open this select menu</option>
                <option value="1">7</option>
                <option value="2">8</option>
                <option value="3">9</option>
            </select>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" />
</body>
</html>
