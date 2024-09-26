package myPage;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class myPageLoginSessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myPageLoginSessionTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("login", "USER02");
		
		String loginValue = (String) session.getAttribute("login");
	    System.out.println("Session login value: " + loginValue);
//		response.sendRedirect("views/myPage/myPage.jsp");
		request.getRequestDispatcher("views/myPage/myPage.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
