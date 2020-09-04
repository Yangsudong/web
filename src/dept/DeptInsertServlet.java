package dept;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;


@WebServlet("/dept/deptInsert")
public class DeptInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//지역정보
		ArrayList<HashMap<String, String>> locationlist = LocationDAO.getInstance().selectAll();		
		request.setAttribute("locationlist", locationlist);
		
		//사원(매니저)정보
		List<EmpVO> emplist = EmpDAO.getInstance().selectAll();
		request.setAttribute("emplist", emplist);
		
		//부서등록 페이지로 이동
		request.getRequestDispatcher("deptInsertForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//1.파라미터 VO에 담기
				DeptVO deptVO = new DeptVO();
				deptVO.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
				deptVO.setDepartment_name(request.getParameter("department_name"));
				deptVO.setLocation_id(Integer.parseInt(request.getParameter("location_id")));
				deptVO.setManager_id(Integer.parseInt(request.getParameter("manager_id")));
				
				//2.등록처리
				DeptDAO dao = new DeptDAO();
				dao.insert(deptVO);
				
				//3.결과처리 (생략)
				
				
				//4. 전체 조회 서블릿 페이지로 이동
				response.sendRedirect("deptSelectAll");
	}

}
