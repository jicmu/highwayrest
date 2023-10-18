<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e8cd91d76c21a46aceb30ba9e7ec1e1&libraries=services,clusterer,drawing"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            var svarNm = $("#svarNm").val();
            var svarAddr = $("#svarAddr").val();

            var mapContainer = document.getElementById('map'),
            mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 6
            };

            var map = new kakao.maps.Map(mapContainer, mapOption);

            var mapTypeControl = new kakao.maps.MapTypeControl();

            map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

            var zoomControl = new kakao.maps.ZoomControl();
            map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

            var geocoder = new kakao.maps.services.Geocoder();

            console.log(svarNm);
            console.log(svarAddr);

            geocoder.addressSearch(svarAddr, function(result, status) {

                if (status === kakao.maps.services.Status.OK) {

                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });

                    var infowindow = new kakao.maps.InfoWindow({
                        content: '<div style="width:150px;text-align:center;padding:5px 0;">' + svarNm + '</div>'
                    });
                    infowindow.open(map, marker);

                    map.setCenter(coords);
                }
            });
        });
    </script>
</head>
<body>
    <div class="container">

        <div class="row justify-content-center mt-3">
            <ul class="nav justify-content-center nav-pills nav-fill">
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath }/foodlist?restNo=${highwayrest.svarCd }">메뉴</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/listReview?svarCd=${highwayrest.svarCd }">후기</a>
              </li>
            </ul>
            <hr>
        </div>

        <div class="row mt-3">
            <div class="col-6">
                <div id="map" style="width:450px;height:500px;"></div>
            </div>
            <div class="col-6">
                <h2>${highwayrest.svarNm } 상세 정보</h2>
                <form style="display:none">
                    <input type="hidden" id="svarNm" value="${highwayrest.svarNm}">
                    <input type="hidden" id="svarAddr" value="${highwayrest.svarAddr}">
                </form>
                <table class="table mt-5">
                    <tr><th class="table-light">주소</th><td>${highwayrest.svarAddr }</td></tr>
                    <tr><th class="table-light">대표전화번호</th><td>${highwayrest.rprsTelNo }</td></tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>