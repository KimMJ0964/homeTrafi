package myPage;

import java.io.IOException;
import java.util.List;

import board.Board;
import board.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/mypage/myPosts")
public class myPageMyBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myPageMyBoard() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("login");

        if (memId == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        BoardService boardservice = new BoardService();
        List<Board> postList = boardservice.getPostsByMemberId(memId);

        request.setAttribute("postList", postList);
        request.getRequestDispatcher("views/myPage/myPage.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
