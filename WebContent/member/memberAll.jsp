<%@page import="member.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberAll</title>
</head>
<body>
	<h3 class="page_title">회원 전체조회</h3>
	<div>
	<ul class="search">
		<li>메일수신여부</li>
		<li>성별</li>
		<li><button type="button">검색</button></li>
		
	</ul>
	</div>
	<table border="1" id="members">
		<thead>
			<tr>
				<th width="100" height="50">아이디</th>
				<th width="100">패스워드</th>
				<th width="50">성별</th>
				<th width="200">직업</th>
				<th width="150">메일수신여부</th>
				<th width="150">가입동기</th>
				<th width="200">취미</th>
				<th width="150">가입날짜</th>
			</tr>
		</thead>
		<tbody>
		<%
			ArrayList<MemberVO> list = 
					(ArrayList<MemberVO>)request.getAttribute("list");
			for(MemberVO member : list) {			
		%>
			<tr height="30">
				<td><a href="memberSelect.jsp"><%=member.getId()%></a></td>
				<td><%=member.getPass()%></td>
				<td><%=member.getGender()%></td>  
				<td><%=member.getJob()%></td>  
				<td><%=member.getMailYN()%></td>  
				<td><%=member.getReason()%></td>
				<td><%=member.getHobby()%></td> 
				<td><%=member.getRegdate()%></td>				
			</tr>
		<% } %>
		</tbody>
	</table>
</body>
</html>