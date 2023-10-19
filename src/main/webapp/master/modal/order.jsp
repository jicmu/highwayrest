<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col" style="display: block">
    <div class="card" style="">
        <div class="card-body">
            <h5 class="card-title">${orderlist.id}</h5>
            <c:forEach var="list" items="${orderlist.list}">
                <p class="card-text">${list.menu} / ${list.pay}</p>
            </c:forEach>
            <a href="#" class="btn btn-primary">삭제</a>
        </div>
    </div>
</div>
