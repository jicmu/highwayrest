<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
<%--            <div class="modal-header">--%>
<%--                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--            </div>--%>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <form action="${pageContext.request.contextPath}/master/restfood" method="post">
                            <div class="col">
                                <select id="restFood" name="highway" class="form-select" aria-label="Default select example" onchange="restChange(this.options[this.selectedIndex].value)">
                                    <option value="-1" selected>휴게소 선택</option>
                                    <c:forEach var="item" items="${list}">
                                        <option value="${item.svarCd}">${item.svarNm}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div id="foodlist" class="col">
                            </div>
                            <input type="submit" name="선택완료">
                        </form>
                    </div>
                </div>
            </div>
<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                <button type="button" class="btn btn-primary">Save changes</button>--%>
<%--            </div>--%>
        </div>
    </div>
</div>