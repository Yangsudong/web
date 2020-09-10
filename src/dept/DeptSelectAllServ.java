package dept;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;

@WebServlet("/dept/deptSelectAll")
public class DeptSelectAllServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		String p = request.getParameter("p");
		String department_name = request.getParameter("department_name");
		//유효성 체크
		int page = 1;
		if(p != null) {
			page = Integer.parseInt(p);
		}
		DeptDAO dao = new DeptDAO();
		
		Paging paging = new Paging();
		paging.setPageUnit(5);
		paging.setPageSize(3);
		paging.setPage(page);
		
		//VO에 담기	
		DeptVO dept = new DeptVO();
		dept.setDepartment_name(department_name);
		paging.setTotalRecord(dao.count(dept));
		dept.setFirst(paging.getFirst());
		dept.setLast(paging.getLast());
				
		
		//전체조회
		ArrayList<DeptVO> list = dao.selectAll(dept);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("deptSelectAll.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
