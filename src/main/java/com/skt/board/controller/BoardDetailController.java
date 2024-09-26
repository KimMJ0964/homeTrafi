package com.skt.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		System.out.println("게시글 번호 : " + boardNo);
		BoardService bService = new BoardService();
		BoardService bfService = new BoardService();
		//조회수 1증가시키고 디테일 페이지정보 가져오기
		Board b = bService.increaseCount(boardNo);
		BoardFile bf = bfService.fileDownload(boardNo);
		
		if(b != null) {
			request.setAttribute("board", b);
			
			if(bf != null) {
				request.setAttribute("boardFile", bf);
			}
			
			request.getRequestDispatcher("views/board/boardView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
