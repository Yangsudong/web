package board;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import common.FileRenamePolicy;
import net.sf.json.JSONArray;

@WebServlet("/BoardInsertAjaxServ")
public class BoardInsertAjaxServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터 받아서 VO dao.insert vo를 json str 변환해서 출력
		BoardVO boardVO = new BoardVO();
		boardVO.setPoster(request.getParameter("poster"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContents(request.getParameter("contents"));
		
		BoardDAO.getInstance().insert(boardVO);
		
		BoardVO resultVO = BoardDAO.getInstance().selectOne(boardVO);
		String result = JSONArray.fromObject(resultVO).toString();
		response.getWriter().print(result);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
	}

}
