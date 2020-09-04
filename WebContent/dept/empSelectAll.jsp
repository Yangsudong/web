<%@page import="member.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empSelectAll</title>
</head>
<body>
<%@include file="/common/header.jsp" %>
	<h3 class="page_title">회원 전체조회</h3>
	<div>
	<ul class="search">
		<li>메일수신여부</li>
		<li>성별</li>
		<li><button type="button">검색</button></li>
		
	</ul>
	</div>
	<table border="1" id="employees">
		<thead>
			<tr>
				<th width="100" height="50">사원번호</th>
				<th width="100">이름</th>
				<th width="50">이메일</th>
				<th width="200">입사일</th>
				<th width="150">부서</th>
				<th width="150">JOB_ID</th>
				<th width="200">MANAGER_ID</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${emplist}" var="emplist">		
			<tr height="30">
				<td><a href="empSelectAll.jsp">${emplist.employee_id}</a></td>
				<td>${emplist.first_name}&nbsp;${emplist.last_name}</td>
				<td>${emplist.email}</td>  
				<td>${emplist.hire_date}</td>				
				<td>${emplist.department_id}</td>
				<td>${emplist.job_id}</td> 
				<td>${emplist.manager_id}</td>
			</tr>
		</c:forEach>
		</tbody>
		
	</table>
</body>
</html>