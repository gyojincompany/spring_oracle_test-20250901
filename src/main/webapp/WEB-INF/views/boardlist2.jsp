<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>심플 게시판</title>
  <link rel="stylesheet" href="resources/css/board.css">
</head>
<body>

  <div class="board-container">
    <h2>📋 자유 게시판</h2>

    <table class="board-table">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${boardList }" var="board" varStatus="status">
        <tr>
          <td>${boardCount - status.index }</td>
          <td>
          	<a href="#">${board.btitle }</a>
          </td>
          <td>${board.memberDto.membername }</td>
          <td>
          	<fmt:formatDate value="${board.bdate }" pattern="yyyy-MM-dd" />
          </td>
        </tr>
        </c:forEach>
        <!-- 실제 게시물 반복 -->
      </tbody>
    </table>

    <!-- 페이징 -->
    <div class="pagination">
      <a href="#" class="first">«</a>
      <a href="#" class="prev">‹</a>
      <a href="#" class="page active">1</a>
      <a href="#" class="page">2</a>
      <a href="#" class="page">3</a>
      <a href="#" class="page">4</a>
      <a href="#" class="page">5</a>
      <a href="#" class="next">›</a>
      <a href="#" class="last">»</a>
    </div>
  </div>

</body>
</html>
