package com.skt.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.skt.board.model.vo.Board;
import com.skt.board.service.BoardService;
import com.skt.board.service.BoardServiceImpl;

import common.PageInfo;
import common.Template;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardListController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService bService = new BoardServiceImpl();

		// -------------------페이징처리---------------------------
		int currentPage = Integer.parseInt(request.getParameter("cpage"));
		int listCount = bService.selectListCount(); // 총 게시글 수

		PageInfo pi = Template.getPageInfo(listCount, currentPage, 10, 5);

		ArrayList<Board> list = bService.selectList(pi);

		request.setAttribute("list", list);
		request.setAttribute("pi", pi);

		request.getRequestDispatcher("views/board/boardMainPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
