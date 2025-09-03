package com.gyojincompany.oracle.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto { 
	
	private int commentcount; //댓글 갯수
	private int rnum; //rownum 값 필드
	private int bnum; //글번호(기본키)-자동증가
	private String btitle; //글 제목
	private String bcontent; //글 내용
	private String bwriter; //글 쓴이 아이디
	private int bhit; //글 조회수
	private Timestamp bdate; //글 등록일(날짜 타입)
	
	//BoarDto : MemberDto -> 1:1 관계
	private MemberDto memberDto;
	
	//BoarDto : CommentDto -> 1:N 관계
	private List<CommentDto> commentDtos;
}
