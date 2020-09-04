package dept;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dept/empInsert")
public class EmpInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//job
		List<JobVO> joblist = JobDAO.getInstance().selectAll();	
		request.setAttribute("joblist", joblist);
		
		//manager
		List<EmpVO> emplist = EmpDAO.getInstance().selectAll();
		request.setAttribute("emplist", emplist);
		
		//departments
		List<DeptVO> deptlist = DeptDAO.getInstance().selectAll(null);
		request.setAttribute("deptlist", deptlist);
		request.getRequestDispatcher("employeeInsert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		EmpVO empVO = new EmpVO();
		empVO.setEmployee_id(request.getParameter("employee_id"));
		empVO.setFirst_name(request.getParameter("first_name"));
		empVO.setLast_name(request.getParameter("last_name"));
		empVO.setEmail(request.getParameter("email"));
		empVO.setHire_date(request.getParameter("hire_date"));
		empVO.setDepartment_id(request.getParameter("department_id"));
		empVO.setJob_id(request.getParameter("job_id"));
		empVO.setManager_id(request.getParameter("manager_id"));
		
		EmpDAO dao = new EmpDAO();
		dao.insert(empVO);
		
		response.sendRedirect("empSelectAll");
	}

}
