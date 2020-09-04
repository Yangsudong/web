package member;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/member/memberUpdate")
public class MemberUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//수정페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("memberUpdate.jsp").forward(request, response);
		
	}

	//수정처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		MemberVO memberVO = new MemberVO();
		
		try {
			BeanUtils.copyProperties(memberVO, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String strHobby = "";
		String[] hobby = request.getParameterValues("hobby");
		System.out.println(Arrays.toString(hobby));
		if(hobby != null) {
			for(String temp : hobby) {
				strHobby += temp + "/";
			}
		}
		memberVO.setHobby(strHobby);
		
		MemberDAO dao = new MemberDAO();
		dao.update(memberVO);
		
		//목록으로 이동
		response.sendRedirect("memberSelectAll.do");
	}

}
