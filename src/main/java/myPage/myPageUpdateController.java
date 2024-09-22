package myPage;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.Member;
import member.MemberService;

public class myPageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public myPageUpdateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 데이터 처리

		// 사용자가 입력한 정보를 폼에서 가져옴
		String memId = request.getParameter("memId");
		String memName = request.getParameter("memName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String memNo = request.getParameter("memNo");
		String email = request.getParameter("email");

		// Member 객체에 새로 입력된 정보를 담음
		Member member = new Member(memId, memName, phone, address, memNo, email);

		// Service를 통해 업데이트 요청 처리
		int result = new MemberService().updateMember(member);

		if (result > 0) {
			// 업데이트 성공 시 성공 페이지로 이동
			request.getSession().setAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");
			response.sendRedirect(request.getContextPath() + "/myPageTest.me");
		} else {
			// 실패 시 에러 페이지로 이동
			request.setAttribute("message", "회원 정보 수정에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
