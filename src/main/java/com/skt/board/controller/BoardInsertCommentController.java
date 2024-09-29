package com.skt.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.skt.board.model.vo.BoardComment;
import com.skt.board.service.BoardServiceImpl;

public class BoardInsertCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInsertCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        
        // 로그인된 사용자 세션에서 아이디 가져오기
        String memId = (String) request.getSession().getAttribute("login");
        int postNo = (int)request.getSession().getAttribute("bno");
        String content = request.getParameter("content");

        // 댓글 VO 생성
        BoardComment boardComment = new BoardComment(postNo, memId, content);
        System.out.println(boardComment);
        
        // 서비스 호출하여 댓글 저장
        int result = new BoardServiceImpl().insertComment(boardComment);

        if(result > 0) {
            response.getWriter().print("success");
            request.getRequestDispatcher("detail.bo?bno=" + postNo).forward(request, response);
        } else {
            response.getWriter().print("fail");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
