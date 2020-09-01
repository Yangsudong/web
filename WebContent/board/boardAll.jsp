<%@page import="board.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardAll.jsp</title>
</head>
<body>
	<h3 class="page_title">게시판 전체조회</h3>
	<div>
	<ul class="search">
		<li>메일수신여부</li>
		<li>성별</li>
		<li><button type="button">검색</button></li>
		
	</ul>
	</div>
	<table border="1" id="board">
		<thead>
			<tr>
				<th width="100" height="50">번호</th>
				<th width="100">작성자</th>
				<th width="50">제목</th>
				<th width="200">내용</th>
				<th width="150">작성일자</th>
				<th width="150">조회수</th>
				<th width="200">첨부파일</th>
			</tr>
		</thead>
		<tbody>
		<%
			ArrayList<BoardVO> list = 
					(ArrayList<BoardVO>)request.getAttribute("list");
			for(BoardVO board : list) {			
		%>
			<tr height="30">
				<td><a href="memberSelect.jsp"><%=board.getNo()%></a></td>
				<td><%=board.getNo()%></td>
				<td><%=board.getPoster()%></td>  
				<td><%=board.getSubject()%></td>  
				<td><%=board.getLastpost()%></td>  
				<td><%=board.getViews()%></td>
				<td><%=board.getFilename()%></td> 
			</tr>
		<% } %>
		</tbody>
	</table>
</body>
</html>