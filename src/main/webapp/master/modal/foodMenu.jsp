<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="exampleModal4" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">

            <div class="modal-body">
                <div class="container">
                    <form action="" method="post">
                        <div class="row">
                            <div class="col">
                                <select id="restFood" name="highway" class="form-select" aria-label="Default select example" onchange="selectRest(this.options[this.selectedIndex].value)">
                                    <option value="-1" selected>휴게소 선택</option>
                                    <c:forEach var="item" items="${list}">
                                        <option value="${item.svarCd}">${item.svarNm}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div><br/>
                    </form>
                    <div id="menuList" class="row">
<%--                        <div id="menuList">--%>
<%--                        </div>--%>
                    </div>
                    </form>
                </div>
            </div>

<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                <button type="button" class="btn btn-primary">Save changes</button>--%>
<%--            </div>--%>
        </div>
    </div>
</div>