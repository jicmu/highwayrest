<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e8cd91d76c21a46aceb30ba9e7ec1e1"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col mt-3">
                <form class="d-flex" role="search" action="#" method="post">
                    <input type="hidden" name="searchType" val="2">
                    <input class="form-control me-2" type="search" name="searchWord" placeholder="휴게소명">
                    <button class="btn btn-outline-success" type="submit">검색</button>
                </form>
            </div>
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
        <div class="mt-3">
            <h2>${r.routeCd } 휴게소 목록</h2>
        </div>
        <div class="mt-3">
            <!-- <c:forEach var="r" items="${list }"> -->
                <a href="#?restCd=${r.restCd }" class="list-group-item list-group-item-action">${r.restNm }</a>
            <!-- </c:forEach> -->
        </div>
        <nav class="mt-3" aria-label="Page navigation">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>
        </div>
    </div>
</body>
</html>