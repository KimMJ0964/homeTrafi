package com.skt.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.skt.board.service.BoardServiceImpl;

public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		String contextPath = request.getContextPath();
		
		BoardServiceImpl bService = new BoardServiceImpl();
		int result = bService.deleteBoard(request, boardNo);
		
		if(result > 0) {
			
			request.setAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
			
			request.getRequestDispatcher("/board.bo?cpage=1").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
