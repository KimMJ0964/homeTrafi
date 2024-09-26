package myPage;

import java.io.IOException;
import java.io.PrintWriter;

import com.skt.member.model.vo.Member;
import com.skt.member.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class myPagePwdChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myPagePwdChangeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
	        
	        // 세션에서 로그인된 사용자 정보 가져오기
	        HttpSession session = request.getSession();
	        String memId = (String) session.getAttribute("login");
	        
	        // 비밀번호 확인 및 변경할 비밀번호 가져오기
	        String currentPwd = request.getParameter("userPwd");
	        String newPwd = request.getParameter("updatePwd");
	        
	        MemberServiceImpl memberService = new MemberServiceImpl();
	        Member member = memberService.getMemberById(memId);
	        
	        // 현재 비밀번호가 일치하는지 확인
	        if (member != null && member.getMemPwd().equals(currentPwd)) {
	            int result = memberService.updatePassword(memId, newPwd);
	            
	            if (result > 0) {
	            	request.getRequestDispatcher("views/myPage/myPage.jsp").forward(request, response);
	            } else {
	                request.setAttribute("msg", "비밀번호 변경에 실패했습니다.");
	                request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
	            }
	        } else {
	            request.setAttribute("msg", "현재 비밀번호가 일치하지 않습니다.");
	            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
	        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
