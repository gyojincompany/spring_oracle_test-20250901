package com.gyojincompany.oracle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {		
		
		String error = request.getParameter("error");
		
		if(error != null) {
			model.addAttribute("error", error);
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, Model model, HttpSession session) {		
		
		String mid = request.getParameter("memberid");
		String mpw = request.getParameter("memberpw");
		
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		
		int checkFlag = dao.memberLoginDao(mid, mpw); //로그인 성공->1, 실패->0
		
		if(checkFlag == 1) { //로그인하려는 아이디와 비번이 존재->성공
			session.setAttribute("sessionId", mid); //로그인성공->세션 sessionId값 설정
			
			model.addAttribute("msg", "로그인 성공. 회원님 반갑습니다.");
			model.addAttribute("url", "loginSuccess");
			
			return "alert/alert";
		} else { //로그인 실패		
			model.addAttribute("msg", "로그인 실패. 아이디 또는 비밀번호가 잘못 되었습니다.");
			model.addAttribute("url", "login");
			
			
			return "alert/alert";		
		}
		
	}
	
	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess() {
		return "loginSuccess";
	}
	
	

}
