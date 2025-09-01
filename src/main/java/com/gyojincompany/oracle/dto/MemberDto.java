package com.gyojincompany.oracle.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	private int membernum; //기본키(자동증가)
	private String memberid;
	private String memberpw;
	private String membername;
	private String memberdate; //가입일
	
	//private List<BoardDto> boardDtos; //Member:Board->1:N 관계
	
}
