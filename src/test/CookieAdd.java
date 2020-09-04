package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/CookieAdd")
public class CookieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		//쿠기객체 생성
		Cookie cookie = new Cookie("id","hong");
		cookie.setPath("/");	
		
		//쿠키 유효시간 설정 (설정하지않으면 브라우저가 꺼지면 만료)
		cookie.setMaxAge(60*60*24);	//=24시간
		
		//쿠키저장
		response.addCookie(cookie);
		
		//쿠기객체2 생성
		Cookie cookie2 = new Cookie("popupYn","yes");
		cookie2.setPath("/");
				
		//쿠키2 유효시간 설정 (설정하지않으면 브라우저가 꺼지면 만료)
		cookie2.setMaxAge(60*60*24);	//=24시간
				
		//쿠키2저장
		response.addCookie(cookie2);
		
		
		response.sendRedirect("cookieSelect.jsp");
		
	}

}
