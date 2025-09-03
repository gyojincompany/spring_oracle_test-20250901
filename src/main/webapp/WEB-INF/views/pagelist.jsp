<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>자유 게시판</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background-color: #f8f9fa;
      padding: 40px;
    }

    .board-container {
      max-width: 900px;
      margin: 0 auto;
      background: #fff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0,0,0,0.05);
      overflow: hidden;
    }

    .board-header {
      padding: 20px 30px;
      font-size: 20px;
      font-weight: bold;
      border-bottom: 2px solid #007bff;
      display: flex;
      align-items: center;
    }

    .board-header::before {
      content: "🗂️";
      margin-right: 10px;
      font-size: 22px;
    }

    table.board {
      width: 100%;
      border-collapse: collapse;
    }

    table.board th,
    table.board td {
      padding: 15px;
      text-align: center;
      border-bottom: 1px solid #ddd;
    }

    table.board th {
      background-color: #f1f1f1;
      font-weight: bold;
    }

    table.board td a {
      color: #007bff;
      text-decoration: none;
    }

    table.board td a:hover {
      text-decoration: underline;
    }

    .pagination {
      display: flex;
      justify-content: center;
      padding: 20px;
      gap: 6px;
    }

    .pagination a {
      padding: 8px 14px;
      border: 1px solid #ddd;
      border-radius: 4px;
      color: #333;
      text-decoration: none;
      transition: all 0.2s;
      background: #fff;
    }

    .pagination a:hover {
      background-color: #007bff;
      color: white;
      border-color: #007bff;
    }

    .pagination a.active {
      background-color: #007bff;
      color: white;
      border-color: #007bff;
      pointer-events: none;
    }
  </style>
</head>
<body>

  <div class="board-container">
    <div class="board-header">자유 게시판</div>
    <table class="board">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${boardList}" var="board" varStatus="status">
        <tr>
          <td>${boardCount - (board.rnum -1) }</td>
          <td>
          	<a href="contentview2?bnum=${board.bnum}">${board.btitle }</a>
          	[${board.commentcount }]          	
          </td>
          <td>${board.memberDto.membername }</td>
          <td><fmt:formatDate value="${board.bdate }" pattern="yyyy-MM-dd HH:mm" /></td>
        </tr>
        </c:forEach>
        
      </tbody>
    </table>

    <div class="pagination">
      <c:if test="${pageNum > 1}">	
      <a href="pagelist?pageNum=1">&laquo;</a>
      <a href="pagelist?pageNum=${pageNum-1 }">&lsaquo;</a>
      </c:if>
      <c:forEach var="i" begin="${startPage }" end="${endPage }">
      	<c:choose>
      		<c:when test="${i == pageNum}">
      			<a href="#" class="active">${i}</a>
      		</c:when>
      		<c:otherwise>
      			<a href="pagelist?pageNum=${i}">${i}</a>
      		</c:otherwise>      		
      	</c:choose>	
      </c:forEach>
      <c:if test="${pageNum < totalPage}">
      <a href="pagelist?pageNum=${pageNum+1 }">&rsaquo;</a>
      <a href="pagelist?pageNum=${totalPage }">&raquo;</a>
      </c:if>
    </div>
  </div>

</body>
</html>

