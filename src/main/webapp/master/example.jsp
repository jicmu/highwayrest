<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <title>업로드</title>
</head>
<body>
    <div>
        <form action="${pageContext.request.contextPath}/master/upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file1">
            <input type="file" name="file2">
            <input type="file" name="file3">
            <input type="submit" value="ㄱㄱ">
        </form>
    </div>
</body>
</html>
