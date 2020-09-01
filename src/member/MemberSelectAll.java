package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 회원전체 조회
@WebServlet("/member/memberSelectAll.do")
public class MemberSelectAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//파라미터
		
		
		//DB 조회
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.selectAll(null);
		
		//결과 저장
		request.setAttribute("list", list);
		
		//뷰페이지로 포워드(이동)
		request.getRequestDispatcher("memberAll.jsp")
				.forward(request, response);
	}

}
