package com.skt.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.skt.board.model.vo.Board;
import com.skt.board.model.vo.BoardComment;
import com.skt.board.service.BoardServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		HttpSession session = request.getSession();
        session.setAttribute("bno", boardNo);
		
		BoardServiceImpl bService = new BoardServiceImpl();
		//조회수 증가 + 상세조회
		Board b = bService.increaseCount(boardNo);
		
		ArrayList<BoardComment> commentList = bService.commentList(boardNo);

	        // 댓글 목록을 request에 담아서 JSP로 전달
	    request.setAttribute("commentList", commentList);
		
		if(b != null) {
			request.setAttribute("b", b);
			request.getRequestDispatcher("views/board/boardView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "상세조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
