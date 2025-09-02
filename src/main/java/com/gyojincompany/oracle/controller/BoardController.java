package com.gyojincompany.oracle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.oracle.dao.BoardDao;
import com.gyojincompany.oracle.dto.BoardDto;

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
	
	@RequestMapping(value = "/blist")
	public String blist(Model model) {
		
		//System.out.println("blist->boardList");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		List<BoardDto> boardDtos = boardDao.boardListDao(); //모든 글 가져오기(조인 테이블)
		model.addAttribute("boardList", boardDtos);
		
		model.addAttribute("boardCount", boardDao.AllBoardCountDao()); //모든 글 갯수 전달하기
		
		return "boardList";
	}
	
	@RequestMapping(value = "/blist2")
	public String blist2(Model model) {
		
		//System.out.println("blist->boardList");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		List<BoardDto> boardDtos = boardDao.boardListDao(); //모든 글 가져오기(조인 테이블)
		model.addAttribute("boardList", boardDtos);
		
		model.addAttribute("boardCount", boardDao.AllBoardCountDao()); //모든 글 갯수 전달하기
		
		return "boardlist2";
	}
	
	@RequestMapping(value = "/boarddelete")
	public String boarddelete(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum"); //삭제할 글의 번호
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		int result = boardDao.boardDeleteDao(bnum); //삭제 성공->1, 실패->0
		
		if (result == 0) { //삭제 실패
			model.addAttribute("msg", "글 삭제가 실패 하였습니다. 다시 확인해 주세요.");
			model.addAttribute("url", "blist");
		} else {
			model.addAttribute("msg", "글 삭제가 성공 하였습니다.");
			model.addAttribute("url", "blist");
		}
		
		return "alert/alert";
	}
	
	@RequestMapping(value = "/contentview")
	public String contentview(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum"); //유저가 클릭한 글의 번호
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		boardDao.updateHitDao(bnum); //조회수 증가함수 호출
		//메서드 호출 순서 주의
		BoardDto boardDto = boardDao.contentViewDao(bnum);
		
		model.addAttribute("boardDto", boardDto);
		
		return "boardContent";
	}
	
	
	
	@RequestMapping(value = "/boardmodify")
	public String boardmodify(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		int result = boardDao.boardModifyDao(bnum, btitle, bcontent);
		
		if(result == 1) { //수정 성공->1, 실패->0
			model.addAttribute("msg", "글 수정이 성공 하였습니다.");
			model.addAttribute("url", "blist");			
		} else {
			model.addAttribute("msg", "글 수정이 실패 하였습니다.");
			model.addAttribute("url", "blist");			
		}
		return "alert/alert";
	}
	
	@RequestMapping(value = "/pagelist")
	public String pagelist(HttpServletRequest request, Model model) {
		
		
		
		int pageSize = 10; //게시판 목록에 한 페이지 당 출력될 글 수
		int pageNum = 1; //유저가 클릭한 페이지 번호->현재 페이지 번호
		//처음에 게시판 리스트 링크로 들어왔을 경우는 무조건 1페이지로 출력->초기값 1
		int blockSize = 5; //페이지 블럭에 표시될 페이지의 수 (1 2 3 4 5 , 6 7 8 9 10 ...)
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum")); //유저가 선택한 페이지의 번호
		}
		
		int startRow = (pageNum * pageSize) - 9; //페이징 되었을 때 시작 행의 번호(1->1, 2->11, 3->21...)
		//((pageNum - 1) * pageSize) + 1
		int endRow = pageNum * pageSize; //1->10, 2->20, 3->30 ...
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		List<BoardDto> boardDtos = boardDao.pageBoardListDao(startRow, endRow); //페이징 된 모든 글 가져오기(조인 테이블)
		int totalCount = boardDao.AllBoardCountDao();
		
		int startPage = (((pageNum - 1)/blockSize) * blockSize) + 1;
		//1 2 3 4 5 -> 1, 6 7 8 9 10 -> 6, 11 12 13 14 15 -> 11 ...
		int endPage = startPage + blockSize - 1;
		//1 2 3 4 5 -> 5, 6 7 8 9 10 -> 10, 11 12 13 14 15 -> 15 ...
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);  
		//전체 글수로 만든 총 페이지 수 (글 153->16, 178->18, 12->2)
		if(endPage > totalPage) {
			endPage = totalPage;
		}	
		//실제 모든 글 갯수로 만든 총 페이지수(totalPage)가 endPage 보다 작을 경우
		//없는 페이지까지 페이지 블럭에 출력되므로 totalPage가 endPage 보다 작을 경우에
		//totalPage 값으로 endPage 값을 대체
		
		model.addAttribute("boardList", boardDtos);
		model.addAttribute("pageNum", pageNum); //유저가 클릭한 페이지 번호->현재 페이지 번호
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("boardCount", totalCount); //모든 글 갯수 전달하기
		
		return "pagelist";
	}
	
	@RequestMapping(value = "/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/maptest")
	public String maptest() {
		return "maptest";
	}
	
	@RequestMapping(value = "/kakaomaptest")
	public String kakaomaptest() {
		return "kakaomaptest";
	}
	
	@RequestMapping(value = "/commentwrite")
	public String commentwrite(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum");
		String ccontent = request.getParameter("ccontent");
		String cwriter = request.getParameter("cwriter");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		boardDao.commentWriteDao(bnum, ccontent, cwriter);
		
		return "redirect:contentview2?bnum=" + bnum;
	}
	
	@RequestMapping(value = "/contentview2")
	public String contentview2(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum"); //유저가 클릭한 글의 번호
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		boardDao.updateHitDao(bnum); //조회수 증가함수 호출

		BoardDto boardDto = boardDao.contentCommentViewDao(bnum); //댓글 포함한 조인 sql문 호출
		
		model.addAttribute("post", boardDto);
		
		return "contentview";
	}

}
