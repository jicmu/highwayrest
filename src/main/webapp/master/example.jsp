<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
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
