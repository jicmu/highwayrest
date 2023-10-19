<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
<%--            <div class="modal-header">--%>
<%--                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--            </div>--%>
            <div class="modal-body">
                <div class="container">
                    <form action="${pageContext.request.contextPath}/master/home" method="post">
                        <div class="row">
                            <div class="col">
                                <select id="road" name="highway" class="form-select" aria-label="Default select example" onchange="roadMove(this.options[this.selectedIndex].value)">
                                    <option value="-1" selected>고속도로 선택</option>
                                </select>
                            </div>
                            <div class="col">
                                <select disabled id="upDown" class="form-select" aria-label="Default select example" onchange="going(this.options[this.selectedIndex].value)">
                                    <option value="-1">상행, 하행 선택</option>
                                    <option value="0">상행</option>
                                    <option value="1">하행</option>
                                </select>
                            </div>
                            <div class="col">
                                <select name="highwayrest" disabled id="rest" class="form-select" aria-label="Default select example">
                                    <option value="-1">휴게소 선택</option>
                                </select>
                            </div>
                        </div>
                        <input type="submit" value="버튼">
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