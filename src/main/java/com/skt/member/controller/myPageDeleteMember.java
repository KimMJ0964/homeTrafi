package com.skt.member.controller;

import java.io.IOException;

import com.skt.member.model.vo.Member;
import com.skt.member.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class myPageDeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myPageDeleteMember() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        
        // 세션에서 로그인된 사용자 정보 가져오기
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("login");

        // 비밀번호 확인
        String userPwd = request.getParameter("userPwd");
        
        MemberServiceImpl memberService = new MemberServiceImpl();
        Member member = memberService.getMemberById(memId);
        
        // 비밀번호가 맞으면 삭제
        if (member != null && member.getMemPwd().equals(userPwd)) {
            int result = memberService.deleteMember(memId);
            
            if (result > 0) {
                session.invalidate();  // 세션 삭제 (로그아웃)
                request.getRequestDispatcher("views/myPage/myPage.jsp").forward(request, response);	
            } else {
                request.setAttribute("msg", "회원 탈퇴에 실패했습니다.");
                request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
