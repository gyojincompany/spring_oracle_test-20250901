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
	public String join() {		
		
		return "memberjoin";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {		
		
		String mid = request.getParameter("memberid");
		String mpw = request.getParameter("memberpw");
		String mname = request.getParameter("membername");
		
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		
		int result = dao.memberjoinDao(mid, mpw, mname);
		System.out.println("가입성공여부 : " + result); //1이면 성공, 0이면 실패
		
		model.addAttribute("mid", mid);
		
		return "memberjoinOk";
	}
	
	

}
