<%@page import="dept.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptSelectAll.jsp</title>
<style>
	.pagination li {
		display : inline-block;
		border : 1px solid lightgray;
	}
	.pagination .active {
		border : 1px solid lightgray;
		background-color : lightyellow;
	}
</style>
</head>
<body>

<%@include file="/common/header.jsp" %>
<table border="1">

<form name="searchFrm">
	<input type="hidden" name="p" value="1">
	<input name="department_name" value="${parm.department_name}">
	<button>검색</button>
</from>
<% 

ArrayList<DeptVO> list = (ArrayList<DeptVO>)request.getAttribute("list");
	for( DeptVO dept : list) {
%>
	<tr>
		<td><a href="deptSelect?department_id=<%=dept.getDepartment_id()%>"><%=dept.getDepartment_id()%></a></td>
		<td><%=dept.getDepartment_name()%></td>
		<td><%=dept.getLocation_id() %></td>
		<td><%=dept.getManager_id() %>
	</tr>
<% } %>
</table>

<my:paging paging="${paging}" jsfunc="gopage" />
<script>
	function gopage(p) {
		searchFrm.p.value=p;
		searchFrm.submit();
		location.href = "deptSelectAll?p=" + p;
	}
</script>
</body>
</html>