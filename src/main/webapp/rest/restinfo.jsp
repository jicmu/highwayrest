<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e8cd91d76c21a46aceb30ba9e7ec1e1"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <ul class="nav justify-content-center">
              <li class="nav-item">
                <a class="nav-link" href="#">메뉴</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">후기</a>
              </li>
            </ul>
            <hr>
        </div>
        <div class="row mt-3">
            <h2>OO휴게소 상세 정보</h2>
        </div>
        <div class="row mt-3">
            <div class="col">
                <div id="map" style="width:300px;height:200px;"></div>
                <script>
                    var container = document.getElementById('map');
                    var options = {
               	    	center: new kakao.maps.LatLng(33.450701, 126.570667),
        	   	        level: 3
                    };

                    var map = new kakao.maps.Map(container, options);
                </script>
            </div>
        </div>
        <div class="row mt-3">
            <table>
                <tr><th>주소</th><td></td></tr>
                <tr><th>소형차주차대수</th><td></td></tr>
                <tr><th>대형차주차대수</th><td></td></tr>
                <tr><th>장애인주차대수</th><td></td></tr>
                <tr><th>대표전화번호</th><td></td></tr>
            </table>
        </div>
    </div>
</body>
</html>