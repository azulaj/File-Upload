<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Hello World!</h2>
<form action="uploaddb" method="post" enctype="multipart/form-data">
<input type="file" name="file" multiple="multiple">
<input type="submit"><br><br>
To upload into folder <a href="index.jsp">click here</a>
</form>
</body>
</html>