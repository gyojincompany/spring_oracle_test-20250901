package com.gyojincompany.oracle.dto;

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
	private String memebername;
	private String memberdate; //가입일
	
}
