<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e8cd91d76c21a46aceb30ba9e7ec1e1&libraries=services,clusterer,drawing"></script>
    <script>
        window.onload = () => {
            document.getElementById("svarNm").onchange = function() {
                var searchWord = document.getElementById("searchWord");
                var svarNm = document.getElementById("svarNm").value;
                searchWord.value = svarNm;
            };

            document.getElementById("direction").onchange = function() {
                let direction = document.getElementById("direction").value;
                let url = "${pageContext.request.contextPath }/restsearch?direction=" + direction + "&routeCd=${highway.routeCd}";
                location.replace(url);
            };

            var mapContainer = document.getElementById('map'),
            mapOption = {
                center: new kakao.maps.LatLng(36, 128),
                level: 13
            };

            var map = new kakao.maps.Map(mapContainer, mapOption);

            var mapTypeControl = new kakao.maps.MapTypeControl();

            map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

            var zoomControl = new kakao.maps.ZoomControl();
            map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

            var geocoder = new kakao.maps.services.Geocoder();

            var positions = [];

            <c:forEach var="r" items="${list }">
                var obj = new Object();
                obj.restNm = '${r.svarNm }';
                obj.restAddr = '${r.svarAddr}';
                obj.svarCd = '${r.svarCd}';
                obj.gudClssCd = '${r.gudClssCd}';

                positions.push(obj);
            </c:forEach>

            console.log(positions)

            positions.forEach(function(position){
                geocoder.addressSearch(position.restAddr, function(result, status) {

                    if (status === kakao.maps.services.Status.OK) {

                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                        var marker = new kakao.maps.Marker({
                            map: map,
                            position: coords,
                            clickable: true
                        });

                        var iwContent = '<div style="width:150px;text-align:center;padding:5px 0;"><a href="${pageContext.request.contextPath}/restinfo?svarCd=' + position.svarCd + '">' + position.restNm + '</a></div>',
                            iwRemovable = true;

                        var infowindow = new kakao.maps.InfoWindow({
                            content: iwContent,
                            removable: iwRemovable
                        });

                        kakao.maps.event.addListener(marker, 'click', function() {
                            infowindow.open(map, marker);
                        });
                    }
                });
            });
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col mt-3">
                <form class="d-flex" id="f" role="search" action="${pageContext.request.contextPath }/restsearch" method="post">
                    <input type="hidden" name="searchType" value="2">
                    <input type="hidden" name="memberNo" value="${sessionScope.loginNum }">
                    <input type="hidden" id="searchWord" name="searchWord" value="">
                    <input class="form-control me-2" type="search" id="svarNm" name="svarNm" placeholder="휴게소명">
                    <input class="btn btn-outline-success" type="submit" value="검색"></button>
                </form>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-6">
                <div id="map" style="width:450px;height:500px;"></div>
            </div>
            <div class="col-6">
                <h2>${highway.routeNm } 휴게소 목록</h2>
                <div class="mt-3">
                    <table class="table table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>휴게소명</th>
                                <th>
                                    <select id="direction" class="form-select form-select-sm">
                                        <option selected>방향선택</option>
                                        <option value="0">상행</option>
                                        <option value="1">하행</option>
                                    </select>
                                </th>
                            </tr>
                        </thead>
                        <c:forEach var="r" items="${list }">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath }/restinfo?svarCd=${r.svarCd }" class="list-group-item list-group-item-action">${r.svarNm }</a>
                            </td>
                            <c:if test="${r.gudClssCd == 0}">
                                <td>상행</td>
                            </c:if>
                            <c:if test="${r.gudClssCd == 1}">
                                <td>하행</td>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
                <nav class="mt-3" aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</body>
</html>