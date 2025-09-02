<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/test.css"><!-- css 불러오기 -->
<title>외부파일 불러오기</title>
</head>
<body>
	<h2><span class="test">이미지 불러오기</span></h2>
	<hr>
	<img src="${pageContext.request.contextPath}/resources/img/logo.png"><!-- 이미지 불러오기 -->
</body>
</html>