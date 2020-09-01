package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*							// 웹주소						
@WebServlet(urlPatterns = {"/hello","/deptSelect"}, loadOnStartup = 1)
			*/
public class HelloServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServ() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("service 실행");
    	System.out.println("client ip: "+request.getRemoteAddr());
    	System.out.println("client agent: "+request.getHeader("User-Agent"));
    	System.out.println("uri :"+request.getRequestURI());
    	System.out.println("url: "+request.getRequestURL());
    	System.out.println("query string: "+request.getQueryString());
    	
    	DeptDAO dao = new DeptDAO();
    	int id = Integer.parseInt(request.getParameter("id"));
    	// 웹에서 http://localhost:8088/javascript_review/hello?id=부서번호입력
    	DeptVO dept = dao.selectOne(new DeptVO(id));
    	request.setAttribute("dept", dept);
    	request.getRequestDispatcher("deptSelect.jsp")
    		   .forward(request, response);
		
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init 실행");
	}


}
