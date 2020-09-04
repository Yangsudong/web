package member;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import common.ConnectionManager;

@WebServlet("/member/memberInsert.do")
public class MemberInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInsertServ() {
        super();
    }
    // 등록 페이지 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("memberInsert.jsp")
				.forward(request, response);
	}

	// 등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//파라미터를 VO에 담기
		MemberVO memberVO = new MemberVO();
/* 		memberVO.setId(request.getParameter("id"));
		memberVO.setPass(request.getParameter("pass"));
		memberVO.setGender(request.getParameter("gender"));
		memberVO.setJob(request.getParameter("job"));
		memberVO.setMailYN(request.getParameter("mailYn"));
		memberVO.setReason(request.getParameter("reason"));
*/
		try {
			BeanUtils.copyProperties(memberVO, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println("===============map===============");
		Map<String, String[]> map = request.getParameterMap();
		System.out.println(map);
		System.out.println("id = " + map.get("id")[0]);
		
		System.out.println("===============names===============");
		Enumeration<String> pnames = request.getParameterNames();
		while(pnames.hasMoreElements()) {
			System.out.println(pnames.nextElement());
		}
		
		//checkbox
		System.out.println("===============values===============");
		String strHobby = "";
		String[] hobby = request.getParameterValues("hobby");
		System.out.println(Arrays.toString(hobby));
		if(hobby != null) {
			for(String temp : hobby) {
				strHobby += temp + "/";
			}
		}
		memberVO.setHobby(strHobby);
		
		
		//DB 등록처리
		MemberDAO dao = new MemberDAO();
		dao.insert(memberVO);
		
		//목록으로 이동
		response.sendRedirect("memberSelectAll.do");
		
	}

}
