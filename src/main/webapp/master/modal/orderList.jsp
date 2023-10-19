<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="exampleModal5" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">

            <div class="modal-body">
                <div class="container">
                    <form action="" method="post">
                        <div class="row">
                            <div class="col">
                                <select name="highway" class="form-select" aria-label="Default select example" onchange="restOrder(this.options[this.selectedIndex].value)">
                                    <option value="-1" selected>찾을 휴게소 선택</option>
                                    <c:forEach var="item" items="${list}">
                                        <option value="${item.svarCd}">${item.svarNm}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div><br/>
                        <div class="row">
                            <div id="cl" class="col">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>