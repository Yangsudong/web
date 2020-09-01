package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import member.MemberDAO;

@WebServlet("board/boardInsertServ.do")
public class BoardInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInsertServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("boardInsert.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardVO boardVO = new BoardVO();
		
		boardVO.setNo(Integer.parseInt(request.getParameter("no")));
		boardVO.setPoster(request.getParameter("poster"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContents(request.getParameter("contents"));
		boardVO.setLastpost(request.getParameter("lastpost"));
		boardVO.setViews(Integer.parseInt(request.getParameter("views")));
		boardVO.setFilename(request.getParameter("filename"));
		
		//DB 등록처리
		BoardDAO dao = new BoardDAO();
		dao.insert(boardVO);
				
		//목록으로 이동
		response.sendRedirect("boardSelectAll.do");
	}

}
