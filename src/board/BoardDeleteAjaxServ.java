package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

@WebServlet("/BoardDeleteAjaxServ")
public class BoardDeleteAjaxServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//게시글 번호를 파라미터 받아서 단건조회 json 변환해서 출력
		Integer no = Integer.parseInt(request.getParameter("no"));
		BoardVO boardVO = new BoardVO();
		boardVO.setNo(no);
		BoardVO resultVO = BoardDAO.getInstance().selectOne(boardVO);
		String result = JSONArray.fromObject(resultVO).toString();
		response.getWriter().print(result);
		
		BoardDAO.getInstance().delete(boardVO);
		
		//response.getWriter().print("{\"result\",\"삭제완료\"}");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
