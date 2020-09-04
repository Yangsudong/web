package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SetAttrServ")
public class SetAttrServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShareObject obj2 = new ShareObject();
		obj2.setCount(1);
		obj2.setStr("클라이언트공유");
		
		HttpSession session = request.getSession();
		session.setAttribute("data", obj2);
		
		response.getWriter().append("session data set");
	}

}
