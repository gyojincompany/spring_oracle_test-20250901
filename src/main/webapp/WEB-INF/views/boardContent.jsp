<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
</head>
<body>
	<h2>글 내용 보기 및 수정</h2>
	<hr>
	<form action="boardmodify">
		제목 : <input type="text" name="btitle" value="${boardDto.btitle }"><br><br>
		내용 : <textarea rows="10" cols="45" name="bcontent">${boardDto.bcontent }</textarea><br><br>
		글쓴이 : <input type="text" name="membername" value="${boardDto.memberDto.membername }" readonly><br><br>
		조회수 : ${boardDto.bhit}<br><br>
		등록일 : <fmt:formatDate value="${boardDto.bdate }" pattern="yyyy-MM-dd HH:mm:ss" /><br><br>
		<input type="hidden" name="bnum" value="${boardDto.bnum }">
		<input type="submit" value="수정">
		<input type="button" value="글목록" onclick="javascript:window.location.href='blist'">
	</form>
	
</body>
</html>