package com.gyojincompany.oracle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.oracle.dao.BoardDao;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/bwrite")
	public String bwrite(HttpSession session, Model model) { //글쓰기 양식(폼) 페이지를 띄우는 요청
		
		String sid = (String) session.getAttribute("sessionId");
		if(sid == null) { //로그인하지 않은 상태
			model.addAttribute("msg", "로그인한 회원만 글쓰기가 가능합니다.");
			model.addAttribute("url", "login");
			
			return "alert/alert";
		}
		
		return "write_form";
	}
	
	@RequestMapping(value = "/bwriteOk")
	public String bwriteOk(HttpServletRequest request, Model model) { //글쓰기 양식(폼)에서 유저가 쓴 글 데이터를 가져다가 DB에 넣는 요청
		
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bwriter = request.getParameter("bwriter");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		boardDao.boardWriteDao(btitle, bcontent, bwriter); //글쓰기		
		
		return "redirect:blist";
	}

}
