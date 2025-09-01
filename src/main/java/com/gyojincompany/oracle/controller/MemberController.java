package com.gyojincompany.oracle.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.oracle.dao.MemberDao;

@Controller
public class MemberController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/join")
	public String join(HttpServletRequest request, Model model) {		
		
		String error = request.getParameter("error");
		
		if(error != null) {
			model.addAttribute("error", error);
		}
		
		return "memberjoin";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {		
		
		String mid = request.getParameter("memberid");
		String mpw = request.getParameter("memberpw");
		String mname = request.getParameter("membername");
		
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		
		int checkFlag = dao.memeberidCheckDao(mid); //아이디가 이미 DB에 존재하는지 확인->존재하면 1, 아니면 0
		
		if(checkFlag == 1) { //가입하려는 아이디가 이미 존재->가입 불가
			model.addAttribute("msg", "이미 가입된 아이디 입니다. 다시 가입해 주세요.");
			model.addAttribute("url", "join");
			
			return "alert/alert";
		} else {
			int result = dao.memberjoinDao(mid, mpw, mname);
			System.out.println("가입성공여부 : " + result); //1이면 성공, 0이면 실패
			model.addAttribute("mid", mid);
			
			return "memberjoinOk";
		}
	}
	
	

}
