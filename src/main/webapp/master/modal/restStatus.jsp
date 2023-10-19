<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div class="modal fade" id="exampleModal3" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container-sm text-center">
                    <c:forEach var="rest" items="${restmasterlist}">
                        <div class="row">
                            <div class="col col-lg-6">
                                <span>${rest.name}</span>
                            </div>
<%--                            <div class="col-md-auto">--%>
<%--                            </div>--%>
                            <div class="col col-lg-6">
                                <c:choose>
                                    <c:when test="${rest.status eq 0}">
                                        <div class="form-check form-switch">
                                            <input class="form-check-input" type="checkbox" id="${rest.id}" name="darkmode" value="yes" onclick="changeToggle('${rest.id}')">
                                            <label id="${rest.id}_label" class="form-check-label" for="${rest.id}">폐업</label>
                                        </div><br/>
                                    </c:when>
                                    <c:when test="${rest.status eq 1}">
                                        <div class="form-check form-switch">
                                            <input class="form-check-input" type="checkbox" id="${rest.id}" name="darkmode" value="yes" onclick="changeToggle('${rest.id}')" checked>
                                            <label id="${rest.id}_label" class="form-check-label" for="${rest.id}">영업</label>
                                        </div><br/>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>