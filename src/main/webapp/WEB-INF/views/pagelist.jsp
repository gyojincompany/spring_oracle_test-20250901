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
          <td>${boardCount - status.index }</td>
          <td><a href="contentview?bnum=${board.bnum}">${board.btitle }</a></td>
          <td>${board.memberDto.membername }</td>
          <td><fmt:formatDate value="${board.bdate }" pattern="yyyy-MM-dd HH:mm" /></td>
        </tr>
        </c:forEach>
        
      </tbody>
    </table>

    <div class="pagination">
      <a href="#">&laquo;</a>
      <a href="#">&lsaquo;</a>
      <a href="#" class="active">1</a>
      <a href="#">2</a>
      <a href="#">3</a>
      <a href="#">4</a>
      <a href="#">5</a>
      <a href="#">&rsaquo;</a>
      <a href="#">&raquo;</a>
    </div>
  </div>

</body>
</html>

