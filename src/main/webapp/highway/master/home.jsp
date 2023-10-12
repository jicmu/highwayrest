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
        let routeCd = "";
        $(document).ready(function() {
            $.ajax({
                url: "http://data.ex.co.kr/openapi/roadEtcInfo/spinRouteList?key=1835622522&type=json&useYn=Y",
                type: "get",
                datatype: "json",
                success: function(res) {
                    console.log("전국 고속도로 개수: " + res.count);
                    let road = document.getElementById("road");
                    let txt = "";
                    for(let list of res.list) {
                        let info = list.routeNo + "/" + list.routeCd + "/" + list.routeNm;
                        txt += "<option value=" + info + ">" + list.routeNm + "</option>"
                    }
                    road.innerHTML += txt;

                    road.options[0].disabled = "disabled";
                }
            })
        })

        // 고속도로 선택시
        const roadMove = (value) => {
            let upDown = document.getElementById("upDown");
            let rest = document.getElementById("rest");

            rest.disabled = true;
            rest.innerHTML = "<option value='-1'>휴게소 선택</option>";

            upDown.disabled = false;
            upDown.options[0].selected = true;
            upDown.options[0].disabled = "disabled";

            routeCd = value.split("/")[1];

            console.log("routeCd: " + routeCd);
        }

        // 상,하행 선택시
        const going = (value) => {
            console.log("선택된 값: " + value);

            $.ajax({
                url: "http://data.ex.co.kr/openapi/restinfo/hiwaySvarInfoList?key=1835622522&type=json&svarGsstClssCd=0&routeCd=" + routeCd +"&gudClssCd=" + value,
                type: "get",
                datatype: "json",
                success: function(result) {
                    console.log("성공!");
                    console.log(result);
                    let list = result.list;
                    let rest = document.getElementById("rest");
                    rest.innerHTML = "<option value='-1'>휴게소 선택</option>";
                    let txt = "";
                    for(let item of list) {
                        let restloc = item.svarNm;
                        if(restloc.indexOf("쉼터") == -1){
                            console.log(item);
                            let value = item.svarCd + "/" + item.svarNm + "/" +item.gudClssCd + "/" + item.rprsTelNo + "/" + item.routeCd + "/" + item.svarAddr;
                            console.log("value: " + value);
                            txt += "<option value=" + value +">" + item.svarNm + "</option>";
                        }
                    }
                    rest.innerHTML += txt;
                    rest.disabled = false;
                }, error: function() {
                    console.log("휴게소 정보 불러오기 실패!");
                }
            })
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" />
</body>

</html>
