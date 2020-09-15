package board;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import common.FileRenamePolicy;

//파라미터 스트림 값을 바운드리(구분기호)로 잘라서 part배열로 만들어줌 (파일크기를 10mb로 제한)
@MultipartConfig(location = "c:/upload", 
				 maxRequestSize = 1024*1024*10
				 )
@WebServlet("/board/boardInsertServ.do")
public class BoardInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("boardInsert.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 받기
		BoardVO boardVO = new BoardVO();
		
		boardVO.setPoster(request.getParameter("poster"));
		boardVO.setSubject(request.getParameter("subject"));
		boardVO.setContents(request.getParameter("contents"));
		
		
		//첨부파일처리
		Part part = request.getPart("filename");
		String fileName = getFileName(part); //Long.toString(System.currentTimeMillis()); 
		String path = request.getServletContext().getRealPath("/images"); // "c:/upload";
		System.out.println(path);
		//파일명 중복체크
		File renamefile = FileRenamePolicy.rename(new File(path,fileName));
		part.write(path + "/" + renamefile.getName());
		boardVO.setFilename(renamefile.getName());
		
		//DB 등록처리
		BoardDAO dao = new BoardDAO();
		dao.insert(boardVO);
				
		//목록으로 이동
		response.sendRedirect("boardSelectAll.do");
				
	}
	
	private String getFileName(Part part) throws UnsupportedEncodingException {
		for (String cd : part.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
	
}
