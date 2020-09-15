<%@page import="board.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
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
				<th width="100">제목</th>
				<th width="200">내용</th>
				<th width="150">작성일자</th>
				<th width="150">조회수</th>
				<th width="200">첨부파일</th>
				<th>이미지</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items = "${list}" var="board">		
			<tr height="30">
				<td><a href="memberSelect.jsp">${board.no}</a></td>
				<td>${board.poster}</td>  
				<td>${board.subject}</td>  
				<td>${board.contents}</td> 
				<td>${board.lastpost}</td>  
				<td>${board.views}</td>
				<td><a href="download.do?filename=${board.filename}"> ${board.filename} </a></td> 
				<td>
					<c:if test="${not empty board.filename}">
						<img src="../images/${board.filename}" style="width:300px">
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>