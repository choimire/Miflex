<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="signupForm" action="signup?${_csrf.parameterName}=${_csrf.token }" method="post" enctype="multipart/form-data">
	<input type="text" name="test" value="전송입니다.">
	<input type="file" name="file">
	<input type="submit" value="전송">
</body>
</html>