<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	<form action="joinOk" method="post">
		아이디 : <input type="text" name="memberid"><br><br>
		비밀번호 : <input type="text" name="memberpw"><br><br>
		이름 : <input type="text" name="membername"><br><br>
		<input type="submit" value="회원가입">
	</form>
	<c:if test="${not empty error}">
		<h3 style="color:red;">다시 가입란을 작성하시기 바랍니다.</h3>
	</c:if>
</body>
</html>