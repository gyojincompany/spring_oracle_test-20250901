package com.gyojincompany.oracle.dao;

import java.util.List;

import com.gyojincompany.oracle.dto.BoardDto;


public interface BoardDao {
	
	public void boardWriteDao(String btitle, String bcontent, String bwriter); //게시판 글쓰기
	public List<BoardDto> boardListDao(); //게시판 글 목록 보기->페이징 안됨
	public int AllBoardCountDao(); //게시판 모든 글 갯수 가져오기
	public int boardDeleteDao(String bnum); //게시판 글 삭제
	public BoardDto contentViewDao(String bnum); //글 번호로 해당 글 레코드 가져오기
	public int boardModifyDao(String bnum, String btitle, String bcontent); //글 번호로 해당 글 수정하기
	public void updateHitDao(String bnum); //해당 글의 조회수 1증가
	public List<BoardDto> pageBoardListDao(int startRow, int endRow); //페이징 된 게시판 글 목록 보기
	
	//댓글 작성
	public void commentWriteDao(String bnum, String ccontent, String cwriter);
	public BoardDto contentCommentViewDao(String bnum); //댓글을 포함한 게시글 내용 가져오기
	

}
