package board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardCreateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("게시글생성");
		String title = request.getParameter("title");
        String content = request.getParameter("content");
        String memId = "user01"; // 로그인 세션에서 사용자 ID를 가져온다고 가정
        
        BoardService boardService = new BoardService();
        int isCreated = boardService.createBoard(memId, title, content);
        System.out.println("isCreated : " + isCreated);
        
        String contextPath = request.getContextPath();
        String targetUrl = contextPath + "/board.bo?cpage=1"; // 이동할 URL

        // 리다이렉트 수행
        response.sendRedirect(targetUrl);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
