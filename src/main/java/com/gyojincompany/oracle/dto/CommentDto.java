package com.gyojincompany.oracle.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private int cnum; //댓글 번호
	private int bnum; //원 게시판 글(부모글) 번호
	private String ccontent; //댓글 내용
	private String cwriter; //댓글 쓴사람 이름
	private Timestamp cdate; //댓글 등록일
	
}
