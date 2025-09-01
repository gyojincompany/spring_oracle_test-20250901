<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>
	<h2>회원 로그인</h2>
	<hr>
	<form action="loginOk" method="post">
		아이디 : <input type="text" name="memberid"><br><br>
		비밀번호 : <input type="password" name="memberpw"><br><br>
		<input type="submit" value="로그인">
	</form>
	<c:if test="${not empty error}">
		<h3 style="color:red;">로그인 실패! 아이디와 비밀번호를 다시 확인하시기 바랍니다.</h3>
	</c:if>
</body>
</html>