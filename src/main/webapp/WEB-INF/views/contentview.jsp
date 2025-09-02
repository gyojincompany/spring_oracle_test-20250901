<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<div class="container">
    <!-- 게시글 상세 -->
    <div class="post-detail">
        <h2 class="post-title">${post.btitle}</h2>
        <div class="post-meta">
            <span>작성자: ${post.memberDto.membername}</span>
            <span>작성일: ${post.bdate}</span>
        </div>
        <div class="post-content">
            ${post.bcontent}
        </div>
    </div>

    <!-- 댓글 입력 -->
    <div class="comment-section">
        <h3>댓글 작성</h3>
        <form action="commentwrite" method="post" class="comment-form">
            <input type="hidden" name="bnum" value="${post.bnum}"> <!-- 원글(부모글) 번호 -->
            <input type="text" name="cwriter" placeholder="작성자 이름" required>
            <textarea name="ccontent" rows="4" placeholder="댓글을 입력하세요..." required></textarea>
            <button type="submit">댓글 등록</button>
        </form>
    </div>

    <!-- 댓글 목록 -->
    <div class="comment-list">
        <h3>댓글 목록</h3>
        <c:forEach var="comment" items="${post.commentDtos}">          
           
            <div class="comment-item">
                <div class="comment-meta">
                    <strong>${comment.cwriter}</strong> | ${comment.cdate}
                </div>
                <div class="comment-content">
                    ${comment.ccontent}
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
