<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e8cd91d76c21a46aceb30ba9e7ec1e1"></script>
    <script>
        var mapContainer = document.getElementById('map'),
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667),
            level: 3
        };

        var map = new kakao.maps.Map(mapContainer, mapOption);

        var geocoder = new kakao.maps.services.Geocoder();

        geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {

            if (status === kakao.maps.services.Status.OK) {

                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });

                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                });
                infowindow.open(map, marker);

                map.setCenter(coords);
            }
        });
    </script>
</head>
<body>
    <div class="container">
        <div class="row">
            <ul class="nav justify-content-center">
              <li class="nav-item">
                <a class="nav-link" href="/highwayrest/foodlist?stdRestCd=${highwayrest.svarCd }">메뉴</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">후기</a>
              </li>
            </ul>
            <hr>
        </div>
        <div class="row mt-3">
            <h2>${highwayrest.svarNm } 상세 정보</h2>
        </div>
        <div class="row mt-3">
            <div class="col">
                <div id="map" style="width:300px;height:200px;"></div>

            </div>
        </div>
        <div class="row mt-3">
            <div class="col-10">
                <table>
                    <tr><th>주소</th><td>${highwayrest.svarAddr }</td></tr>
                    <tr><th>대표전화번호</th><td>${highwayrest.rprsTelNo }</td></tr>
                </table>
                <div class="d-none" id="svarNm">${highwayrest.svarNm }</div>
                <div class="d-none" id="svarAddr">${highwayrest.svarAddr }</div>
            </div>
        </div>
    </div>
</body>
</html>