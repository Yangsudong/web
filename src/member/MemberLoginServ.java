package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/member/login", "/member/logout"})
public class MemberLoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//logout
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("../index.jsp");
	}
	
	//login
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 VO
		MemberVO memberVO = new MemberVO();
		memberVO.setId(request.getParameter("id"));
		memberVO.setPass(request.getParameter("pass"));
		
		//2. 서비스 처리(DB)
		MemberVO resultVO = MemberDAO.getInstance().selectOne(memberVO);
		
		
		//3. 결과저장
		String page = "";
		if(resultVO == null) {		//id가 없음
			request.setAttribute("erromsg", "해당 ID가 없습니다.");
			page = "login.jsp";
		} else {
			if(memberVO.getPass().equals(resultVO.getPass())) {		//로그인 성공
				request.getSession().setAttribute("login", resultVO);
				request.getSession().setAttribute("id", resultVO.getId());
				page = "../index.jsp";
			} else {
				page = "login.jsp";
			}
		}
		
		//4. 뷰페이지 이동(redierect, forward) 또는 뷰페이지 출력
		request.getRequestDispatcher(page).forward(request, response);
	
	}

}
